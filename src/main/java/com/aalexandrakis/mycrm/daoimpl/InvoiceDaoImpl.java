package com.aalexandrakis.mycrm.daoimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
