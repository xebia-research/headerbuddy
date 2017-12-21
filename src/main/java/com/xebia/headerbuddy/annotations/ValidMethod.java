package com.xebia.headerbuddy.annotations;

import com.xebia.headerbuddy.annotations.validators.ValidMethodValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "(?i)^((?!,,)[a-z,])+$")
@Constraint(validatedBy = ValidMethodValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMethod {

    String message() default "Invalid HTTP Method!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
