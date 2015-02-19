package com.aalexandrakis.mycrm.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="customers")
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customerId")
	private Integer customerId;
	@NotBlank @Column(name="customerName")
	private String customerName;
	@NotBlank @Column(name="customerBusDesc")
	private String customerBusDesc;
	@NotBlank @Column(name="customerDoy")
	private String customerDoy;
	@NotBlank @Column(name="customerAddress")
	private String customerAddress;
	@NotBlank @Column(name="customerAfm")
	private String customerAfm;
	@NotBlank @Column(name="customerPhone")
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
