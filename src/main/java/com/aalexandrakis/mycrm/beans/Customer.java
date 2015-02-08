package com.aalexandrakis.mycrm.beans;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@NotBlank
	private Integer customerId;
	@NotBlank
	private String customerName;
	@NotBlank
	private String customerBusDesc;
	@NotBlank
	private String customerDoy;
	@NotBlank
	private String customerAddress;
	@NotBlank
	private String customerAfm;
	@NotBlank
	private String customerPhone;
	
	public Customer() {
		super();
	}


	public Integer getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerBusDesc() {
		return customerBusDesc;
	}


	public void setCustomerBusDesc(String customerBusDesc) {
		this.customerBusDesc = customerBusDesc;
	}


	public String getCustomerDoy() {
		return customerDoy;
	}


	public void setCustomerDoy(String customerDoy) {
		this.customerDoy = customerDoy;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public String getCustomerAfm() {
		return customerAfm;
	}


	public void setCustomerAfm(String customerAfm) {
		this.customerAfm = customerAfm;
	}

	

	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
