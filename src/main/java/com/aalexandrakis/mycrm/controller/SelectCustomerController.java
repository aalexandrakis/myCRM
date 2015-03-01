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

import com.aalexandrakis.mycrm.daoimpl.CustomerDaoImpl;
import com.aalexandrakis.mycrm.models.Customer;

@Controller
public class SelectCustomerController{

	@RequestMapping("/selectCustomer/{target}")
	protected ModelAndView customers(Customer customer, @PathVariable String target) {
		ModelAndView model = new ModelAndView("selectCustomer");
		model.addObject("invoiceActive", "active");
		try {
		List<Customer> customers = CustomerDaoImpl.getCustomers(null);
		model.addObject("customers", customers);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/selectCustomer/{target}", method = RequestMethod.POST)
	protected ModelAndView customers(Customer customer, @PathVariable String target, BindingResult result) {
		Map<String, String> parms = new HashMap<String, String>();
		if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty()){
			parms.put("customerName", customer.getCustomerName());
		}
		if (customer.getCustomerAfm() != null && !customer.getCustomerAfm().isEmpty()){
			parms.put("customerAfm", customer.getCustomerAfm());
		}
		ModelAndView model = new ModelAndView("selectCustomer");
		model.addObject("invoiceActive", "active");
		
		try {
			List<Customer> customers = CustomerDaoImpl.getCustomers(parms);
			model.addObject("customers", customers);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		
		return model;
	}


}
