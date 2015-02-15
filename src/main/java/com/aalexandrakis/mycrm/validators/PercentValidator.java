package com.aalexandrakis.mycrm.validators;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PercentValidator implements ConstraintValidator<Percent, BigDecimal>{
	
	public void initialize(Percent arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(BigDecimal percent, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		if (percent == null){
			return false;
		}
		if (percent.compareTo(new BigDecimal("100")) == 1){
			return false;
		} else { 
			return true;
		}
	}
	
}
