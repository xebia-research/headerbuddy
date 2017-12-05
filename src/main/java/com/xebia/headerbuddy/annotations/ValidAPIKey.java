package com.xebia.headerbuddy.annotations;

import com.xebia.headerbuddy.annotations.validators.ValidAPIKeyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Pattern(regexp = "^[a-zA-Z0-9]{24,}$")
@Pattern(regexp = "^[a-zA-Z0-9]+$")
@Constraint(validatedBy = ValidAPIKeyValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAPIKey {

    String message() default "Invalid API Key!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
