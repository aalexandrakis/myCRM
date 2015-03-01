package com.aalexandrakis.mycrm.validators;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, Date>{
	private String format;
	public void initialize(ValidDate arg0) {
		format = arg0.format();
	}

	public boolean isValid(Date dateToValidate, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if (dateToValidate == null){
			return false;
		}
		DateFormat df = new SimpleDateFormat(format);
		try {
			df.format(dateToValidate);
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
}
