package com.xebia.headerbuddy.annotations;


import com.xebia.headerbuddy.annotations.validators.ValidOutputValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "(?i)^(json|xml|html)$",
        message = "Invalid output value!")
@Constraint(validatedBy = ValidOutputValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOutput {

    String message() default "Output is not recognized";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}