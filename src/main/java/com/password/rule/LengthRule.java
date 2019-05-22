package com.password.rule;

public class LengthRule extends BaseRule {

    @Override
    public boolean validate(String value) {
        int minLength = Integer.parseInt(this.properties.getProperty("validation.rule.length.min"));
        int maxLength = Integer.parseInt(this.properties.getProperty("validation.rule.length.max"));
        if (value.length() < minLength || value.length() > maxLength) {
            return false;
        }
        return true;
    }

    @Override
    public String getFailedMessage() {
        return this.properties.getProperty("validation.rule.length.message");
    }
}
