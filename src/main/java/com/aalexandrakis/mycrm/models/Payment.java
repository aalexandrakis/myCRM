package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.aalexandrakis.mycrm.validators.ValidDate;
@Entity
@Table(name="payments")
public class Payment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="paymentId")
	private Integer paymentId;

	@NotNull @Min(1) @Column(name="companyId")
	private Integer companyId;
	
	@Transient
	private CompanyInfo companyInfo;
	
	@NotNull @Min(1) @Column(name="supplierId")
	private Integer supplierId;
	
	@Transient
	private Supplier supplier;
	 
	@Column(name="amount") @NumberFormat(style=Style.CURRENCY)
	private BigDecimal amount;
	
	@ValidDate(format="dd/MM/yyyy") @DateTimeFormat(pattern="dd/MM/yyyy") @Column(name="paymentDate")
	private Date paymentDate;
	
	@Column(name="paymentFile")
	@Lob
	private java.sql.Blob paymentFile;
	
	@Transient
	private String paymentDateString;
	
	@Transient 
	private String dateFrom;
	
	@Transient 
	private String dateTo;
	
	@Column(name="paymentFileName")
	private String paymentFileName;
	
	@Column(name="paymentFileType")
	private String paymentFileType;
	
	public Payment(){
	}


	
	public String getPaymentDateString() {
		SimpleDateFormat new_df = new SimpleDateFormat("dd-MM-yyyy");
		this.paymentDateString = new_df.format(this.paymentDate);
		return this.paymentDateString;
	}

	public void setPaymentDateString(String paymentDateString) {
		this.paymentDateString = paymentDateString;
	}

	
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	
	public java.sql.Blob getPaymentFile() {
		return paymentFile;
	}

	public void setPaymentFile(java.sql.Blob paymentFile) {
		this.paymentFile = paymentFile;
	}



	public Integer getPaymentId() {
		return paymentId;
	}



	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}



	public Integer getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}



	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}



	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}



	public Integer getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}



	public Supplier getSupplier() {
		return supplier;
	}



	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public Date getPaymentDate() {
		return paymentDate;
	}



	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	public String getPaymentFileName() {
		return paymentFileName;
	}



	public void setPaymentFileName(String paymentFileName) {
		this.paymentFileName = paymentFileName;
	}



	public String getPaymentFileType() {
		return paymentFileType;
	}



	public void setPaymentFileType(String paymentFileType) {
		this.paymentFileType = paymentFileType;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
