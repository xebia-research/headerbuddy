package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidOutput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Does nothing at the moment but if you want a bigger validation you can create a validate in the isValid method
 */
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
