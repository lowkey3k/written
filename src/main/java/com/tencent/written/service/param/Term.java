package com.tencent.written.service.param;

import java.io.Serializable;

/**
 * @author haitao.li
 * @description: sql条件解析
 * @date 2021/6/21 15:51
 */
public class Term {


    /**
     * 字段
     */
    private String column;

    /**
     * 值
     */
    private Object value;

    /**
     * 默认条件类型
     */
    private TermEnum termType = TermEnum.eq;


    public String getColumn() {
        return column;
    }

    public Term setColumn(String column) {
        this.column = column;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Term setValue(Object value) {
        this.value = value;
        return this;
    }

    public TermEnum getTermType() {
        return termType;
    }

    public Term setTermType(TermEnum termType) {
        this.termType = termType;
        return this;
    }


}
