package com.aalexandrakis.mycrm.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.aalexandrakis.mycrm.validators.Percent;

public class Invoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer invoiceId;

	@NotNull @Min(1)
	private int companyId;
	
	private CompanyInfo companyInfo;
	
	@NotNull @Min(1)
	private int customerId;
	
	private Customer customer;
	 
	private BigDecimal amount;
	
	@Percent
	private BigDecimal fpa;
	
	private BigDecimal taxis;
	
	private BigDecimal gross;
	
	@Percent
	private BigDecimal withHolding;
	
	private List<InvoiceLine> invoiceLines;
	
	
	public Invoice(){
		this.withHolding = new BigDecimal("20.00");
		this.fpa = new BigDecimal("23.00");
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

	public void addNewLine(){
		if (this.invoiceLines == null) {
			this.invoiceLines = new ArrayList<InvoiceLine>();
		}
		this.invoiceLines.add(new InvoiceLine(this.invoiceLines.size() + 1));
	}
	
	public void removeLine(int lineId){  
		for (InvoiceLine line : this.invoiceLines){
			if (line.getLineId() == lineId){
				this.invoiceLines.remove(line);
			}
		}
		int i=0;
		for (InvoiceLine line : this.invoiceLines){
			i++;
			line.setLineId(i);
		}
	}
	
}
