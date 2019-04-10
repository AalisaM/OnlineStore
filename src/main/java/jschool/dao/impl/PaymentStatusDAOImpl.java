package jschool.dao.impl;

import jschool.dao.PaymentStatusDAO;
import jschool.dao.ShippingTypeDAO;
import jschool.model.PaymentStatus;
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
public class PaymentStatusDAOImpl implements PaymentStatusDAO {
	
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
	public PaymentStatus findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PaymentStatus p = (PaymentStatus) session.load(PaymentStatus.class, id);
		logger.info("Order loaded successfully, Order details="+p);
		return p;
	}

	@Override
	public void update(PaymentStatus p) {

	}

	@Override
	public void add(PaymentStatus p) {

	}

	@Override
	public void remove(int id) {

	}

	@Override
	public PaymentStatus getByStatus(String status) {
		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery cq = cb.createQuery(PaymentStatus.class);
		Root<PaymentStatus> roles = cq.from(PaymentStatus.class);
		cq.where(cb.equal(roles.get("status"), status));

		List<PaymentStatus> result = session.createQuery(cq).getResultList();
		return result.size() == 0 ? null : result.get(0);
	}
}
