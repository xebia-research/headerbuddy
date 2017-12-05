package com.xebia.headerbuddy.validators;

import com.xebia.headerbuddy.models.ValidURL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidURLValidator implements ConstraintValidator<ValidURL, String> {

    //private final String REGEX_URL = "^https?:\\/\\/(www\\.)?((?!www\\.)[a-zA-Z0-9\\.\\-\\_]{1,})(\\.([a-z]{1,})|\\:([0-9]{1,}))\\/?(([a-zA-Z0-9\\/\\.\\-\\_\\:\\?\\=\\&]{1,}))?$";

    @Override
    public void initialize(ValidURL constraintAnnotation){
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return true;
    }
}
