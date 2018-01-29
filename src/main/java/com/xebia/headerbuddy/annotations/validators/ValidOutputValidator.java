package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidOutput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidOutputValidator implements ConstraintValidator<ValidOutput, String> {

    @Override
    public void initialize(ValidOutput constraintAnnotation) {
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return true;
    }
}
