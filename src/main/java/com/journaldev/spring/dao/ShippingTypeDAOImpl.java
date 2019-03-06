package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.ShippingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingTypeDAOImpl implements ShippingTypeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ShippingTypeDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addShippingType(ShippingType p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("ShippingType saved successfully, ShippingType Details="+p);
	}

	@Override
	public void updateShippingType(ShippingType p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("ShippingType updated successfully, ShippingType Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingType> listShippingTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ShippingType> personsList = session.createQuery("FROM ShippingType").list();
		for(ShippingType p : personsList){
			logger.info("ShippingType List::"+p);
		}
		return personsList;
	}

	@Override
	public ShippingType getShippingTypeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ShippingType p = (ShippingType) session.load(ShippingType.class, new Integer(id));
		logger.info("ShippingType loaded successfully, ShippingType details="+p);
		return p;
	}

	@Override
	public void removeShippingType(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ShippingType p = (ShippingType) session.load(ShippingType.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("ShippingType deleted successfully, person details="+p);
	}

}
