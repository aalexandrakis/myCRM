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
														  invoice.getWithHoldingString() + "')";
		System.out.println(query);
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
		int invoiceId = 0;
		stm.executeUpdate(query, invoiceId);
//		invoice.setInvoiceId(stm.getGeneratedKeys().getInt(0));
		for (InvoiceLine line : invoice.getInvoiceLines()){
			query = "Insert into invoiceDetails values(" + invoiceId + " , '" + 
														   line.getDescription() + "', '" +
														   line.getNet() + "', '" + 
														   line.getLineId() + "')";
			System.out.println(query);
			stm.executeUpdate(query);
		}
		
		stm.close();
		con.close();
		return true;
	}

}
