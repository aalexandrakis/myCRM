package com.aalexandrakis.mycrm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.OutcomeDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrm.models.Outcome;
@Controller
public class OutcomeController{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	Outcome outcome;
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields(new String[]{"dateFrom", "dateTo"});
		binder.registerCustomEditor(Date.class,  "invoiceDate", new CustomDateEditor(df, false));
	}
	
	@RequestMapping(value = "/outcome", method = RequestMethod.POST, params = "calculate")
	protected ModelAndView calculate(HttpServletRequest request, HttpServletResponse response, Outcome outcome, BindingResult result){
		ModelAndView model = new ModelAndView("outcomeForm");
		
		model.addObject("outcomeActive", "active");
		checkErrors(model, result);
		outcome.calculate();
		model.addObject("outcome", outcome);
		model.addObject("readOnly", "false");
		return model;
	}

	@RequestMapping(value = "/outcome", method = RequestMethod.POST, params = "save")
	protected ModelAndView outcomePost(@Valid Outcome outcome, BindingResult result) {
		ModelAndView model = new ModelAndView("outcomeForm");
		model.addObject("outcomeActive", "active");
		model.addObject("readOnly", "false");
		checkErrors(model, result);
		if (!result.hasErrors()){
			try {
				outcome.calculate();
				OutcomeDaoImpl.saveOutcome(outcome);
				model.addObject("outcome", outcome);
				return model;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				result.reject(e.getMessage());
				model.addObject("outcome", outcome);
				return model;
			}
		} else {
			model.addObject("outcome", outcome);
			return model;
		}
	}
	

	@RequestMapping(value = "/outcome", method = RequestMethod.POST, params = "addNewLine")
	protected ModelAndView addNewLine(HttpServletRequest request, HttpServletResponse response, Outcome outcome, BindingResult result){
		ModelAndView model = new ModelAndView("outcomeForm");
		
		model.addObject("outcomeActive", "active");
		checkErrors(model, result);
		outcome.addNewLine();
		model.addObject("outcome", outcome);
		model.addObject("readOnly", "false");
		return model;
	}

	@RequestMapping(value = "/outcome", method = RequestMethod.POST, params = "removeLine")
	protected ModelAndView removeLine(HttpServletRequest request, HttpServletResponse response, Outcome outcome, @RequestParam("removeLine") String lineId, BindingResult result){
		ModelAndView model = new ModelAndView("outcomeForm");
		
		model.addObject("outcomeActive", "active");
		checkErrors(model, result);
		outcome.removeLine(Integer.valueOf(lineId));
		model.addObject("outcome", outcome);
		model.addObject("readOnly", "false");
		return model;
	}

	@RequestMapping(value = "/outcome", method = RequestMethod.GET)
	protected ModelAndView outcomeGet(HttpServletRequest request, HttpServletResponse response, Outcome outcome) {
//		System.out.println(invoice.getInvoiceDate());
		ModelAndView model = new ModelAndView("outcomeForm");
		model.addObject("readOnly", "false");
		model.addObject("outcomeActive", "active");
		if (this.outcome != null){
			outcome = this.outcome;
		} else {
			outcome = new Outcome();
			this.outcome = outcome;
		}
		model.addObject("outcome", outcome);
		return model;
	}

	@RequestMapping("/outcome/company/{companyId}")
	protected ModelAndView outcomeCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.outcome.setCompanyId(companyId);
		try {
			this.outcome.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/outcome");
	}
	
	@RequestMapping("/outcome/supplier/{supplierId}")
	protected ModelAndView outcomeSupplier(HttpServletRequest request, HttpServletResponse response, @PathVariable int supplierId) {
		this.outcome.setSupplierId(supplierId);
		try {
			this.outcome.setSupplier(SupplierDaoImpl.getSupplier(supplierId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/outcome");
	}

	private void checkErrors(ModelAndView model, BindingResult result){
		if (result.hasFieldErrors("companyId")){
			model.addObject("companyError", "has-error");
		}
		if (result.hasFieldErrors("supplierId")){
			model.addObject("supplierError", "has-error");
		}
		if (result.hasFieldErrors("outcomeDate")){
			model.addObject("outcomeDateError", "has-error");
		}
		if (result.hasFieldErrors("outcomeNumber")){
			model.addObject("outcomeNumberError", "has-error");
		}

	}
	
}
