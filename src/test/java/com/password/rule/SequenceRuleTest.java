package com.password.rule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



public class SequenceRuleTest {
    private BaseRule baseRule;

    @Before
    public void setUp() throws Exception {
        baseRule = new SequenceRule();
    }

    @Test
    public void testValidate() {
        Assert.assertEquals(baseRule.validate("test1234"), true);
        Assert.assertEquals(baseRule.validate("abcde"), true);
        Assert.assertEquals(baseRule.validate("abcab"), true);
        Assert.assertEquals(baseRule.validate("eekkee"), true);
    }

    @Test
    public void testFailedValidate() {
        Assert.assertEquals(baseRule.validate("1111"), false);
        Assert.assertEquals(baseRule.validate("1212"), false);
        Assert.assertEquals(baseRule.validate("dw1212wd"), false);
        Assert.assertEquals(baseRule.validate("ababc"), false);
    }
}