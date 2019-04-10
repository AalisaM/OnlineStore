package jschool.dao.impl;

import jschool.dao.PaymentTypeDAO;
import jschool.dao.ShippingTypeDAO;
import jschool.model.OrderStatus;
import jschool.model.PaymentType;
import jschool.model.ShippingType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PaymentTypeDAOImpl implements PaymentTypeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public List<PaymentType> list() {
		Session session = this.sessionFactory.getCurrentSession();
		List<PaymentType> categories = session.createQuery("FROM " + PaymentType.class.getSimpleName()).list();
		for(PaymentType p : categories){
			logger.info("PaymentType List::"+p);
		}
		return categories;
	}

	@Override
	public PaymentType findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PaymentType p = (PaymentType) session.load(PaymentType.class, id);
		logger.info("PaymentType loaded successfully, PaymentType details="+p);
		return p;
	}

	@Override
	public void update(PaymentType p) {

	}

	@Override
	public void add(PaymentType p) {

	}

	@Override
	public void remove(int id) {

	}

	@Override
	public PaymentType findByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery cq = cb.createQuery(PaymentType.class);
		Root<PaymentType> roles = cq.from(PaymentType.class);
		cq.where(cb.equal(roles.get("type"), name));

		List<PaymentType> result = session.createQuery(cq).getResultList();
		return result.size() == 0 ? null : result.get(0);
	}
}
