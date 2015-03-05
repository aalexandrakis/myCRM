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
import com.aalexandrakis.mycrm.models.Outcome;
import com.aalexandrakis.mycrm.models.OutcomeLine;
import com.aalexandrakis.mycrm.models.Supplier;
import com.aalexandrakis.mycrm.util.HibernateUtil;

public class OutcomeDaoImpl {
	public static final DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	public static Outcome saveOutcome(Outcome outcome, MultipartFile file) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			Blob blob = Hibernate.getLobCreator(session).createBlob(file.getInputStream(), file.getSize());
			if (!file.isEmpty()){
	            outcome.setFileName(file.getOriginalFilename());
	            outcome.setOutcomeFile(blob);
	            outcome.setFileType(file.getContentType());
			} else {
				Outcome outcomeOld = getOutcome(outcome.getOutcomeId());
				outcome.setFileName(outcomeOld.getFileName());
	            outcome.setOutcomeFile(outcomeOld.getOutcomeFile());
	            outcome.setFileType(outcomeOld.getFileType());
			}			
			session.beginTransaction();
			session.saveOrUpdate(outcome);
			Integer outcomeId = outcome.getOutcomeId();
			for (OutcomeLine outcomeLine : outcome.getOutcomeLines()){
				outcomeLine.setOutcomeId(outcomeId);
				session.saveOrUpdate(outcomeLine);
			}
			session.getTransaction().commit();
			return outcome;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Outcome> getOutcomes(Map<String, Object> parms){
		String query = "from Outcome";
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
				query += "outcomeDate between '" + parms.get("dateFrom") + "' and '" + parms.get("dateTo") + "'";
			}
		}
		System.out.println(query);
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Outcome> outcomeList = session.createQuery(query).list();
		
		for (Outcome outcome : outcomeList){
			outcome.setSupplier((Supplier) session.get(Supplier.class, outcome.getSupplierId()));
			outcome.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, outcome.getCompanyId()));
		}
		
		session.getTransaction().commit();
		session.close();
		return outcomeList;
	}
	
	public static Outcome getOutcome(Integer outcomeId) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.openSession();
		try{
			session.beginTransaction();
			Outcome outcome = (Outcome) session.get(Outcome.class, outcomeId);
			outcome.setCompanyInfo((CompanyInfo) session.get(CompanyInfo.class, outcome.getCompanyId()));
			outcome.setSupplier((Supplier) session.get(Supplier.class, outcome.getSupplierId()));
			return outcome;
		} catch (Exception e){
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}
