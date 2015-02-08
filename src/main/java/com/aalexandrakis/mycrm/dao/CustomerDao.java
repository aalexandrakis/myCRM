package com.aalexandrakis.mycrm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aalexandrakis.mycrm.beans.Customer;
import com.aalexandrakis.mycrm.commons.Methods;

public class CustomerDao {
	
	public static List<Customer> getCustomers(Map<String, String> parms) throws SQLException{
		String query = "Select * from customers";
		if (parms != null){
			int counter = 0;
			for (String key : parms.keySet()){
				if (counter == 0){
					query += " where ";
				} else {
					query += " and ";
				}
				query += key + " like '" + parms.get(key) + "%'";
				counter++;
			}
		}
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    ResultSet rs =  stm.executeQuery(query);
	    List<Customer> customers = new ArrayList<Customer>();
		while (rs.next()){
			Customer customer = new Customer();
			customer.setCustomerAfm(rs.getString("customerAfm"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerBusDesc(rs.getString("customerBusDesc"));
			customer.setCustomerDoy(rs.getString("customerDoy"));
			customer.setCustomerAddress(rs.getString("customerAddress"));
			customer.setCustomerId(rs.getInt("customerId"));
			customer.setCustomerPhone(rs.getString("customerPhone"));
			customers.add(customer);
		}
		rs.close();
		stm.close();
		con.close();
		
		return customers;
	}
	
	public static boolean saveCustomer(Customer customer) throws Exception{
		String query = "Insert into customers values(NULL, '" + customer.getCustomerName() + "', '" +
																customer.getCustomerBusDesc() + "', '" +
																customer.getCustomerDoy() + "', '" +
																customer.getCustomerAddress() + "', '" +
																customer.getCustomerAfm() + "', '" +
																customer.getCustomerPhone() + "')";
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    try {
	    	stm.executeUpdate(query);
	    } catch (Exception e){
	    	throw e;
	    } finally {
			stm.close();
			con.close();
	    }
		return true;
	}

	public static boolean updateCustomer(Customer customer) throws Exception{
		String query = "Update customers set customerName = '" + customer.getCustomerName() + "', " +
										 "customerBusDesc = '" + customer.getCustomerBusDesc() + "', " +
										 "customerDoy = '" + customer.getCustomerDoy() + "', " +
										 "customerAddress = '" + customer.getCustomerAddress() + "', " +
										 "customerAfm = '" + 	customer.getCustomerAfm() + "', " +
										 "customerPhone = '" + 	customer.getCustomerPhone() + "' " + 
										 "where customerId = " + customer.getCustomerId();
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    try {
	    	stm.executeUpdate(query);
	    } catch (Exception e){
	    	throw e;
	    } finally {
			stm.close();
			con.close();
	    }
		return true;
	}

	public static Customer getCustomer(Integer customerId) throws Exception{
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    ResultSet rs =  stm.executeQuery("Select * from customers where customerId = " + customerId);
		Customer customer = new Customer();
		try {
			while (rs.next()){
				customer.setCustomerAfm(rs.getString("customerAfm"));
				customer.setCustomerName(rs.getString("customerName"));
				customer.setCustomerBusDesc(rs.getString("customerBusDesc"));
				customer.setCustomerDoy(rs.getString("customerDoy"));
				customer.setCustomerAddress(rs.getString("customerAddress"));
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setCustomerPhone(rs.getString("customerPhone"));
			}
		}  catch (Exception e){
			throw e;
		} finally {
			rs.close();
			stm.close();
			con.close();
		}
		return customer;
	}
}
