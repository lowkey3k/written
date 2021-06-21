package com.tencent.written.service.util;

import com.tencent.written.service.param.Param;
import com.tencent.written.service.param.Term;
import com.tencent.written.service.param.TermEnum;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.written.service.param.TermEnum.*;

/**
 * @author haitao.li
 * @description: 解析sql为term条件
 * @date 2021/6/21 15:56
 */
public class TermUtil {

    public static List<Param> params(String sql) {
        List<Param> params = new ArrayList<>();
        String[] ors = sql.split("or");
        for (int i = 0; i < ors.length; i++) {
            params.add(param(ors[i].trim()));
        }
        return params;
    }

    public static Param param(String sql) {
        Param param = new Param();
        String[] ands = sql.split("and");
        for (int i = 0; i < ands.length; i++) {
            String kv = ands[i].trim();
            if (kv.contains(eq.getValue())) {
                param.addTerm(getTerm(kv, eq));
            }
            if (kv.contains(gt.getValue())) {
                param.addTerm(getTerm(kv, gt));
            }
            if (kv.contains(lt.getValue())) {
                param.addTerm(getTerm(kv, lt));
            }
            if (kv.contains(like.getValue())) {
                param.addTerm(getTerm(kv, like));
            }
        }
        return param;
    }

    private static Term getTerm(String kv, TermEnum gt) {
        String[] split = kv.split(gt.getValue());
        String k = split[0].trim();
        String v = split[1].trim();
        Object value;
        if (!v.contains("\"")) {
            value = Double.parseDouble(v.replace("\"", "").trim());
        } else {
            value = v.replace("\"", "").trim();
        }
        Term term = new Term();
        term.setColumn(k);
        term.setValue(value);
        term.setTermType(gt);
        return term;
    }


}
