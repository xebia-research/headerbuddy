package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Does nothing at the moment but if you want greater url validation e.g. give some sites not allowance you can create a validate in the  isValid method
 */
public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
