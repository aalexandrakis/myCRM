package com.aalexandrakis.mycrm.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="users")
public class User {
	
	@NotBlank @Id @Column(name="j_username")
	private String j_username;
	
	@NotBlank @Column(name="j_password")
	private String j_password;

	@Column(name="userEmail")
	private String email;
	
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Role> rolesList = new LinkedHashSet<Role>();
	
	
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
	
	public Collection<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(Collection<Role> roles) {
		this.rolesList = roles;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String[] getRolesArray(){
		List<String> roles = new ArrayList<String>();
		Iterator<Role> itr = this.rolesList.iterator();
		while(itr.hasNext()){
			roles.add(itr.next().getRole());
		}
		return roles.toArray(new String[roles.size()]);
	}
	
}
