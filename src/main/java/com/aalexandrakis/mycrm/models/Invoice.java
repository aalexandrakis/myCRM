package com.aalexandrakis.mycrm.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Integer companyId;
	
	@Transient
	private CompanyInfo companyInfo;
	
	@NotNull @Min(1) @Column(name="customerId")
	private Integer customerId;
	
	@Transient
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
	
	@OneToMany(mappedBy="invoice", fetch = FetchType.EAGER)
	private List<InvoiceLine> invoiceLines;
	
	@Column(name="withHoldingString")
	private String withHoldingString;
	
	@Column(name="words")
	private String words;
	
	@Transient
	private String invoiceDateString;
	
	@Transient 
	private String dateFrom;
	
	@Transient 
	private String dateTo;
	
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
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
		return this.invoiceDate;
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

	
	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	
	public String getInvoiceDateString() {
		SimpleDateFormat new_df = new SimpleDateFormat("dd-MM-yyyy");
		this.invoiceDateString = new_df.format(this.invoiceDate);
		return this.invoiceDateString;
	}

	public void setInvoiceDateString(String invoiceDateString) {
		this.invoiceDateString = invoiceDateString;
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
			
			this.words = grossInWords();
		}
		
	}
	
	public String grossInWords(){
		Map<String, String> map = new HashMap<String, String>(){
			private static final long serialVersionUID = 1L;

		{
			put("11", "Εκατόν ");
			put("12", "Διακόσιες ");
			put("13", "Τριακόσιες ");
			put("14", "Τετρακόσιες ");
			put("15", "Πεντακόσιες ");
			put("16", "Εξακόσιες ");
			put("17", "Εφτακόσιες ");
			put("18", "Οχτακόσιες ");
			put("19", "Ενιακόσιες ");
			
			put("21", "Δέκα ");
			put("22", "Είκοσι ");
			put("23", "Τριάντα ");
			put("24", "Σαράντα ");
			put("25", "Πενήντα ");
			put("26", "Εξήντα ");
			put("27", "Εβδομήντα ");
			put("28", "Ογδόντα ");
			put("29", "Ενεννήντα ");
			
			put("2131", "Έντεκα ");
			put("2132", "Δωδεκα ");

			put("31", "Μια ");
			put("32", "Δύο ");
			put("33", "Τρεις ");
			put("34", "Τέσσερις ");
			put("35", "Πέντε ");
			put("36", "Εξι ");
			put("37", "Εφτά ");
			put("38", "Οχτώ ");
			put("39", "Εννέα ");
			
			put("41", "Εκατόν ");
			put("42", "Διακόσια ");
			put("43", "Τριακόσια ");
			put("44", "Τετρακόσια ");
			put("45", "Πεντακόσια ");
			put("46", "Εξακόσια ");
			put("47", "Εφτακόσια ");
			put("48", "Οχτακόσια ");
			put("49", "Ενιακόσια ");
			
			put("51", "Δέκα ");
			put("52", "Είκοσι ");
			put("53", "Τριάντα ");
			put("54", "Σαράντα ");
			put("55", "Πενήντα ");
			put("56", "Εξήντα ");
			put("57", "Εβδομήντα ");
			put("58", "Ογδόντα ");
			put("59", "Ενεννήντα ");
			
			put("5161", "Έντεκα ");
			put("5162", "Δωδεκα ");
			
			put("61", "Ένα ");
			put("62", "Δύο ");
			put("63", "Τρία ");
			put("64", "Τέσσερα ");
			put("65", "Πέντε ");
			put("66", "Έξι ");
			put("67", "Εφτά ");
			put("68", "Οχτώ ");
			put("69", "Εννέα ");
			
			put("81", "Δέκα ");
			put("82", "Έικοσι ");
			put("83", "Τριάντα ");
			put("84", "Σαρράντα ");
			put("85", "Πενήντα ");
			put("86", "Εξήντα ");
			put("87", "Εβδομήντα ");
			put("88", "Ογδόντα ");
			put("89", "Ενεννήντα ");
			
			put("8191", "Έντεκα ");
			put("8192", "Δώδεκα ");
			
			put("91", "Ένα ");
			put("92", "Δύο ");
			put("93", "Τρία ");
			put("94", "Τέσσερα ");
			put("95", "Πέντε ");
			put("96", "Έξι ");
			put("97", "Εφτά ");
			put("98", "Οχτώ ");
			put("99", "Εννέα ");
			
		}};
		
		List<String> grossArray = new ArrayList<String>();
		String grossInWords = "";
		int length =  this.gross.toString().length();
		int i = 1;
		for (i = 1 ; i <= 9 - length; i++){
			grossArray.add("0");
		}
		for(char character : this.gross.toString().toCharArray()){
			i++;
			grossArray.add(String.valueOf(character));
		}
		
		//thousands
		if (!grossArray.get(0).equals("0") || !grossArray.get(1).equals("0") || !grossArray.get(2).equals("0")){
			grossInWords += map.get("1" + grossArray.get(0)) != null ? map.get("1" + grossArray.get(0)) : "" ;
			if (grossArray.get(0).equals("0") && grossArray.get(1).equals("0") && grossArray.get(2).equals("1")){
				grossInWords += "Χίλια ";
			} else if (grossArray.get(1).equals("1") && (grossArray.get(2).equals("1") || grossArray.get(2).equals("2")) ){
				grossInWords += map.get("21" + "3" + grossArray.get(2));
				grossInWords += "Χιλιάδες , ";
			} else {
				grossInWords += map.get("2" + grossArray.get(1)) != null ? map.get("2" + grossArray.get(1)) : "";
				grossInWords += map.get("3" + grossArray.get(2)) != null ? map.get("3" + grossArray.get(2)) : "";
				grossInWords += "Χιλιάδες , ";
			}
		}
		
		
		//hundreds
		if (!grossArray.get(3).equals("0") || !grossArray.get(4).equals("0") || !grossArray.get(5).equals("0")){
			grossInWords += map.get("4" + grossArray.get(3)) != null ? map.get("4" + grossArray.get(3)) : "";
			if (grossArray.get(4).equals("1") && (grossArray.get(5).equals("1") || grossArray.get(5).equals("2")) ){
				grossInWords += map.get("51" + "6" + grossArray.get(5));
			} else {
				grossInWords += map.get("5" + grossArray.get(4)) != null ? map.get("5" + grossArray.get(4)) : "";
				grossInWords += map.get("6" + grossArray.get(5)) != null ? map.get("6" + grossArray.get(5)) : "";
			}
			grossInWords += "Ευρώ";
		}
		
		if (grossArray.get(6).equals(".") && !this.gross.toString().endsWith(".00")){
			grossInWords += " και ";
			//cents
			if (grossArray.get(7).equals("1") && (grossArray.get(8).equals("1") || grossArray.get(8).equals("2")) ){
				grossInWords += map.get("81" + "9" + grossArray.get(8));
			} else {
				grossInWords += map.get("8" + grossArray.get(7)) != null ? map.get("8" + grossArray.get(7)) : "";
				grossInWords += map.get("9" + grossArray.get(8)) != null ? map.get("9" + grossArray.get(8)) : "";
			}
			grossInWords += "λεπτά.";
		} else {
			grossInWords += ".";
		}
		
		
//		System.out.println(grossInWords);
		
		return grossInWords;
	}
	
}
