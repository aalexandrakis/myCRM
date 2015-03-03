package com.aalexandrakis.mycrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.models.Invoice;

@Controller
public class InvoicePdfController {
	
	@Autowired 
	Invoice invoice;
	
	@RequestMapping(value = "/invoicePdf/{invoiceId}", method = RequestMethod.GET)
	protected ModelAndView  invoicePdf(HttpServletRequest request, HttpServletResponse respose, @PathVariable Integer invoiceId) {
		ModelMap parms = new ModelMap();
		parms.put("REPORT_CONNECTION", Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
	    parms.put("invoiceId", invoiceId.toString());
		return new ModelAndView("pdfInvoiceView", parms);
	}
}
