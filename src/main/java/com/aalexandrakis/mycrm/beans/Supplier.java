package com.aalexandrakis.mycrm.beans;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class Supplier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@NotBlank
	private Integer supplierId;
	@NotBlank
	private String supplierName;
	@NotBlank
	private String supplierBusDesc;
	@NotBlank
	private String supplierDoy;
	@NotBlank
	private String supplierAddress;
	@NotBlank
	private String supplierAfm;
	@NotBlank
	private String supplierPhone;
	
	public Supplier() {
		super();
	}


	public Integer getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getSupplierBusDesc() {
		return supplierBusDesc;
	}


	public void setSupplierBusDesc(String supplierBusDesc) {
		this.supplierBusDesc = supplierBusDesc;
	}


	public String getSupplierDoy() {
		return supplierDoy;
	}


	public void setSupplierDoy(String supplierDoy) {
		this.supplierDoy = supplierDoy;
	}


	public String getSupplierAddress() {
		return supplierAddress;
	}


	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}


	public String getSupplierAfm() {
		return supplierAfm;
	}


	public void setSupplierAfm(String supplierAfm) {
		this.supplierAfm = supplierAfm;
	}

	

	public String getSupplierPhone() {
		return supplierPhone;
	}


	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
