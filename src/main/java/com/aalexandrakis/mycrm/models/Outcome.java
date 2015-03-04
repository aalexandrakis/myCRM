package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.aalexandrakis.mycrm.validators.ValidDate;
@Entity
@Table(name="outcomeHeader")
public class Outcome implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="outcomeId")
	private Integer outcomeId;

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
	
	@Column(name="fpaAmount") @NumberFormat(style=Style.CURRENCY) 
	private BigDecimal fpaAmount;
	
	@Column(name="gross") @NumberFormat(style=Style.CURRENCY)
	private BigDecimal gross;
	
	@ValidDate(format="dd/MM/yyyy") @DateTimeFormat(pattern="dd/MM/yyyy") @Column(name="outcomeDate")
	private Date outcomeDate;
	
	@NotBlank @Column(name="outcomeNumber")
	private String outcomeNumber;
	
	@OneToMany(mappedBy="outcome", fetch = FetchType.EAGER)
	private List<OutcomeLine> outcomeLines;
	
	@Transient
	private String outcomeDateString;
	
	@Transient 
	private String dateFrom;
	
	@Transient 
	private String dateTo;
	

	
	public Integer getOutcomeId() {
		return outcomeId;
	}

	public void setOutcomeId(Integer outcomeId) {
		this.outcomeId = outcomeId;
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

	public BigDecimal getFpaAmount() {
		return fpaAmount;
	}

	public void setFpaAmount(BigDecimal fpaAmount) {
		this.fpaAmount = fpaAmount;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	public Date getOutcomeDate() {
		return outcomeDate;
	}

	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

	public List<OutcomeLine> getOutcomeLines() {
		return outcomeLines;
	}

	public void setOutcomeLines(List<OutcomeLine> outcomeLines) {
		this.outcomeLines = outcomeLines;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOutcomeDateString() {
		SimpleDateFormat new_df = new SimpleDateFormat("dd-MM-yyyy");
		this.outcomeDateString = new_df.format(this.outcomeDate);
		return this.outcomeDateString;
	}

	public void setOutcomeDateString(String outcomeDateString) {
		this.outcomeDateString = outcomeDateString;
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

	
	public String getOutcomeNumber() {
		return outcomeNumber;
	}

	public void setOutcomeNumber(String outcomeNumber) {
		this.outcomeNumber = outcomeNumber;
	}

	public void addNewLine(){
		if (this.outcomeLines == null) {
			this.outcomeLines = new ArrayList<OutcomeLine>();
		}
		this.outcomeLines.add(new OutcomeLine(this.outcomeLines.size() + 1));
		calculate();
	}
	
	public void removeLine(int lineId){  
		for (OutcomeLine line : this.outcomeLines){
			if (line.getLineId() == lineId){
				this.outcomeLines.remove(line);
				break;
			}
		}
		int i=0;
		for (OutcomeLine line : this.outcomeLines){
			i++;
			line.setLineId(i);
		}
		calculate();
	}
	
	public void calculate(){
		this.gross = BigDecimal.ZERO;
		this.fpaAmount = BigDecimal.ZERO;
		this.amount = BigDecimal.ZERO;
		if (this.outcomeLines != null){
			for (OutcomeLine line : this.outcomeLines){
				if (line.getNet() != null && !line.getNet().equals(BigDecimal.ZERO)){
					this.amount = this.amount.add(line.getNet());
					BigDecimal fpaLineAmount = BigDecimal.ZERO;
					fpaLineAmount = line.getNet().multiply(line.getFpa()).divide(new BigDecimal("100"));
					this.fpaAmount = this.fpaAmount.add(fpaLineAmount);
				}
			}
			this.gross = this.fpaAmount.add(this.amount);
		}
	}
}
