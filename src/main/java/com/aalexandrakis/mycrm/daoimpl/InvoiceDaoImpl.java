package com.aalexandrakis.mycrm.daoimpl;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aalexandrakis.mycrm.models.CompanyInfo;
import com.aalexandrakis.mycrm.models.Customer;
import com.aalexandrakis.mycrm.models.Invoice;
import com.aalexandrakis.mycrm.models.InvoiceLine;
import com.aalexandrakis.mycrm.util.HibernateUtil;

public class InvoiceDaoImpl {
	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static void saveInvoice(Invoice invoice) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			session.save(invoice);
			Integer invoiceId = invoice.getInvoiceId();
			for (InvoiceLine invoiceLine : invoice.getInvoiceLines()){
				invoiceLine.setInvoiceId(invoiceId);
				session.save(invoiceLine);
			}
			session.getTransaction().commit();
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public static void saveInvoice(Invoice invoice, ByteArrayOutputStream os) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Blob blob = Hibernate.getLobCreator(session).createBlob(os.toByteArray());
			if (os != null){
	            invoice.setInvoiceFile(blob);
			}		
			session.update(invoice);
			session.getTransaction().commit();
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	public static List<Invoice> getInvoices(Map<String, Object> parms){
		String query = "from Invoice";
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
				query += "invoiceDate between '" + parms.get("dateFrom") + "' and '" + parms.get("dateTo") + "'";
			}
		}
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Invoice> invoiceList = session.createQuery(query).list();
		
		for (Invoice invoice : invoiceList){
			invoice.setCustomer((Customer) session.get(Customer.class, invoice.getCustomerId()));
			invoice.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, invoice.getCompanyId()));
		}
		
		session.getTransaction().commit();
		session.close();
		return invoiceList;
	}
	
	public static Invoice getInvoice(Integer invoiceId) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Invoice invoice = (Invoice) session.get(Invoice.class, invoiceId);
			invoice.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, invoice.getCompanyId()));
			invoice.setCustomer((Customer) session.get(Customer.class, invoice.getCustomerId()));
			return invoice;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
