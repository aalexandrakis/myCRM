package com.aalexandrakis.mycrm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.daoimpl.CompanyInfoDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.PaymentDaoImpl;
import com.aalexandrakis.mycrm.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrm.models.Payment;
@Controller
public class PaymentController{
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	Payment payment;
	
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields(new String[]{"dateFrom", "dateTo"});
		binder.registerCustomEditor(Date.class,  "paymentDate", new CustomDateEditor(df, false));
	}
	

	@RequestMapping(value = "/payment", method = RequestMethod.POST, params = "save")
	protected ModelAndView paymentPost(@Valid Payment payment, @RequestParam("file") MultipartFile file, BindingResult result) {
		ModelAndView model = new ModelAndView("paymentForm");
		model.addObject("paymentActive", "active");
		model.addObject("readOnly", "false");
		checkErrors(model, result);
		if (!result.hasErrors()){
			try {
				payment = PaymentDaoImpl.savePayment(payment, file);
				model.addObject("payment", payment);
				return model;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				result.reject(e.getMessage());
				model.addObject("payment", payment);
				return model;
			}
		} else {
			model.addObject("payment", payment);
			return model;
		}
	}
	

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	protected ModelAndView paymentGet(HttpServletRequest request, HttpServletResponse response, Payment payment) {
//		System.out.println(invoice.getInvoiceDate());
		ModelAndView model = new ModelAndView("paymentForm");
		model.addObject("readOnly", "false");
		model.addObject("paymentActive", "active");
		if (this.payment != null){
			payment = this.payment;
		} else {
			payment = new Payment();
			this.payment = payment;
		}
		model.addObject("payment", payment);
		return model;
	}

	@RequestMapping(value = "/payment/new", method = RequestMethod.GET)
	protected ModelAndView newPayment(HttpServletRequest request, HttpServletResponse response, Payment payment) {
		ModelAndView model = new ModelAndView("paymentForm");
		model.addObject("readOnly", "false");
		model.addObject("paymentActive", "active");
		payment = new Payment();
		this.payment = payment;
		model.addObject("payment", payment);
		return model;
	}

	@RequestMapping("/payment/company/{companyId}")
	protected ModelAndView paymentCompany(HttpServletRequest request, HttpServletResponse response,  @PathVariable int companyId) {
		this.payment.setCompanyId(companyId);
		try {
			this.payment.setCompanyInfo(CompanyInfoDaoImpl.getCompanyInfo(companyId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/payment");
	}
	
	@RequestMapping("/payment/supplier/{supplierId}")
	protected ModelAndView paymentSupplier(HttpServletRequest request, HttpServletResponse response, @PathVariable int supplierId) {
		this.payment.setSupplierId(supplierId);
		try {
			this.payment.setSupplier(SupplierDaoImpl.getSupplier(supplierId));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/payment");
	}

	@RequestMapping("/payment/{paymentId}")
	protected ModelAndView paymentById(HttpServletRequest request, HttpServletResponse response,  @PathVariable Integer paymentId) {
			try {
				this.payment = PaymentDaoImpl.getPayment(paymentId);
			} catch (Exception e){
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView("paymentForm");
			model.addObject("payment", payment);
			return model;
		}
	

	@RequestMapping(value = "/paymentPdf/{paymentId}" , method = RequestMethod.GET)
	protected HttpServletResponse paymentPdf(HttpServletRequest request, HttpServletResponse response,  @PathVariable Integer paymentId) throws IOException {
			try {
				this.payment = PaymentDaoImpl.getPayment(paymentId);
				response.setContentType(payment.getPaymentFileType());
				IOUtils.copy(payment.getPaymentFile().getBinaryStream(), response.getOutputStream());
				response.getOutputStream().close();
				return response;
			} catch (Exception e){
				e.printStackTrace();
				response.sendError(500, e.getMessage());
				return response;
			}
	}
	
	private void checkErrors(ModelAndView model, BindingResult result){
		if (result.hasFieldErrors("companyId")){
			model.addObject("companyError", "has-error");
		}
		if (result.hasFieldErrors("supplierId")){
			model.addObject("supplierError", "has-error");
		}
		if (result.hasFieldErrors("paymentDate")){
			model.addObject("paymentDateError", "has-error");
		}
		if (result.hasFieldErrors("paymentNumber")){
			model.addObject("paymentAmountError", "has-error");
		}

	}
	
}
