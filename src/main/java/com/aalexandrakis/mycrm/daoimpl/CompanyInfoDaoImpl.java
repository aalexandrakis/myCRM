package com.aalexandrakis.mycrm.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrm.commons.Methods;
import com.aalexandrakis.mycrm.models.CompanyInfo;
import com.aalexandrakis.mycrm.util.HibernateUtil;

public class CompanyInfoDaoImpl {

	
	public static void saveCompanyInfo(CompanyInfo companyInfo) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.saveOrUpdate(companyInfo);
		session.getTransaction().commit();
		sessionFactory.close();
	}
//	public static boolean saveCompanyInfo(CompanyInfo companyInfo) throws SQLException{
//		String query = "Insert into companyInfo values(NULL, '" + companyInfo.getName() + "', '" +
//														  companyInfo.getBusDesc() + "', '" +
//														  companyInfo.getAddress() + "', '" +
//														  companyInfo.getAfm() + "', '" +
//														  companyInfo.getDoy() + "', '" +
//														  companyInfo.getMobilePhone() + "', '" +
//														  companyInfo.getWorkPhone() + "', '" +
//														  companyInfo.getEmail() + "')";
//		System.out.println(query);
//		Connection con = Methods.getConnection();
//		Statement stm = con.createStatement();
//    	stm.executeUpdate(query);
//		stm.close();
//		con.close();
//		return true;
//	}

//	public static boolean updateCompanyInfo(CompanyInfo companyInfo) throws SQLException{
//		String query = "Update companyInfo set name = '" + companyInfo.getName() + "', " +
//										 "busDesc = '" + companyInfo.getBusDesc() + "', " +
//										 "doy = '" + companyInfo.getDoy() + "', " +
//										 "address = '" + companyInfo.getAddress() + "', " +
//										 "afm = '" + 	companyInfo.getAfm() + "', " +
//										 "mobilePhone = '" + 	companyInfo.getMobilePhone() + "', " + 
//										 "workPhone = '" + 	companyInfo.getWorkPhone() + "', " + 
//										 "email = '" + 	companyInfo.getEmail() + "' " +
//										 "where companyId = " + companyInfo.getCompanyId();
//		System.out.println(query);
//		Connection con = Methods.getConnection();
//		Statement stm = con.createStatement();
//    	stm.executeUpdate(query);
//		stm.close();
//		con.close();
//		return true;
//	}

	public static CompanyInfo getCompanyInfo(Integer companyId) throws Exception{
		Connection con = Methods.getConnection();
		Statement stm = con.createStatement();
	    ResultSet rs =  stm.executeQuery("Select * from companyInfo where companyId = " + companyId);
		CompanyInfo companyInfo = new CompanyInfo();
		try {
			while (rs.next()){
				companyInfo.setCompanyId(rs.getInt("companyId"));
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
	
	
	public static List<CompanyInfo> getCompaniesInfo(Map<String, String> parms) throws Exception{
		String query = "Select * from companyInfo";
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
		List<CompanyInfo> companiesInfo = new ArrayList<CompanyInfo>();
		try {
			while (rs.next()){
				CompanyInfo companyInfo = new CompanyInfo();
				companyInfo.setCompanyId(rs.getInt("companyId"));
				companyInfo.setName(rs.getString("name"));
				companyInfo.setBusDesc(rs.getString("busDesc"));
				companyInfo.setAddress(rs.getString("address"));
				companyInfo.setAfm(rs.getString("afm"));
				companyInfo.setDoy(rs.getString("doy"));
				companyInfo.setMobilePhone(rs.getString("mobilePhone"));
				companyInfo.setWorkPhone(rs.getString("workPhone"));
				companyInfo.setEmail(rs.getString("email"));
				companiesInfo.add(companyInfo);
			}
		}  catch (Exception e){
			throw e;
		} finally {
			rs.close();
			stm.close();
			con.close();
		}
		return companiesInfo;
	}
}
