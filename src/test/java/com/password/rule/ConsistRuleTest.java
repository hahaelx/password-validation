package com.password.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

import static org.junit.Assert.*;

public class ConsistRuleTest {
    private BaseRule baseRule;

    @Before
    public void setUp() throws Exception {
        baseRule = new ConsistRule();
    }

    @Test
    public void testValidate() {
        Assert.assertEquals(baseRule.validate("test1234"), true);
        Assert.assertEquals(baseRule.validate("11abcde"), true);
        Assert.assertEquals(baseRule.validate("a3cab21"), true);
        Assert.assertEquals(baseRule.validate("ee12ee12"), true);
    }

    @Test
    public void testFailedValidate() {
        Assert.assertEquals(baseRule.validate("1111123"), false);
        Assert.assertEquals(baseRule.validate("3434343"), false);
        Assert.assertEquals(baseRule.validate("asddasd"), false);
        Assert.assertEquals(baseRule.validate("qweqwe"), false);
    }
}