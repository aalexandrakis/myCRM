package com.aalexandrakis.mycrm.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.models.CompanyInfo;
import com.aalexandrakis.mycrm.models.Invoice;
import com.aalexandrakis.mycrm.models.InvoiceLine;

public class InvoiceDaoImpl {
	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static boolean saveInvoice(Invoice invoice) throws SQLException{
		
		String query = "Insert into  invoiceHeader values(NULL, '" +    invoice.getCompanyId() + "', '" +
														  invoice.getCustomerId() + "', '" +
														  invoice.getAmount() + "', '" +
														  invoice.getFpa() + "', '" +
														  invoice.getTaxis() + "', '" +
														  invoice.getGross() + "', '" +
														  invoice.getWithHolding() + "', '" +
														  invoice.getFpaAmount() + "', '" +
														  invoice.getWithHoldingAmount() + "', '" +
														  invoice.getReceivedAmount() + "', '" + 
														  df.format(invoice.getInvoiceDate()) + "', '" +
														  invoice.getWithHoldingString() + "', '" +
														  invoice.getWords() + "')";
		System.out.println(query);
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
		stm.executeUpdate(query);
		ResultSet rs = stm.getGeneratedKeys();
//		System.out.println("invoice id " + rs.getInt(1));
		for (InvoiceLine line : invoice.getInvoiceLines()){
			query = "Insert into invoiceDetails values(" + 10 + " , '" + 
														   line.getDescription() + "', '" +
														   line.getNet() + "', '" + 
														   line.getLineId() + "')";
			System.out.println("detail query " + query);
			stm.executeUpdate(query);
		}
		rs.close();
		stm.close();
		con.close();
		return true;
	}

}
