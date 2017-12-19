package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidAPIKey;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Does nothing at the moment but if you want greater url validation e.g. give some sites not allowance you can create a validate in the  isValid method
 */
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
