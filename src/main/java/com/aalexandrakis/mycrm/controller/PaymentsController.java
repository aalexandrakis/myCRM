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
import com.aalexandrakis.mycrm.daoimpl.PaymentDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrm.models.Payment;

@Controller
public class PaymentsController{
	@Autowired 
	Payment payment;

	Date date = new Date();
	SimpleDateFormat df = new SimpleDateFormat();
	
	@RequestMapping("/payments")
	protected ModelAndView payment(HttpServletRequest request, HttpServletResponse response, Payment payment) {
		if (this.payment != null){
			payment = this.payment;
		} else {
			payment = new Payment();
			this.payment = payment;
		}
		df.applyPattern("yyyy");
		if (payment.getDateFrom() == null || payment.getDateFrom().isEmpty()){
			payment.setDateFrom("01/01/" + df.format(date));
		}
		if (payment.getDateTo() == null || payment.getDateTo().isEmpty()){
			payment.setDateTo("31/12/" + df.format(date));
		}
		
		ModelAndView model = new ModelAndView("payments");
		model.addObject("paymentActive", "active");
		model.addObject("payment", payment);
		return model;
	}

	@RequestMapping(value = "/payments" , method = RequestMethod.POST, params = "downloadFiles")
	protected HttpServletResponse downloadFiles(HttpServletRequest request, HttpServletResponse response,  @Valid Payment payment, BindingResult result) throws IOException {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition","inline; filename=payments.zip;");
			ServletOutputStream outputStream = response.getOutputStream();
			
			try {
				List<Payment> payments  = PaymentDaoImpl.getPayments(getQueryParams());
				ZipOutputStream zip = new ZipOutputStream(outputStream);
				for (Payment paymentObject : payments){
					zip.putNextEntry(new ZipEntry(paymentObject.getPaymentFileName()));
					byte[] b = new byte[1024];
					int len;
					InputStream inputStream = paymentObject.getPaymentFile().getBinaryStream();
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
	

	@RequestMapping(value = "/payments", method = RequestMethod.POST, params = "print")
	protected ModelAndView  paymentPdf(HttpServletRequest request, HttpServletResponse respose, Payment payment) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		ModelMap parms = new ModelMap();
		parms.put("REPORT_CONNECTION", Methods.getConnection(System.getenv("MYCRM_DB_USERNAME"),System.getenv("MYCRM_DB_PASSWORD")));
	    parms.put("dateFrom", df.parseObject(payment.getDateFrom()));
	    parms.put("dateTo", df.parseObject(payment.getDateTo()));
		return new ModelAndView("pdfPaymentsView", parms);
	}
	
	@RequestMapping(value = "/payments", method = RequestMethod.POST, params = "search")
	protected ModelAndView search(HttpServletRequest request, HttpServletResponse response, @Valid Payment payment, BindingResult result) {
		ModelAndView model = new ModelAndView("payments");
		model.addObject("paymentActive", "active");
		List<Payment> payments = null;
		if (!checkErrors(model, result)){
			payments = PaymentDaoImpl.getPayments(getQueryParams());
			BigDecimal amountSummary = BigDecimal.ZERO;
			for (Payment paymc : payments){
				amountSummary = amountSummary.add(paymc.getAmount());
			}
			model.addObject("amountSummary", amountSummary);
		}
		
		model.addObject("payments", payments);
		return model;
	}

	@RequestMapping(value = "/payments", method = RequestMethod.POST, params = "clear")
	protected ModelAndView clearFilters(HttpServletRequest request, HttpServletResponse response, @Valid Payment payment, BindingResult result) {
		ModelAndView model = new ModelAndView("payments");
		model.addObject("paymentActive", "active");
		payment.setCompanyInfo(null);
		payment.setSupplier(null);
		model.addObject("payment", payment);
		model.addObject("payments", null);
		return model;
	}

	@RequestMapping("/payments/company/{companyId}")
	protected ModelAndView paymentCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.payment.setCompanyId(companyId);
		try {
			this.payment.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/payments");
	}
	
	@RequestMapping("/payments/supplier/{supplierId}")
	protected ModelAndView invoiceCustomer(HttpServletRequest request, HttpServletResponse response, @PathVariable int supplierId) {
		this.payment.setSupplierId(supplierId);
		try {
			this.payment.setSupplier(SupplierDaoImpl.getSupplier(supplierId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/payments");
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
	
	private HashMap<String, Object> getQueryParams(){
		SimpleDateFormat old_df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat new_df = new SimpleDateFormat("yyyy-MM-dd");

		HashMap<String, Object> parms = new HashMap<String, Object>();
		try {
			parms.put("dateFrom", new_df.format(old_df.parse(payment.getDateFrom())));
			parms.put("dateTo", new_df.format(old_df.parse(payment.getDateTo())));
		} catch (Exception e){
			System.out.println("Could not parse date");
		}
		
		if (payment.getSupplier().getSupplierId() != null ){
			parms.put("supplierId", payment.getSupplier().getSupplierId());
		}

		if (payment.getCompanyInfo().getCompanyId() != null){
			parms.put("companyId", payment.getCompanyInfo().getCompanyId());
		}
		return parms;
	}
}
