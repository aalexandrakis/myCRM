package com.aalexandrakis.mycrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aalexandrakis.mycrm.beans.Supplier;
import com.aalexandrakis.mycrm.dao.SupplierDao;

@Controller
public class SuppliersController{
	
	@RequestMapping("/suppliers")
	protected ModelAndView suppliers(Supplier supplier) {
		ModelAndView model = new ModelAndView("suppliers");
		model.addObject("supplierActive", "active");

		try {
		List<Supplier> suppliers = SupplierDao.getSuppliers(null);
		model.addObject("suppliers", suppliers);
		} catch (Exception e){
			System.out.println(e.getMessage());
			return model;
		}

		return model;
	}

	@RequestMapping(value = "/suppliers", method = RequestMethod.POST)
	protected ModelAndView suppliers(Supplier supplier, BindingResult result) {
		// TODO Auto-generated method stub
		Map<String, String> parms = new HashMap<String, String>();
		if (supplier.getSupplierName() != null && !supplier.getSupplierName().isEmpty()){
			parms.put("supplierName", supplier.getSupplierName());
		}
		if (supplier.getSupplierAfm() != null && !supplier.getSupplierAfm().isEmpty()){
			parms.put("supplierAfm", supplier.getSupplierAfm());
		}
		ModelAndView model = new ModelAndView("suppliers");
		model.addObject("supplierActive", "active");

		try {
		List<Supplier> suppliers = SupplierDao.getSuppliers(parms);
		model.addObject("suppliers", suppliers);
		} catch (Exception e){
			result.reject("query.fail", e.getMessage());
			return model;
		}
		
		return model;
	}


}
