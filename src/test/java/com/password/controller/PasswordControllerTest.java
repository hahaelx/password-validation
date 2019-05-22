package com.password.controller;

import com.password.rule.ValidateResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordControllerTest {
    String URI = "/password/validate";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_success_123twsw() {
        String password = "123twsw";
        ResponseEntity<ValidateResult> response = restTemplate.getForEntity(URI + "/" + password, ValidateResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ValidateResult result = response.getBody();
        Assert.assertEquals(result.isSuccess(), true);
        Assert.assertEquals(result.getFailedMessages().size(), 0);
    }

    @Test
    public void should_success_sdlo231d3() {
        String password = "sdlo231d3";
        ResponseEntity<ValidateResult> response = restTemplate.getForEntity(URI + "/" + password, ValidateResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ValidateResult result = response.getBody();
        Assert.assertEquals(result.isSuccess(), true);
        Assert.assertEquals(result.getFailedMessages().size(), 0);
    }

    @Test
    public void should_failed_1234() {
        String password = "1234";
        ResponseEntity<ValidateResult> response = restTemplate.getForEntity(URI + "/" + password, ValidateResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ValidateResult result = response.getBody();
        Assert.assertEquals(result.isSuccess(), false);
        Assert.assertEquals(result.getFailedMessages().size(), 2);
    }

    @Test
    public void should_failed_123456789012345676890() {
        String password = "123456789012345676890";
        ResponseEntity<ValidateResult> response = restTemplate.getForEntity(URI + "/" + password, ValidateResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ValidateResult result = response.getBody();
        Assert.assertEquals(result.isSuccess(), false);
        Assert.assertEquals(result.getFailedMessages().size(), 2);
    }

    @Test
    public void should_failed_abcabc() {
        String password = "abcabc";
        ResponseEntity<ValidateResult> response = restTemplate.getForEntity(URI + "/" + password, ValidateResult.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        ValidateResult result = response.getBody();
        Assert.assertEquals(result.isSuccess(), false);
        Assert.assertEquals(result.getFailedMessages().size(), 2);
    }
}