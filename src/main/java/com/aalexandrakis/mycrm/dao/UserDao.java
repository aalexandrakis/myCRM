package com.aalexandrakis.mycrm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aalexandrakis.mycrm.beans.User;
import com.aalexandrakis.mycrm.commons.Methods;

public class UserDao {
	
	public static User login(String userName, String password) throws SQLException{
		User user = new User(userName, password);
		Connection con = Methods.getConnection(userName, password);
		Statement stm = con.createStatement();
		String query;
		query = "Select role from roles where user = '" + userName + "'";
		ResultSet rs =  stm.executeQuery(query);
		while (rs.next()){
			user.addRole(rs.getString("role"));
		}
		rs.close();
		stm.close();
		con.close();
		return user;
	}
}
