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
@Table(name="suppliers")

public class Supplier implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="supplierId")
	private Integer supplierId;
	@NotBlank @Column(name="supplierName")
	private String supplierName;
	@NotBlank @Column(name="supplierBusDesc")
	private String supplierBusDesc;
	@NotBlank @Column(name="supplierDoy")
	private String supplierDoy;
	@NotBlank @Column(name="supplierAddress")
	private String supplierAddress;
	@NotBlank @Column(name="supplierAfm")
	private String supplierAfm;
	@NotBlank @Column(name="supplierPhone")
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
