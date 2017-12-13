package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidAPIKey;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAPIKeyValidator implements ConstraintValidator<ValidAPIKey, String> {

    @Override
    public void initialize(ValidAPIKey constraintAnnotation) {
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
