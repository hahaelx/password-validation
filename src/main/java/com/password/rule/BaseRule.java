package com.password.rule;

import java.util.Properties;

public abstract class BaseRule {

    protected Properties properties;

    public abstract boolean validate(String value);

    public abstract String getFailedMessage();

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
