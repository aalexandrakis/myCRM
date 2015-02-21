package myCRM;

import java.math.BigDecimal;

import org.junit.Test;

import com.aalexandrakis.mycrm.models.Invoice;

public class GrossToString {

	@Test
	public void grossToString(){
		Invoice invoice = new Invoice();
		invoice.setGross(new BigDecimal("2460.01"));
		invoice.grossInWords();
	}
}
