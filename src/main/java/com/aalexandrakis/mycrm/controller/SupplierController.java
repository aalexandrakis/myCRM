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

import com.aalexandrakis.mycrm.beans.Supplier;
import com.aalexandrakis.mycrm.dao.SupplierDao;

@Controller
public class SupplierController{
	@Autowired
	private ApplicationContext appContext;

	@RequestMapping("/supplier")
	protected ModelAndView supplier(Supplier supplier) {
		ModelAndView model = new ModelAndView("supplier");
		return model;
	}

	@RequestMapping("/supplier/{supplierId}")
	protected ModelAndView supplier(Supplier supplier , @PathVariable Integer supplierId, BindingResult result) {
		ModelAndView model = new ModelAndView("supplier");
		try {
			supplier = SupplierDao.getSupplier(supplierId);
			model.addObject("supplier", supplier);
		} catch (Exception e) {
			result.reject(e.getMessage());
		}
		return model;
	}

	
	@RequestMapping(value = "/supplier", method = RequestMethod.POST)
	protected ModelAndView supplier(@Valid Supplier supplier, BindingResult result) {
		
		ModelAndView model = new ModelAndView("supplier");
		if (result.hasFieldErrors("supplierName")){
			model.addObject("supplierNameError", "has-error");
		}
		if (result.hasFieldErrors("supplierBusDesc")){
			model.addObject("supplierBusDescError", "has-error");
		}
		if (result.hasFieldErrors("supplierPhone")){
			model.addObject("supplierPhoneError", "has-error");
		}
		if (result.hasFieldErrors("supplierAfm")){
			model.addObject("supplierAfmError", "has-error");
		}
		if (result.hasFieldErrors("supplierDoy")){
			model.addObject("supplierDoyError", "has-error");
		}
		if (result.hasFieldErrors("supplierAddress")){
			model.addObject("supplierAddressError", "has-error");
		}
		
		if (result.hasErrors()){
			return model;
		}
		
		try {
			if (supplier.getSupplierId() == null){
				SupplierDao.saveSupplier(supplier);
				model.addObject("successMessage", "Supplier added successfully");
			} else {
				SupplierDao.updateSupplier(supplier);
				model.addObject("successMessage", "Supplier updated successfully");
			}
		} catch (Exception e) {
			result.reject("saveError", e.getMessage());
		}
		
		if (result.hasErrors()){
			return model;
		} else {
			return new ModelAndView("redirect:suppliers");
		}
		
	}
}
