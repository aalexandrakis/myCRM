package com.aalexandrakis.mycrm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrm.models.Invoice;

@Controller
public class InvoicesController{
	@Autowired 
	Invoice invoice;

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat();
	
	@RequestMapping("/invoices")
	protected ModelAndView invoices(HttpServletRequest request, HttpServletResponse response, Invoice invoice) {
		if (this.invoice != null){
			invoice = this.invoice;
		} else {
			invoice = new Invoice();
			this.invoice = invoice;
		}
		df.applyPattern("yyyy");
		if (invoice.getDateFrom() == null || invoice.getDateFrom().isEmpty()){
			invoice.setDateFrom("01/01/" + df.format(date));
		}
		if (invoice.getDateTo() == null || invoice.getDateTo().isEmpty()){
			invoice.setDateTo("31/12/" + df.format(date));
		}
		
		ModelAndView model = new ModelAndView("invoices");
		model.addObject("invoiceActive", "active");
		model.addObject("invoice", invoice);
		return model;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.POST, params = "search")
	protected ModelAndView calculate(HttpServletRequest request, HttpServletResponse response, @Valid Invoice invoice, BindingResult result) {
		ModelAndView model = new ModelAndView("invoices");
		SimpleDateFormat old_df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat new_df = new SimpleDateFormat("yyyy-MM-dd");
		model.addObject("invoiceActive", "active");
		Map<String, Object> parms = new HashMap<String, Object>();
		try {
			parms.put("dateFrom", new_df.format(old_df.parse(invoice.getDateFrom())));
			parms.put("dateTo", new_df.format(old_df.parse(invoice.getDateTo())));
		} catch (Exception e){
			System.out.println("Could not parse date");
		}
		
		if (invoice.getCustomer() != null && invoice.getCustomerId() != null){
			parms.put("customerId", invoice.getCustomer().getCustomerId());
		}

		if (invoice.getCompanyInfo() != null && invoice.getCompanyId() != null){
			parms.put("companyId", invoice.getCompanyInfo().getCompanyId());
		}
		List<Invoice> invoices = null;
		if (!checkErrors(model, result)){
			invoices = InvoiceDaoImpl.getInvoices(parms);
		}
		model.addObject("invoices", invoices);
		return model;
	}
	
	@RequestMapping("/invoices/company/{companyId}")
	protected ModelAndView invoiceCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.invoice.setCompanyId(companyId);
		try {
			this.invoice.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoices");
	}
	
	@RequestMapping("/invoices/customer/{customerId}")
	protected ModelAndView invoiceCustomer(HttpServletRequest request, HttpServletResponse response, @PathVariable int customerId) {
		this.invoice.setCustomerId(customerId);
		try {
			this.invoice.setCustomer(CustomerDaoImpl.getCustomer(customerId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoices");
	}
	
	private boolean checkErrors(ModelAndView model, BindingResult result){
		boolean hasErrors = false;
		if (result.hasFieldErrors("dateFrom")){
			model.addObject("dateFromError", "has-error");
			hasErrors = true;
		}
		if (result.hasFieldErrors("dateTo")){
			model.addObject("dateToError", "has-error");
			hasErrors = true;
		}
		return hasErrors;
	}
	
//	@RequestMapping(value = "/suppliers", method = RequestMethod.POST)
//	protected ModelAndView suppliers(Supplier supplier, BindingResult result) {
//		// TODO Auto-generated method stub
//		Map<String, String> parms = new HashMap<String, String>();
//		if (supplier.getSupplierName() != null && !supplier.getSupplierName().isEmpty()){
//			parms.put("supplierName", supplier.getSupplierName());
//		}
//		if (supplier.getSupplierAfm() != null && !supplier.getSupplierAfm().isEmpty()){
//			parms.put("supplierAfm", supplier.getSupplierAfm());
//		}
//		ModelAndView model = new ModelAndView("suppliers");
//		model.addObject("supplierActive", "active");
//
//		try {
//		List<Supplier> suppliers = SupplierDaoImpl.getSuppliers(parms);
//		model.addObject("suppliers", suppliers);
//		} catch (Exception e){
//			result.reject("query.fail", e.getMessage());
//			return model;
//		}
//		
//		return model;
//	}


}
