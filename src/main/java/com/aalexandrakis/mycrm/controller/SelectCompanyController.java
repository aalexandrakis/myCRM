package com.aalexandrakis.mycrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.models.CompanyInfo;

@Controller
public class SelectCompanyController{

	@RequestMapping("/selectCompany/{target}")
	protected ModelAndView selectCompany(CompanyInfo companyInfo, @PathVariable String target ) {
		ModelAndView model = new ModelAndView("selectCompany");
		model.addObject("invoiceActive", "active");
		try {
		List<CompanyInfo> companiesInfo = CompanyInfoDaoImpl.getCompaniesInfo(null);
		model.addObject("companiesInfo", companiesInfo);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/selectCompany/{target}", method = RequestMethod.POST)
	protected ModelAndView customers(CompanyInfo companyInfo, @PathVariable String target, BindingResult result) {
		Map<String, String> parms = new HashMap<String, String>();
		if (companyInfo.getName() != null && !companyInfo.getName().isEmpty()){
			parms.put("name", companyInfo.getName());
		}
		if (companyInfo.getAfm() != null && !companyInfo.getAfm().isEmpty()){
			parms.put("afm", companyInfo.getAfm());
		}
		ModelAndView model = new ModelAndView("selectCompany");
		model.addObject("invoiceActive", "active");
		
		try {
		List<CompanyInfo> companiesInfo = CompanyInfoDaoImpl.getCompaniesInfo(parms);
		model.addObject("companiesInfo", companiesInfo);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		return model;
	}
}
