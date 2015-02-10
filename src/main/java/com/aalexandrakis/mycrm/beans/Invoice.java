package com.aalexandrakis.mycrm.beans;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public class Invoice {
	private Integer invoiceId;
	
	private int companyId;
	
	private CompanyInfo companyInfo;
	
	private int customerId;
	
	private Customer customer;
	
	@NotBlank @NumberFormat(pattern = "###,###,##0.00")
	private BigDecimal amount;

	@NotBlank @NumberFormat(pattern = "##0.00")
	private BigDecimal fpa;

	@NotBlank @NumberFormat(pattern = "###,###,##0.00")
	private BigDecimal taxis;

	@NotBlank @NumberFormat(pattern = "###,###,##0.00")
	private BigDecimal gross;

	private List<InvoiceLine> invoiceLines;
	
	@NotBlank @NumberFormat(pattern = "##0.00")
	private BigDecimal withHolding;
	
	public Invoice(){
		this.withHolding = BigDecimal.ZERO;
	}

	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFpa() {
		return fpa;
	}

	public void setFpa(BigDecimal fpa) {
		this.fpa = fpa;
	}

	public BigDecimal getTaxis() {
		return taxis;
	}

	public void setTaxis(BigDecimal taxis) {
		this.taxis = taxis;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getWithHolding() {
		return withHolding;
	}

	public void setWithHolding(BigDecimal withHolding) {
		this.withHolding = withHolding;
	}

	
	
}
