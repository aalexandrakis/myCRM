package com.aalexandrakis.mycrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.Customer;
import com.aalexandrakis.mycrm.dao.CustomerDao;

@Controller
public class CustomersController{

	@RequestMapping("/customers")
	protected ModelAndView customers(Customer customer) {
		ModelAndView model = new ModelAndView("customers");
		model.addObject("customerActive", "active");
		try {
		List<Customer> customers = CustomerDao.getCustomers(null);
		model.addObject("customers", customers);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	protected ModelAndView customers(Customer customer, BindingResult result) {
		Map<String, String> parms = new HashMap<String, String>();
		if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty()){
			parms.put("customerName", customer.getCustomerName());
		}
		if (customer.getCustomerAfm() != null && !customer.getCustomerAfm().isEmpty()){
			parms.put("customerAfm", customer.getCustomerAfm());
		}
		ModelAndView model = new ModelAndView("customers");
		model.addObject("customerActive", "active");
		
		try {
		List<Customer> customers = CustomerDao.getCustomers(parms);
		model.addObject("customers", customers);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		
		return model;
	}


}
