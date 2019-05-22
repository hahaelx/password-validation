package com.password.rule;

import java.util.ArrayList;
import java.util.List;

public class ValidateResult {
    private boolean isSuccess = true;
    private List<String> failedMessages = new ArrayList<>();

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<String> getFailedMessages() {
        return failedMessages;
    }

    public void addFailedMessages(String message) {
        failedMessages.add(message);
    }


}
