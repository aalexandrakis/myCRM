package com.aalexandrakis.mycrm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aalexandrakis.mycrm.beans.CompanyInfo;
import com.aalexandrakis.mycrm.commons.Methods;

public class CompanyInfoDao {
	
	public static boolean saveOrUpdateCompanyInfo(CompanyInfo companyInfo) throws Exception{
		String query = "Select count(*) from companyInfo";
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(query);
		System.out.println(rs.getFetchSize());
		rs.next();
		if (rs.getInt(1) > 0){
			updateCompanyInfo(companyInfo);
		} else {
			saveCompanyInfo(companyInfo);
		}
		
		stm.close();
		con.close();
	    rs.close();
		return true;
	}
	
	public static boolean saveCompanyInfo(CompanyInfo companyInfo) throws SQLException{
		String query = "Insert into companyInfo values('" + companyInfo.getName() + "', '" +
														  companyInfo.getBusDesc() + "', '" +
														  companyInfo.getAddress() + "', '" +
														  companyInfo.getAfm() + "', '" +
														  companyInfo.getDoy() + "', '" +
														  companyInfo.getMobilePhone() + "', '" +
														  companyInfo.getWorkPhone() + "', '" +
														  companyInfo.getEmail() + "')";
		System.out.println(query);
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
    	stm.executeUpdate(query);
		stm.close();
		con.close();
		return true;
	}

	public static boolean updateCompanyInfo(CompanyInfo companyInfo) throws SQLException{
		String query = "Update companyInfo set name = '" + companyInfo.getName() + "', " +
										 "busDesc = '" + companyInfo.getBusDesc() + "', " +
										 "doy = '" + companyInfo.getDoy() + "', " +
										 "address = '" + companyInfo.getAddress() + "', " +
										 "afm = '" + 	companyInfo.getAfm() + "', " +
										 "mobilePhone = '" + 	companyInfo.getMobilePhone() + "', " + 
										 "workPhone = '" + 	companyInfo.getWorkPhone() + "', " + 
										 "email = '" + 	companyInfo.getEmail() + "'";
		System.out.println(query);
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
    	stm.executeUpdate(query);
		stm.close();
		con.close();
		return true;
	}

	public static CompanyInfo getCompanyInfo() throws Exception{
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    ResultSet rs =  stm.executeQuery("Select * from companyInfo");
		CompanyInfo companyInfo = new CompanyInfo();
		try {
			while (rs.next()){
				companyInfo.setName(rs.getString("name"));
				companyInfo.setBusDesc(rs.getString("busDesc"));
				companyInfo.setAddress(rs.getString("address"));
				companyInfo.setAfm(rs.getString("afm"));
				companyInfo.setDoy(rs.getString("doy"));
				companyInfo.setMobilePhone(rs.getString("mobilePhone"));
				companyInfo.setWorkPhone(rs.getString("workPhone"));
				companyInfo.setEmail(rs.getString("email"));
			}
		}  catch (Exception e){
			throw e;
		} finally {
			rs.close();
			stm.close();
			con.close();
		}
		return companyInfo;
	}
}
