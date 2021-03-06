package com.aalexandrakis.mycrm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.OutcomeDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrm.models.Outcome;

@Controller
public class OutcomesController{
	@Autowired 
	Outcome outcome;

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat();
	
	@RequestMapping("/outcomes")
	protected ModelAndView outcome(HttpServletRequest request, HttpServletResponse response, Outcome outcome) {
		if (this.outcome != null){
			outcome = this.outcome;
		} else {
			outcome = new Outcome();
			this.outcome = outcome;
		}
		df.applyPattern("yyyy");
		if (outcome.getDateFrom() == null || outcome.getDateFrom().isEmpty()){
			outcome.setDateFrom("01/01/" + df.format(date));
		}
		if (outcome.getDateTo() == null || outcome.getDateTo().isEmpty()){
			outcome.setDateTo("31/12/" + df.format(date));
		}
		
		ModelAndView model = new ModelAndView("outcomes");
		model.addObject("outcomeActive", "active");
		model.addObject("outcome", outcome);
		return model;
	}

	@RequestMapping(value = "/outcomes" , method = RequestMethod.POST, params = "downloadFiles")
	protected HttpServletResponse downloadFiles(HttpServletRequest request, HttpServletResponse response,  @Valid Outcome outcome, BindingResult result) throws IOException {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition","inline; filename=outcomes.zip;");
			ServletOutputStream outputStream = response.getOutputStream();
			
			try {
				List<Outcome> outcomes  = OutcomeDaoImpl.getOutcomes(getQueryParams(outcome));
				ZipOutputStream zip = new ZipOutputStream(outputStream);
				for (Outcome outcomeObject : outcomes){
					zip.putNextEntry(new ZipEntry(outcomeObject.getFileName()));
					byte[] b = new byte[1024];
					int len;
					InputStream inputStream = outcomeObject.getOutcomeFile().getBinaryStream();
					while((len = inputStream.read(b)) != -1){
						zip.write(b, 0, len);
					}
					zip.closeEntry();
				}
				zip.flush();
				zip.close();

				outputStream.flush();
				return response;
			} catch (Exception e){
				e.printStackTrace();
				response.sendError(500, e.getMessage());
				return response;
			}
	}
	

	@RequestMapping(value = "/outcomes", method = RequestMethod.POST, params = "print")
	protected ModelAndView  outcomePdf(HttpServletRequest request, HttpServletResponse respose, Outcome outcome) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		ModelMap parms = new ModelMap();
		parms.put("REPORT_CONNECTION", Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
	    parms.put("dateFrom", df.parseObject(outcome.getDateFrom()));
	    parms.put("dateTo", df.parseObject(outcome.getDateTo()));
		return new ModelAndView("pdfOutcomesView", parms);
	}
	
	@RequestMapping(value = "/outcomes", method = RequestMethod.POST, params = "search")
	protected ModelAndView calculate(HttpServletRequest request, HttpServletResponse response, @Valid Outcome outcome, BindingResult result) {
		ModelAndView model = new ModelAndView("outcomes");
		model.addObject("outcomeActive", "active");
		List<Outcome> outcomes = null;
		if (!checkErrors(model, result)){
			outcomes = OutcomeDaoImpl.getOutcomes(getQueryParams(outcome));
			BigDecimal amountSummary = BigDecimal.ZERO;
			BigDecimal fpaAmountSummary = BigDecimal.ZERO;
			BigDecimal grossSummary = BigDecimal.ZERO;
			for (Outcome outc : outcomes){
				amountSummary = amountSummary.add(outc.getAmount());
				fpaAmountSummary = fpaAmountSummary.add(outc.getFpaAmount());
				grossSummary = grossSummary.add(outc.getGross());
			}
			model.addObject("amountSummary", amountSummary);
			model.addObject("fpaAmountSummary", fpaAmountSummary);
			model.addObject("grossSummary", grossSummary);
		}
		
		model.addObject("outcomes", outcomes);
		return model;
	}

	@RequestMapping(value = "/outcomes", method = RequestMethod.POST, params = "clear")
	protected ModelAndView clearFilters(HttpServletRequest request, HttpServletResponse response, @Valid Outcome outcome, BindingResult result) {
		ModelAndView model = new ModelAndView("outcomes");
		model.addObject("outcomeActive", "active");
		outcome.setCompanyInfo(null);
		outcome.setSupplier(null);
		model.addObject("outcome", outcome);
		model.addObject("outcomes", null);
		return model;
	}

	@RequestMapping("/outcomes/company/{companyId}")
	protected ModelAndView outcomeCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.outcome.setCompanyId(companyId);
		try {
			this.outcome.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/outcomes");
	}
	
	@RequestMapping("/outcomes/supplier/{supplierId}")
	protected ModelAndView invoiceCustomer(HttpServletRequest request, HttpServletResponse response, @PathVariable int supplierId) {
		this.outcome.setSupplierId(supplierId);
		try {
			this.outcome.setSupplier(SupplierDaoImpl.getSupplier(supplierId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/outcomes");
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
	
	private HashMap<String, Object> getQueryParams(Outcome outcome){
		SimpleDateFormat old_df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat new_df = new SimpleDateFormat("yyyy-MM-dd");

		HashMap<String, Object> parms = new HashMap<String, Object>();
		try {
			parms.put("dateFrom", new_df.format(old_df.parse(outcome.getDateFrom())));
			parms.put("dateTo", new_df.format(old_df.parse(outcome.getDateTo())));
		} catch (Exception e){
			System.out.println("Could not parse date");
		}
		
		if (outcome.getSupplier().getSupplierId() != null ){
			parms.put("supplierId", outcome.getSupplier().getSupplierId());
		}

		if (outcome.getCompanyInfo().getCompanyId() != null){
			parms.put("companyId", outcome.getCompanyInfo().getCompanyId());
		}
		return parms;
	}


}
