package com.password.controller;

import com.password.rule.ValidateResult;
import com.password.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "password")
public class PasswordController {
    Logger logger = Logger.getLogger(PasswordController.class.getName());

    private ValidationService validationService;

    @Autowired
    public PasswordController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @GetMapping("/validate/{value}")
    public ValidateResult validate(@PathVariable String value) {
        logger.info("validate");
        return validationService.validatePassword(value);
    }
}

