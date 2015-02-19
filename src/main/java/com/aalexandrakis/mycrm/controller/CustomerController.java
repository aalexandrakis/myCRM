package com.aalexandrakis.mycrm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrm.models.Customer;

@Controller
public class CustomerController{
	@Autowired
	private ApplicationContext appContext;
	
	@RequestMapping("/customer")
	protected ModelAndView customer(Customer customer) {
		ModelAndView model = new ModelAndView("customer");
		model.addObject("customerActive", "active");
		return model;
	}

	@RequestMapping("/customer/{customerId}")
	protected ModelAndView customer(Customer customer , @PathVariable Integer customerId, BindingResult result) {
		ModelAndView model = new ModelAndView("customer");
		model.addObject("customerActive", "active");
		try {
			customer = CustomerDaoImpl.getCustomer(customerId);
			model.addObject("customer", customer);
		} catch (Exception e) {
			result.reject(e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	protected ModelAndView customer(@Valid Customer customer, BindingResult result) {
		
		ModelAndView model = new ModelAndView("customer");
		model.addObject("customerActive", "active");
		if (result.hasFieldErrors("customerName")){
			model.addObject("customerNameError", "has-error");
		}
		if (result.hasFieldErrors("customerBusDesc")){
			model.addObject("customerBusDescError", "has-error");
		}
		if (result.hasFieldErrors("customerPhone")){
			model.addObject("customerPhoneError", "has-error");
		}
		if (result.hasFieldErrors("customerAfm")){
			model.addObject("customerAfmError", "has-error");
		}
		if (result.hasFieldErrors("customerDoy")){
			model.addObject("customerDoyError", "has-error");
		}
		if (result.hasFieldErrors("customerAddress")){
			model.addObject("customerAddressError", "has-error");
		}
		
		if (result.hasErrors()){
			return model;
		}
		
		try {
			if (customer.getCustomerId() == null){
				CustomerDaoImpl.saveCustomer(customer);
				model.addObject("successMessage", "Customer added successfully");
			} else {
				CustomerDaoImpl.updateCustomer(customer);
				model.addObject("successMessage", "Customer updated successfully");
			}
		} catch (Exception e) {
			result.reject("saveError", e.getMessage());
		}
		
		if (result.hasErrors()){
			return model;
		} else {
			return new ModelAndView("redirect:customers");
		}
		
	}
}
