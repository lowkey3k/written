package com.tencent.written.service.param;



import java.util.HashMap;
import java.util.Map;

/**
 * @description: 条件类型
 * @author haitao.li
 * @date 2021/6/21 15:52
 */
public enum TermEnum {

    /**
     * ==
     *
     
     */
    eq("="),

    /**
     * !=
     *
     
     */
    not("not"),

    /**
     * like
     *
     
     */
    like("like"),

    /**
     * not like
     *
     
     */
    nlike("nlike"),

    /**
     * >
     *
     
     */
    gt(">"),

    /**
     * <
     *
     
     */
    lt("<"),

    /**
     * >=
     *
     
     */
    gte("gte"),

    /**
     * <=
     *
     
     */
    lte("lte"),

    /**
     * in
     *
     
     */
    in("in"),

    /**
     * notin
     *
     */
    nin("nin"),

    /**
     * =''
     *
     */
    empty("empty"),

    /**
     * !=''
     *
     
     */
    nempty("nempty"),

    /**
     * is null
     *
     
     */
    isnull("isnull"),

    /**
     * not null
     *
     
     */
    notnull("notnull");


    TermEnum(String value) {
        this.value = value;
    }


    private static final Map<String, TermEnum> CACHE = new HashMap<>();

    static {
        if (values() != null) {
            for (TermEnum operator : values()) {
                CACHE.put(operator.getValue(), operator);
            }
        }
    }

    private String value;


    public static TermEnum parse(String operation) {
        return CACHE.get(operation);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
