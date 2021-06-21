package com.tencent.written.service;

/**
 * @author haitao.li
 * @description: sql匹配json，输出匹配结果
 * @date 2021/6/21
 */
public interface SqlWhereService {

    /**
     * sql匹配json中的数据，判断sql是否成立
     * @param json 目标json
     * @param sql  目标sql
     * @return 返回匹配结果，true：匹配成功，false:匹配失败
     */
    Boolean sqlWhere(String json,String sql);

}
