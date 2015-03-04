package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="outcomeDetails")
public class OutcomeLine implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="outcomeId")
	private Integer outcomeId;
	@NotBlank @Column(name="description")
	private String description;
	
	@NumberFormat(pattern = "###,###,##0.00") @Column(name="net")
	private BigDecimal net;
	
	@Column(name="lineId")
	private int lineId;

	@NumberFormat(pattern = "##0.00") @Column(name="fpa")
	private BigDecimal fpa;

	@ManyToOne  
	@JoinColumn(name="outcomeId", insertable = false, updatable = false)
	private Outcome outcome;

	
	public OutcomeLine(){
		
	}

	public OutcomeLine(String description){
		this.description = description;
	}
	
	public OutcomeLine(int lineId){
		this.lineId = lineId;
	}
	
	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
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

	
	public BigDecimal getFpa() {
		return fpa;
	}

	public void setFpa(BigDecimal fpa) {
		this.fpa = fpa;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	
	
}
