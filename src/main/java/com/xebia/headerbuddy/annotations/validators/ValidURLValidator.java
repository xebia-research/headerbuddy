package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidURL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidURLValidator implements ConstraintValidator<ValidURL, String> {

    @Override
    public void initialize(ValidURL constraintAnnotation){
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return true;
    }
}
