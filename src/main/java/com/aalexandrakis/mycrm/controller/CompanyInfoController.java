package com.aalexandrakis.mycrm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.CompanyInfo;
import com.aalexandrakis.mycrm.dao.CompanyInfoDao;

@Controller
public class CompanyInfoController{
	@Autowired
	private ApplicationContext appContext;

	@RequestMapping("/companyInfo")
	protected ModelAndView companyInfo(CompanyInfo companyInfo) {
		ModelAndView model = new ModelAndView("companyInfo");
		try {
			companyInfo = CompanyInfoDao.getCompanyInfo();
			model.addObject("companyInfo", companyInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/companyInfo", method = RequestMethod.POST)
	protected ModelAndView companyInfo(@Valid CompanyInfo companyInfo, BindingResult result) {
		ModelAndView model = new ModelAndView("companyInfo");
		if (result.hasFieldErrors("name")){
			model.addObject("nameError", "has-error");
		}
		if (result.hasFieldErrors("busDesc")){
			model.addObject("busDescError", "has-error");
		}
		if (result.hasFieldErrors("address")){
			model.addObject("addressError", "has-error");
		}
		if (result.hasFieldErrors("afm")){
			model.addObject("afmError", "has-error");
		}
		if (result.hasFieldErrors("doy")){
			model.addObject("doyError", "has-error");
		}
		if (result.hasFieldErrors("mobilePhone")){
			model.addObject("mobilePhoneError", "has-error");
		}
		if (result.hasFieldErrors("workPhone")){
			model.addObject("workPhoneError", "has-error");
		}
		if (result.hasFieldErrors("email")){
			model.addObject("emailError", "has-error");
		}
		if (result.hasErrors()){
			return model;
		}
		try {
			CompanyInfoDao.saveOrUpdateCompanyInfo(companyInfo);
			model.addObject("successMessage", "Company info added/updated successfully");
		} catch (Exception e) {
			result.reject("saveError", e.getMessage());
		}
		return model;
	}
}
