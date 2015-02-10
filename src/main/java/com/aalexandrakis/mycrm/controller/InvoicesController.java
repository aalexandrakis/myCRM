package com.aalexandrakis.mycrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.CompanyInfo;
import com.aalexandrakis.mycrm.beans.Invoice;
import com.aalexandrakis.mycrm.beans.InvoiceLine;
import com.aalexandrakis.mycrm.dao.CompanyInfoDao;

@Controller
public class InvoicesController{

	@RequestMapping("/invoices")
	protected ModelAndView companiesInfo(@ModelAttribute Invoice invoice) {
		ModelAndView model = new ModelAndView("invoices");
		model.addObject("invoiceActive", "active");
		List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
		for (int i=1 ; i < 6 ; i++){
			invoiceLines.add(new InvoiceLine(i));
		}
		invoice.setInvoiceLines(invoiceLines);
		model.addObject("invoice", invoice);
//		try {
//			List<CompanyInfo> companiesInfo = CompanyInfoDao.getCompaniesInfo(null);
//			model.addObject("companiesInfo", companiesInfo);
//		} catch (Exception e){
//			System.out.println(e.getMessage());
//			return model;
//		}

		return model;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.POST)
	protected ModelAndView customers(CompanyInfo companyInfo, BindingResult result) {
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
