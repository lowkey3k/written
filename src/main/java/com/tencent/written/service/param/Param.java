package com.tencent.written.service.param;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:  param
 * @author haitao.li
 * @date 2021/6/21 18:42
 */
public class Param {

    public List<Term> getTerms() {
        return terms;
    }

    /**
     * 条件
     */
    protected List<Term> terms = new LinkedList<>();


    public <T extends Param> T addTerm(Term term) {
        terms.add(term);
        return (T) this;
    }



}
