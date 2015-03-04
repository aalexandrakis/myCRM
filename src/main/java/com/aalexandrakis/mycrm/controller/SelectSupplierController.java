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

import com.aalexandrakis.mycrm.daoimpl.SupplierDaoImpl;
import com.aalexandrakis.mycrm.models.Supplier;

@Controller
public class SelectSupplierController{

	@RequestMapping("/selectSupplier/{target}")
	protected ModelAndView customers(Supplier supplier, @PathVariable String target) {
		ModelAndView model = new ModelAndView("selectSupplier");
		model.addObject("outcomeActive", "active");
		try {
		List<Supplier> suppliers = SupplierDaoImpl.getSuppliers(null);
		model.addObject("suppliers", suppliers);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/selectSupplier/{target}", method = RequestMethod.POST)
	protected ModelAndView customers(Supplier supplier, @PathVariable String target, BindingResult result) {
		Map<String, String> parms = new HashMap<String, String>();
		if (supplier.getSupplierName() != null && !supplier.getSupplierName().isEmpty()){
			parms.put("supplierName", supplier.getSupplierName());
		}
		if (supplier.getSupplierAfm() != null && !supplier.getSupplierAfm().isEmpty()){
			parms.put("supplierAfm", supplier.getSupplierAfm());
		}
		ModelAndView model = new ModelAndView("selectSupplier");
		model.addObject("outcomeActive", "active");
		
		try {
			List<Supplier> suppliers = SupplierDaoImpl.getSuppliers(parms);
			model.addObject("suppliers", suppliers);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		
		return model;
	}


}
