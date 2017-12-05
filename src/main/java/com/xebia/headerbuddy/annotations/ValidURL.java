package com.xebia.headerbuddy.annotations;

import com.xebia.headerbuddy.annotations.validators.ValidURLValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Pattern(regexp = "(?i)^https?:\\/\\/(www\\.)?((?!www\\.)[a-z0-9\\.\\-\\_]+)(\\.([a-z]{1,})|\\:([0-9]+))\\/?([a-z0-9\\/\\.\\-\\_\\:\\?\\=\\&]+)?$")
@Constraint(validatedBy = ValidURLValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidURL {

    String message() default "Invalid URL!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
