package com.aalexandrakis.mycrm.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.models.Supplier;

public class SupplierDaoImpl {
	
	public static List<Supplier> getSuppliers(Map<String, String> parms) throws SQLException{
		String query = "Select * from suppliers";
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
	    List<Supplier> suppliers = new ArrayList<Supplier>();
		while (rs.next()){
			Supplier supplier = new Supplier();
			supplier.setSupplierAfm(rs.getString("supplierAfm"));
			supplier.setSupplierName(rs.getString("supplierName"));
			supplier.setSupplierBusDesc(rs.getString("supplierBusDesc"));
			supplier.setSupplierDoy(rs.getString("supplierDoy"));
			supplier.setSupplierAddress(rs.getString("supplierAddress"));
			supplier.setSupplierId(rs.getInt("supplierId"));
			supplier.setSupplierPhone(rs.getString("supplierPhone"));
			suppliers.add(supplier);
		}
		rs.close();
		stm.close();
		con.close();
		
		return suppliers;
	}
	
	public static boolean saveSupplier(Supplier supplier) throws Exception{
		String query = "Insert into suppliers values(NULL, '" + supplier.getSupplierName() + "', '" +
																supplier.getSupplierBusDesc() + "', '" +
																supplier.getSupplierDoy() + "', '" +
																supplier.getSupplierAddress() + "', '" +
																supplier.getSupplierAfm() + "', '" +
																supplier.getSupplierPhone() + "')";
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

	public static boolean updateSupplier(Supplier supplier) throws Exception{
		String query = "Update suppliers set supplierName = '" + supplier.getSupplierName() + "', " +
										 "supplierBusDesc = '" + supplier.getSupplierBusDesc() + "', " +
										 "supplierDoy = '" + supplier.getSupplierDoy() + "', " +
										 "supplierAddress = '" + supplier.getSupplierAddress() + "', " +
										 "supplierAfm = '" + 	supplier.getSupplierAfm() + "', " +
										 "supplierPhone = '" + 	supplier.getSupplierPhone() + "' " + 
										 "where supplierId = " + supplier.getSupplierId();
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

	public static Supplier getSupplier(Integer supplierId) throws Exception{
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    ResultSet rs =  stm.executeQuery("Select * from suppliers where supplierId = " + supplierId);
		Supplier supplier = new Supplier();
		try {
			while (rs.next()){
				supplier.setSupplierAfm(rs.getString("supplierAfm"));
				supplier.setSupplierName(rs.getString("supplierName"));
				supplier.setSupplierBusDesc(rs.getString("supplierBusDesc"));
				supplier.setSupplierDoy(rs.getString("supplierDoy"));
				supplier.setSupplierAddress(rs.getString("supplierAddress"));
				supplier.setSupplierId(rs.getInt("supplierId"));
				supplier.setSupplierPhone(rs.getString("supplierPhone"));
			}
		}  catch (Exception e){
			throw e;
		} finally {
			rs.close();
			stm.close();
			con.close();
		}
		return supplier;
	}
}
