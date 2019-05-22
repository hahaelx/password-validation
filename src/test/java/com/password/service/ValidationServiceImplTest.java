package com.password.service;

import com.password.rule.ValidateResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidationServiceImplTest {
    String RULES = "com.password.rule.LengthRule,com.password.rule.ConsistRule,com.password.rule.SequenceRule";
    String CONFIG_PATH = "/validation.properties";
    String MSG_LENGTH = "Must be between 5 and 12 characters in length.";
    String MSG_SEQUENCE = "Must not contain any sequence of characters immediately followed by the same sequence.";
    String MSG_CONSIST = "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.";
    ValidationServiceImpl validationService;

    @Before
    public void setUp() throws Exception {
        validationService = new ValidationServiceImpl(RULES, CONFIG_PATH);
    }

    @Test
    public void test_success_abc1234() {
        ValidateResult validateResult = validationService.validatePassword("abc1234");
        Assert.assertEquals(validateResult.isSuccess(), true);
    }

    @Test
    public void test_success_q1w2e3r4() {
        ValidateResult validateResult = validationService.validatePassword("q1w2e3r4");
        Assert.assertEquals(validateResult.isSuccess(), true);
    }

    @Test
    public void test_success_pl12j3dd3() {
        ValidateResult validateResult = validationService.validatePassword("pl12j3dd3");
        Assert.assertEquals(validateResult.isSuccess(), true);
    }

    @Test
    public void test_failed_12345() {
        ValidateResult validateResult = validationService.validatePassword("12345");
        Assert.assertEquals(validateResult.isSuccess(), false);
        String[] testArray = new String[]{MSG_CONSIST};
        Assert.assertArrayEquals(validateResult.getFailedMessages().toArray(), testArray);
    }

    @Test
    public void test_failed_1234() {
        ValidateResult validateResult = validationService.validatePassword("1234");
        Assert.assertEquals(validateResult.isSuccess(), false);
        String[] testArray = new String[]{MSG_LENGTH, MSG_CONSIST};
        Assert.assertArrayEquals(validateResult.getFailedMessages().toArray(), testArray);
    }

    @Test
    public void test_failed_a1a1() {
        ValidateResult validateResult = validationService.validatePassword("a1a1");
        Assert.assertEquals(validateResult.isSuccess(), false);
        String[] testArray = new String[]{MSG_LENGTH, MSG_SEQUENCE};
        Assert.assertArrayEquals(validateResult.getFailedMessages().toArray(), testArray);
    }

    @Test
    public void test_failed_abcabc() {
        ValidateResult validateResult = validationService.validatePassword("abcabc");
        Assert.assertEquals(validateResult.isSuccess(), false);
        String[] testArray = new String[]{MSG_CONSIST, MSG_SEQUENCE};
        Assert.assertArrayEquals(validateResult.getFailedMessages().toArray(), testArray);
    }
}