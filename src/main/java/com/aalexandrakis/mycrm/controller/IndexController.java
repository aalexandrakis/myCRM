package com.aalexandrakis.mycrm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController{

	@RequestMapping("/index")
	protected ModelAndView index() {
		// TODO Auto-generated method stub
		ModelAndView model = new ModelAndView("index");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getName());
//		System.out.println(authentication.getCredentials());
		return model;
	}


}
