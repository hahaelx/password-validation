package com.password.service;

import com.password.rule.BaseRule;
import com.password.rule.ValidateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class ValidationServiceImpl implements ValidationService {
    Logger logger = Logger.getLogger(ValidationServiceImpl.class.getName());
    List<BaseRule> ruleList = new ArrayList<>();
    private Properties properties;

    @Autowired
    public ValidationServiceImpl(@Value("${validation.rules}") String rules,
                                 @Value("${validation.configPath}") String configPath) {
        getValidationConfig(configPath);
        logger.info("ValidationServiceImpl");
        String[] rulesArray = rules.split(",");
        for (String rule : rulesArray) {
            logger.info("add rule= " + rule);
            addRule(rule);
        }
    }

    private void addRule(String className) {
        try {
            BaseRule valudateRule = (BaseRule) Class.forName(className).newInstance();
            valudateRule.setProperties(properties);
            ruleList.add(valudateRule);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getValidationConfig(String configPath) {
        try {
            Resource resource = new ClassPathResource(configPath);
            this.properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ValidateResult validatePassword(String password) {
        logger.info("validatePassword " + password);
        ValidateResult validateResult = new ValidateResult();
        for (BaseRule rule : ruleList) {
            if (!rule.validate(password)) {
                validateResult.setSuccess(false);
                validateResult.addFailedMessages(rule.getFailedMessage());
            }
        }
        return validateResult;
    }
}
