package com.xebia.headerbuddy.annotations;

import com.xebia.headerbuddy.annotations.validators.ValidEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "(?i)^(.+)\\@(.+)\\.(.+)$", message = "Invalid Email!")
@Constraint(validatedBy = ValidEmailValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "Invalid Email!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
