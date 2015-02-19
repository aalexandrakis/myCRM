package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="invoiceDetails")
public class InvoiceLine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="invoiceId")
	private Integer invoiceId;
	@NotBlank @Column(name="description")
	private String description;
	
	@NotBlank @NumberFormat(pattern = "###,###,##0.00") @Column(name="net")
	private BigDecimal net;
	
	@Column(name="lineId")
	private int lineId;
	
	public InvoiceLine(){
		
	}

	public InvoiceLine(String description){
		this.description = description;
	}
	
	public InvoiceLine(int lineId){
		this.lineId = lineId;
	}
	
	public Integer getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getNet() {
		return net;
	}

	public void setNet(BigDecimal net) {
		this.net = net;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	
	
}
