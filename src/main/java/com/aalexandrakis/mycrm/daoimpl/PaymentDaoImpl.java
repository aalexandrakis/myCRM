package com.aalexandrakis.mycrm.daoimpl;

import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aalexandrakis.mycrm.models.CompanyInfo;
import com.aalexandrakis.mycrm.models.Payment;
import com.aalexandrakis.mycrm.models.Supplier;
import com.aalexandrakis.mycrm.util.HibernateUtil;

public class PaymentDaoImpl {
	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static Payment savePayment(Payment payment) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(payment);
			session.getTransaction().commit();
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return payment;
	}
	
	public static Payment savePayment(Payment payment, MultipartFile file) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Blob blob = Hibernate.getLobCreator(session).createBlob(file.getInputStream(), file.getSize());
			if (!file.isEmpty()){
	            payment.setPaymentFileName(file.getOriginalFilename());
	            payment.setPaymentFile(blob);
	            payment.setPaymentFileType(file.getContentType());
			} else {
				Payment paymentOld = getPayment(payment.getPaymentId());
				payment.setPaymentFileName(paymentOld.getPaymentFileName());
	            payment.setPaymentFile(paymentOld.getPaymentFile());
	            payment.setPaymentFileType(paymentOld.getPaymentFileType());
			}		
//			if (file != null){
//	            payment.setPaymentFile(blob);
//			}
			session.save(payment);
			session.getTransaction().commit();
			return payment;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	public static List<Payment> getPayments(Map<String, Object> parms){
		String query = "from Payment";
		if (parms != null){
			int i = 0;
			for (String key : parms.keySet()){
				if (key.endsWith("Id")){
					if (i==0){
						query += " where ";
					} else {
						query += " and ";
					}
					query += key + " = " + parms.get(key);
					i++;
				}
			}
			if (parms.containsKey("dateFrom") && parms.containsKey("dateTo")){
				if (i==0){
					query += " where ";
				} else {
					query += " and ";
				}
				query += "paymentDate between '" + parms.get("dateFrom") + "' and '" + parms.get("dateTo") + "'";
			}
		}
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Payment> paymentList = session.createQuery(query).list();
		for (Payment payment : paymentList){
			payment.setSupplier((Supplier) session.get(Supplier.class, payment.getSupplierId()));
			payment.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, payment.getCompanyId()));
		}
		session.getTransaction().commit();
		session.close();
		return paymentList;
	}
	
	public static Payment getPayment(Integer paymentId) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Payment payment = (Payment) session.get(Payment.class, paymentId);
			payment.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, payment.getCompanyId()));
			payment.setSupplier((Supplier) session.get(Supplier.class, payment.getSupplierId()));
			return payment;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
