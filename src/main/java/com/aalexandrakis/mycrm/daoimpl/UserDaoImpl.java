package com.aalexandrakis.mycrm.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrm.models.User;
import com.aalexandrakis.mycrm.util.HibernateUtil;

public class UserDaoImpl {
	
	
	@SuppressWarnings("unchecked")
	public static User getUser(String userName, String userPassword) throws Exception{
		String query = "from User u where  u.j_username = '" + userName + "' and u.j_password = '" + userPassword+ "'";
		System.out.println("in get user method " + query);
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = null;
		try {
			List<User> users = (List<User>) session.createQuery(query).list();
			System.out.println("list size " + users.size());
			if (!users.isEmpty()){
				user = users.get(0);
			} 
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
}
