package com.tencent.written.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.written.service.SqlWhereService;
import com.tencent.written.service.param.Param;
import com.tencent.written.service.param.Term;
import com.tencent.written.service.util.TermUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haitao.li
 * @description: sql匹配json，输出匹配结果
 * @date 2021/6/21 15:06
 */
@Service
public class SqlWhereServiceImpl implements SqlWhereService {

    private static final String PERCENT="%";
    private static final String POINT=".";
    /**
     * sql匹配json，输出匹配结果
     *
     * @param json 目标json value不含and和or的解决方法
     * @param sql  目标sql
     * @return
     */
    @Override
    public Boolean sqlWhere(String json, String sql) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<Param> params = TermUtil.params(sql);
        AtomicInteger total = new AtomicInteger();
        params.forEach(param -> {
            List<Term> terms = param.getTerms();
            terms.forEach(term -> {
                Object value = term.getValue();
                String column = term.getColumn();
                Object jValue = jsonObject.get(column);
                if (column.contains(POINT)) {
                    jValue = nestedValue(column, jsonObject);
                }
                switch (term.getTermType()) {
                    case gt:
                        if (jValue instanceof Number && value instanceof Number) {
                            Number jV = (Number) jValue;
                            Number v = (Number) value;
                            if (jV.doubleValue() <= v.doubleValue()) {
                                total.getAndIncrement();
                                break;
                            }
                        }
                        break;
                    case lt:
                        if (jValue instanceof Number && value instanceof Number) {
                            Number jV = (Number) jValue;
                            Number v = (Number) value;
                            if (jV.doubleValue() >= v.doubleValue()) {
                                total.getAndIncrement();
                                break;
                            }
                        }
                        break;
                    case eq:
                        if (jValue instanceof Number && value instanceof Number) {
                            Number jV = (Number) jValue;
                            Number v = (Number) value;
                            if (jV.doubleValue() != v.doubleValue()) {
                                total.getAndIncrement();
                                break;
                            }
                        } else if (!jValue.equals(value)) {
                            total.getAndIncrement();
                            break;
                        }
                        break;
                    case like:
                        if (jValue instanceof String && value instanceof String) {
                            String jS = (String) jValue;
                            String v = (String) value;
                            String trim = v.replace(PERCENT, "").trim();
                            if (v.startsWith(PERCENT) && v.endsWith(PERCENT)) {
                                if (!jS.contains(trim)) {
                                    total.getAndIncrement();
                                    break;
                                }
                            }else if (v.startsWith(PERCENT)) {
                                if (!jS.endsWith(trim)) {
                                    total.getAndIncrement();
                                    break;
                                }
                            } else if (v.endsWith(PERCENT)) {
                                if (!jS.startsWith(trim)) {
                                    total.getAndIncrement();
                                    break;
                                }
                            }
                        }
                        break;
                    default:
                }
            });
        });
        return total.get() < params.size();
    }

    /**
     * 获取json嵌套值
     *
     * @param key
     * @param jsonObject
     * @return
     */
    private Object nestedValue(String key, JSONObject jsonObject) {
        String[] nestedKey = key.split("\\.");
        int i = 0;
        while (i < nestedKey.length - 1) {
            JSONObject jo = jsonObject.getJSONObject(nestedKey[i]);
            jsonObject = jo;
            i++;
        }
        Object v = jsonObject.get(nestedKey[i]);
        return v;
    }


}
