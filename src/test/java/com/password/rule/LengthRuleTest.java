package com.password.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

public class LengthRuleTest {
    private BaseRule baseRule;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("validation.rule.length.max","12");
        properties.setProperty("validation.rule.length.min","5");
        baseRule = new LengthRule();
        baseRule.setProperties(properties);
    }

    @Test
    public void testValidate() {
        Assert.assertEquals(baseRule.validate("test1234"), true);
        Assert.assertEquals(baseRule.validate("abcde"), true);
        Assert.assertEquals(baseRule.validate("abcab1"), true);
        Assert.assertEquals(baseRule.validate("eekkee1"), true);
    }

    @Test
    public void testFailedValidate() {
        Assert.assertEquals(baseRule.validate("1111"), false);
        Assert.assertEquals(baseRule.validate("1212"), false);
        Assert.assertEquals(baseRule.validate("12313123123312313asd"), false);
        Assert.assertEquals(baseRule.validate("asddasdasdasdasda"), false);
    }
}