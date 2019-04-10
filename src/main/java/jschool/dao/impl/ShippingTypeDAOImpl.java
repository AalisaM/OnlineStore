package jschool.dao.impl;

import java.util.List;

import jschool.dao.ShippingTypeDAO;
import jschool.model.ShippingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShippingTypeDAOImpl implements ShippingTypeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void add(ShippingType p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("ShippingType saved successfully, ShippingType Details="+p);
	}

	@Override
	public void update(ShippingType p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("ShippingType updated successfully, ShippingType Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingType> list() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ShippingType> personsList = session.createQuery("FROM " + ShippingType.class.getSimpleName()).list();
		for(ShippingType p : personsList){
			logger.info("ShippingType List::"+p);
		}
		return personsList;
	}

	@Override
	public ShippingType findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ShippingType p = (ShippingType) session.load(ShippingType.class, id);
		logger.info("ShippingType loaded successfully, ShippingType details="+p);
		return p;
	}

	@Override
	public void remove(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ShippingType p = (ShippingType) session.load(ShippingType.class, id);
		if(null != p){
			session.delete(p);
		}
		logger.info("ShippingType deleted successfully, person details="+p);
	}

}
