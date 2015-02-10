package com.aalexandrakis.mycrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.CompanyInfo;
import com.aalexandrakis.mycrm.beans.Invoice;
import com.aalexandrakis.mycrm.beans.InvoiceLine;
import com.aalexandrakis.mycrm.dao.CompanyInfoDao;
import com.aalexandrakis.mycrm.dao.CustomerDao;

@Controller
public class InvoiceController{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@RequestMapping("/invoice")
	protected ModelAndView invoice(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Invoice invoice) {
		ModelAndView model = new ModelAndView("invoice");
		model.addObject("invoiceActive", "active");
		if (request.getSession().getAttribute("invoice") == null){
			List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
			for (int i=1 ; i < 6 ; i++){
				invoiceLines.add(new InvoiceLine(i));
			}
			invoice.setInvoiceLines(invoiceLines);
			request.getSession().setAttribute("invoice", invoice);
		} else {
			invoice = (Invoice) request.getSession().getAttribute("invoice"); 
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoice");
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.POST)
	protected ModelAndView invoice(CompanyInfo companyInfo, BindingResult result) {
		Map<String, String> parms = new HashMap<String, String>();
		if (companyInfo.getName() != null && !companyInfo.getName().isEmpty()){
			parms.put("name", companyInfo.getName());
		}
		if (companyInfo.getAfm() != null && !companyInfo.getAfm().isEmpty()){
			parms.put("afm", companyInfo.getAfm());
		}
		ModelAndView model = new ModelAndView("companiesInfo");
		model.addObject("invoiceActive", "active");
		
		try {
			List<CompanyInfo> companiesInfo = CompanyInfoDao.getCompaniesInfo(parms);
			model.addObject("companiesInfo", companiesInfo);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		
		return model;
	}


}
