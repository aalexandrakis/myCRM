package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.aalexandrakis.mycrm.validators.Percent;
import com.aalexandrakis.mycrm.validators.ValidDate;
@Entity
@Table(name="invoiceHeader")
public class Invoice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="invoiceId")
	private Integer invoiceId;

	@NotNull @Min(1) @Column(name="companyId")
	private int companyId;
	
	private CompanyInfo companyInfo;
	
	@NotNull @Min(1) @Column(name="companyId")
	private int customerId;
	
	private Customer customer;
	 
	@Column(name="amount")
	private BigDecimal amount;
	
	@Percent @Column(name="fpa")
	private BigDecimal fpa;
	
	@Column(name="fpaAmount")
	private BigDecimal fpaAmount;
	
	@Column(name="taxis")
	private BigDecimal taxis;
	
	@Column(name="gross")
	private BigDecimal gross;
	
	@Percent @Column(name="withHolding")
	private BigDecimal withHolding;
	
	@Column(name="withHoldingAmount")
	private BigDecimal withHoldingAmount;
	
	@Column(name="receivedAmount")
	private BigDecimal receivedAmount;
	
	@ValidDate(format="dd/MM/yyyy") @DateTimeFormat(pattern="dd/MM/yyyy") @Column(name="invoiceDate")
	private Date invoiceDate;
	
	private List<InvoiceLine> invoiceLines;
	
	private String withHoldingString;
	
	public Invoice(){
		this.withHolding = new BigDecimal("20.00");
		this.fpa = new BigDecimal("23.00");
		this.taxis = BigDecimal.ZERO;
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

	
	public BigDecimal getFpaAmount() {
		return fpaAmount;
	}

	public void setFpaAmount(BigDecimal fpaAmount) {
		this.fpaAmount = fpaAmount;
	}

	
	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	
	public BigDecimal getWithHoldingAmount() {
		return withHoldingAmount;
	}

	public void setWithHoldingAmount(BigDecimal withHoldingAmount) {
		this.withHoldingAmount = withHoldingAmount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	
	public String getWithHoldingString() {
		return withHoldingString;
	}

	public void setWithHoldingString(String withHoldingString) {
		this.withHoldingString = withHoldingString;
	}

	public void addNewLine(){
		calculate();
		if (this.invoiceLines == null) {
			this.invoiceLines = new ArrayList<InvoiceLine>();
		}
		this.invoiceLines.add(new InvoiceLine(this.invoiceLines.size() + 1));
	}
	
	public void removeLine(int lineId){  
		for (InvoiceLine line : this.invoiceLines){
			if (line.getLineId() == lineId){
				this.invoiceLines.remove(line);
				break;
			}
		}
		int i=0;
		for (InvoiceLine line : this.invoiceLines){
			i++;
			line.setLineId(i);
		}
		calculate();
	}
	
	public void calculate(){
		this.amount = BigDecimal.ZERO;
		this.fpaAmount = BigDecimal.ZERO;
		this.gross = BigDecimal.ZERO;
		this.withHoldingAmount = BigDecimal.ZERO;
		this.receivedAmount = BigDecimal.ZERO;
		this.withHoldingString = null;
		if (invoiceLines != null){
			for (InvoiceLine line : invoiceLines){
				if (line.getNet() != null){
					this.amount = this.amount.add(line.getNet());
				}
			}
			
			this.fpaAmount = (this.amount.multiply(this.fpa)).divide(new BigDecimal("100"));
			
			this.gross = this.amount.add(this.fpaAmount);
			
			if (this.withHolding != null && this.withHolding != BigDecimal.ZERO){
				this.withHoldingAmount = this.amount.multiply(this.withHolding).divide(new BigDecimal("100"));
				this.receivedAmount = this.amount.subtract(this.withHoldingAmount).add(this.fpaAmount);
				this.withHoldingString = "Έγινε παρακράτηση " + this.withHolding.toString() + "% = " +
									  this.withHoldingAmount.toString() + " Euro. Τελικό εισπρακτέο " + this.receivedAmount + " Euro";
			}
			
		}
		
	}
	
}
