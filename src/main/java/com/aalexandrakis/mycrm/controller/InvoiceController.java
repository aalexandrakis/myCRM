package com.aalexandrakis.mycrm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.Invoice;
import com.aalexandrakis.mycrm.dao.CompanyInfoDao;
import com.aalexandrakis.mycrm.dao.CustomerDao;

@Controller
public class InvoiceController{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@RequestMapping("/invoice")
	protected ModelAndView invoiceGet(HttpServletRequest request, HttpServletResponse response, Invoice invoice) {
		ModelAndView model = new ModelAndView("invoice");
		model.addObject("invoiceActive", "active");
//		if (request.getSession().getAttribute("invoice") == null
//				|| ((Invoice) request.getSession().getAttribute("invoice")).getInvoiceLines().size()==0){
//			List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
//			for (int i=1 ; i < 6 ; i++){
//				invoiceLines.add(new InvoiceLine(i));
//			}
//			invoice.setInvoiceLines(invoiceLines);
//			request.getSession().setAttribute("invoice", invoice);
//		} else {
//			invoice = (Invoice) request.getSession().getAttribute("invoice"); 
//		}
		if (request.getSession().getAttribute("invoice") != null){
			invoice = (Invoice) request.getSession().getAttribute("invoice");
		} else {
			invoice = new Invoice();
			request.getSession().setAttribute("invoice", invoice);
		}
		model.addObject("invoice", invoice);
		return model;
	}

	@RequestMapping("/invoice/company/{companyId}")
	protected ModelAndView invoiceCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		((Invoice) request.getSession().getAttribute("invoice")).setCompanyId(companyId);
		try {
			((Invoice) request.getSession().getAttribute("invoice")).setCompanyInfo(CompanyInfoDao.getCompanyInfo(companyId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoice");
	}
	
	@RequestMapping("/invoice/customer/{customerId}")
	protected ModelAndView invoiceCustomer(HttpServletRequest request, HttpServletResponse response, @PathVariable int customerId) {
		((Invoice) request.getSession().getAttribute("invoice")).setCustomerId(customerId);
		try {
			((Invoice) request.getSession().getAttribute("invoice")).setCustomer(CustomerDao.getCustomer(customerId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoice");
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "preview")
	protected ModelAndView invoicePost(@Valid Invoice invoice, BindingResult result) {
		ModelAndView model = new ModelAndView("invoice");
		model.addObject("invoiceActive", "active");
		if (result.hasFieldErrors("companyId")){
			model.addObject("companyError", "has-error");
		}
		if (result.hasFieldErrors("customerId")){
			model.addObject("customerError", "has-error");
		}
		if (result.hasFieldErrors("fpa")){
			model.addObject("fpaError", "has-error");
		}
		if (result.hasFieldErrors("withHolding")){
			model.addObject("withHoldingError", "has-error");
		}
		model.addObject("invoice", invoice);
		
		return model;
	}
	
	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "addNewLine")
	protected ModelAndView addNewLine(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("invoice") Invoice invoice){
		invoice.addNewLine();
		request.getSession().setAttribute("invoice", invoice);
		return new ModelAndView("redirect:/invoice");
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "removeLine")
	protected ModelAndView removeLine(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("invoice") Invoice invoice, @RequestParam("removeLine") String lineId){
		invoice.removeLine(Integer.valueOf(lineId));
		request.getSession().setAttribute("invoice", invoice);
		return new ModelAndView("redirect:/invoice");
	}
}
