package com.password.service;


import com.password.rule.ValidateResult;

public interface ValidationService {
    ValidateResult validatePassword(String password);
}
