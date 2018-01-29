package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidMethod;
import com.xebia.headerbuddy.models.HttpRequestMethod;
import org.apache.commons.lang3.EnumUtils;
import org.eclipse.jgit.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidMethodValidator implements ConstraintValidator<ValidMethod, String> {

    @Override
    public void initialize(ValidMethod constraintAnnotation) {
        // Left blank
    }

    /*
    * String value: Is a big string with the requested methods from the parameter the are divided by a ,
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> requestedMethods = new ArrayList<>();
        Collections.addAll(requestedMethods, StringUtils.toLowerCase(value).split(","));

        if (requestedMethods.contains("all")) {
            return true;
        }

        for (String method : requestedMethods) {
            if (!EnumUtils.isValidEnum(HttpRequestMethod.class, method.toUpperCase())) {
                return false;
            }
        }

        // If no unsupported methods are found return true;
        return true;
    }
}
