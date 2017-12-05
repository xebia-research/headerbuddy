package com.xebia.headerbuddy.models;

import com.xebia.headerbuddy.validators.ValidURLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

@Pattern(regexp = "^https?:\\\\/\\\\/(www\\\\.)?((?!www\\\\.)[a-zA-Z0-9\\\\.\\\\-\\\\_]{1,})(\\\\.([a-z]{1,})|\\\\:([0-9]{1,}))\\\\/?(([a-zA-Z0-9\\\\/\\\\.\\\\-\\\\_\\\\:\\\\?\\\\=\\\\&]{1,}))?$")
@Constraint(validatedBy = ValidURLValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {

    String message() default "Default message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
