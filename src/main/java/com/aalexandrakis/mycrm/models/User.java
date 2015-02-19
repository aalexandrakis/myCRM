package com.aalexandrakis.mycrm.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class User {
	
	@NotBlank
	private String j_username;
	
	@NotBlank
	private String j_password;

	private List<String> roles;
	
	public User() {
		super();
	}

	public User(String j_username, String j_password) {
		super();
		this.j_username = j_username;
		this.j_password = j_password;
	}

	
	
	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role){
		if (this.roles == null){
			this.roles = new ArrayList<String>();
		}
		this.roles.add(role);
	}
	
}
