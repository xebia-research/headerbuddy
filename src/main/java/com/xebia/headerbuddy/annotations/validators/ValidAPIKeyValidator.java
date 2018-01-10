package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/*
 * Does nothing at the moment but if you want greater url validation e.g. give some sites not allowance you can create a validate in the  isValid method
 */
@Component
public class ValidAPIKeyValidator implements ConstraintValidator<ValidAPIKey, String> {

    @Autowired
    private EuserRepository userRepository;

    @Autowired
    private Environment env;

    @Override
    public void initialize(ValidAPIKey constraintAnnotation) {
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!Boolean.parseBoolean(env.getProperty("key.required"))) {
            return true;
        }
        if (value == null || Pattern.matches(value, "^[a-zA-Z0-9]+$")) {
            return false;
        }
        // If a user exists with this api key the key is valid
        if (userRepository.findByApikey(value) != null) {
            return true;
        }

        return false;
    }
}
