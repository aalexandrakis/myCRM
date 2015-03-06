package com.aalexandrakis.mycrm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.InvoiceDaoImpl;
import com.aalexandrakis.mycrm.models.Invoice;
@Controller
public class InvoiceController{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	Invoice invoice;
	
	@Autowired
	ServletContext servletContext;
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields(new String[]{"dateFrom", "dateTo"});
		binder.registerCustomEditor(Date.class,  "invoiceDate", new CustomDateEditor(df, false));
	}
	
	
	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "saveAndPrint")
	protected ModelAndView invoicePost(@Valid Invoice invoice, BindingResult result) {
		ModelAndView model = new ModelAndView("invoiceForm");
		model.addObject("invoiceActive", "active");
		model.addObject("readOnly", "false");
		checkErrors(model, result);
		invoice.calculate();
		if (!result.hasErrors()){
			try {
				InvoiceDaoImpl.saveInvoice(invoice);
				ModelMap parms = new ModelMap();
			    parms.put("invoiceId", invoice.getInvoiceId().toString());
		        
			    JasperReport jasperReport = JasperCompileManager.compileReport(servletContext.getRealPath("/WEB-INF/reports/invoicePdf.jrxml"));
	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parms, Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
	            JasperExportManager.exportReportToPdfStream(jasperPrint, os);
	            os.close();
				InvoiceDaoImpl.saveInvoice(invoice, os);
	            return new ModelAndView("redirect:/invoicePdf/" + invoice.getInvoiceId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				result.reject(e.getMessage());
				model.addObject("invoice", invoice);
				return model;
			}
		} else {
			model.addObject("invoice", invoice);
			return model;
		}
	}
	
	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "calculate")
	protected ModelAndView calculate(@Valid Invoice invoice, BindingResult result) {
		ModelAndView model = new ModelAndView("invoiceForm");
		
		model.addObject("invoiceActive", "active");
		checkErrors(model, result);
		invoice.calculate();
		model.addObject("invoice", invoice);
		model.addObject("readOnly", "false");
		return model;
	}
	

	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "addNewLine")
	protected ModelAndView addNewLine(HttpServletRequest request, HttpServletResponse response, Invoice invoice, BindingResult result){
		ModelAndView model = new ModelAndView("invoiceForm");
		
		model.addObject("invoiceActive", "active");
		checkErrors(model, result);
		invoice.addNewLine();
		model.addObject("invoice", invoice);
		model.addObject("readOnly", "false");
		return model;
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.POST, params = "removeLine")
	protected ModelAndView removeLine(HttpServletRequest request, HttpServletResponse response, Invoice invoice, @RequestParam("removeLine") String lineId, BindingResult result){
		ModelAndView model = new ModelAndView("invoiceForm");
		
		model.addObject("invoiceActive", "active");
		checkErrors(model, result);
		invoice.removeLine(Integer.valueOf(lineId));
		model.addObject("invoice", invoice);
		model.addObject("readOnly", "false");
		return model;
	}

	@RequestMapping(value = "/invoice", method = RequestMethod.GET)
	protected ModelAndView invoiceGet(HttpServletRequest request, HttpServletResponse response, Invoice invoice) {
//		System.out.println(invoice.getInvoiceDate());
		ModelAndView model = new ModelAndView("invoiceForm");
		model.addObject("readOnly", "false");
		model.addObject("invoiceActive", "active");
		if (this.invoice != null){
			invoice = this.invoice;
		} else {
			invoice = new Invoice();
			this.invoice = invoice;
		}
		model.addObject("invoice", invoice);
		return model;
	}

	@RequestMapping(value = "/invoice/new", method = RequestMethod.GET)
	protected ModelAndView invoiceNew(HttpServletRequest request, HttpServletResponse response, Invoice invoice) {
//		System.out.println(invoice.getInvoiceDate());
		ModelAndView model = new ModelAndView("invoiceForm");
		model.addObject("readOnly", "false");
		model.addObject("invoiceActive", "active");
		invoice = new Invoice();
		this.invoice = invoice;
		model.addObject("invoice", invoice);
		return model;
	}


	@RequestMapping("/invoice/company/{companyId}")
	protected ModelAndView invoiceCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.invoice.setCompanyId(companyId);
		try {
			this.invoice.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoice");
	}
	
	@RequestMapping("/invoice/customer/{customerId}")
	protected ModelAndView invoiceCustomer(HttpServletRequest request, HttpServletResponse response, @PathVariable int customerId) {
		this.invoice.setCustomerId(customerId);
		try {
			this.invoice.setCustomer(CustomerDaoImpl.getCustomer(customerId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/invoice");
	}

	private void checkErrors(ModelAndView model, BindingResult result){
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
		if (result.hasFieldErrors("invoiceDate")){
			model.addObject("invoiceDateError", "has-error");
		}

	}
	
	@RequestMapping(value = "/invoicePdf/{invoiceId}" , method = RequestMethod.GET)
	protected HttpServletResponse outcomePdf(HttpServletRequest request, HttpServletResponse response,  @PathVariable Integer invoiceId) throws IOException {
			try {
				this.invoice = InvoiceDaoImpl.getInvoice(invoiceId);
				response.setContentType("application/pdf");
				IOUtils.copy(invoice.getInvoiceFile().getBinaryStream(), response.getOutputStream());
				response.getOutputStream().close();
				return response;
			} catch (Exception e){
				e.printStackTrace();
				response.sendError(500, e.getMessage());
				return response;
			}
	}
//	@RequestMapping(value="/invoice/{invoiceId}")
//	protected ModelAndView getInvoice(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer invoiceId){
//		ModelAndView model = new ModelAndView("invoice");
//		Invoice invoice = null;
//		try {
//			invoice = InvoiceDaoImpl.getInvoice(invoiceId);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		model.addObject("invoice", invoice);
//		model.addObject("readOnly", "true");
//		return model;
//	}
	


}
