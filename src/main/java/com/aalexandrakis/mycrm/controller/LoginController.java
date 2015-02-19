package com.aalexandrakis.mycrm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aalexandrakis.mycrm.daoimpl.UserDaoImpl;
import com.aalexandrakis.mycrm.models.User;

@Controller
public class LoginController{
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@RequestMapping("/login")
	public String login(User user){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	protected String login(HttpServletRequest request, HttpServletResponse response, @Valid User user, BindingResult result) {
		if (result.hasErrors()){
			return "login";
		}
		try {
			user = UserDaoImpl.login(user.getJ_username(), user.getJ_password());
		} catch (Exception ex){
			result.reject("login.fail", "Δεν έγινε ταυτοποίηση. Παρακαλώ ελέγξτε τα στοιχεία σας και προσπαθήστε ξανά.");
			return "login";
		}
		
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getJ_username(), user.getJ_password(),
					AuthorityUtils.createAuthorityList(user.getRoles().toArray(new String[user.getRoles().size()])));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			successHandler.onAuthenticationSuccess(request, response, authentication);
		} catch (NullPointerException e) {
			result.reject("login.fail", "Αυτή τη στιγμή υπάρχει πρόβλημα. Προσπαθήστε αργότερα.");
			System.out.println(e.getMessage());
			return "login";
		} catch (IOException e) {
			result.reject("login.fail", "Αυτή τη στιγμή υπάρχει πρόβλημα. Προσπαθήστε αργότερα.");
			System.out.println(e.getMessage());
			return "login";
		} catch (ServletException e) {
			result.reject("login.fail", "Αυτή τη στιγμή υπάρχει πρόβλημα. Προσπαθήστε αργότερα.");
			System.out.println(e.getMessage());
			return "login";
		}
		return null;

	}
}
