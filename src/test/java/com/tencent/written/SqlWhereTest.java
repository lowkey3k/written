package com.tencent.written;

import com.tencent.written.service.SqlWhereService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author haitao.li
 * @description: 单元测试
 * @date 2021/6/21 17:23
 */
@SpringBootTest
public class SqlWhereTest {

    @Resource
    private SqlWhereService service;
    String json = "{\n" +
            "\"a\": 3,\n" +
            "\"b\":\"hello nihao\",\n" +
            "\"c\":{\n" +
            "    \"d\":\"niyao tencent\",\n" +
            "    \"e\": 4.5\n" +
            "},\n" +
            "\"f\": 4.5,\n" +
            "\"g\": \"try hard\"\n" +
            "}";


    /**
     * and查询  包含 > < like =和嵌套
     */
    @Test
    public void test01() {
        String sql = "a=3 and f <5 and b like \"hello%\" and c.e > 3 and c.d like \"%tencent\"";
        Boolean aBoolean = service.sqlWhere(json, sql);
        Assert.isTrue(aBoolean, "sql匹配失败");
    }


    /**
     * and 和 or查询  包含 > < like =和嵌套
     */
    @Test
    public void test02() {
        String sql = "a=3 and f <5 and b like \"hello%\" and c.e < 6 and c.d like \"%tencent1\" or g like \"%har%\" ";
        Boolean aBoolean = service.sqlWhere(json, sql);
        Assert.isTrue(aBoolean, "sql匹配失败");
    }
}
