package com.aalexandrakis.mycrm.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint (validatedBy = PercentValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Percent {
	String message() default "Please enter a valid percent value";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
