package com.password.rule;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ConsistRule extends BaseRule {
    final String regex = "^(?=.*\\d)(?=.*[a-z]).*$";

    @Override
    public boolean validate(String value) {
        Pattern pattern = Pattern.compile(regex, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    @Override
    public String getFailedMessage() {
        return this.properties.getProperty("validation.rule.consist.message");
    }
}
