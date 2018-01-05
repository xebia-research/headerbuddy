package com.xebia.headerbuddy.annotations.validators;

import com.xebia.headerbuddy.annotations.ValidAPIKey;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Does nothing at the moment but if you want greater url validation e.g. give some sites not allowance you can create a validate in the  isValid method
 */
@Component
public class ValidAPIKeyValidator implements ConstraintValidator<ValidAPIKey, String> {

    @Autowired
    private EuserRepository userRepository;

    @Override
    public void initialize(ValidAPIKey constraintAnnotation) {
        // Left blank
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // If a user exists with this api key the key is valid
        if (userRepository.findByApikey(value) != null) {
            return true;
        }
        return false;
    }
}
