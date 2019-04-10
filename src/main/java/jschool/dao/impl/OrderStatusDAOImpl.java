package jschool.dao.impl;

import jschool.dao.OrderStatusDAO;
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
public class OrderStatusDAOImpl implements OrderStatusDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public List<OrderStatus> list() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OrderStatus> categories = session.createQuery("FROM " + OrderStatus.class.getSimpleName()).list();
		for(OrderStatus p : categories){
			logger.info("OrderStatus List::"+p);
		}
		return categories;
	}

	@Override
	public OrderStatus findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		OrderStatus p = (OrderStatus) session.load(OrderStatus.class, new Integer(id));
		logger.info("OrderStatus loaded successfully, OrderStatus details="+p);
		return p;
	}

	@Override
	public void update(OrderStatus p) {

	}

	@Override
	public void add(OrderStatus p) {

	}

	@Override
	public void remove(int id) {

	}

	@Override
	public OrderStatus getByStatus(String orderStatus) {

		Session session = this.sessionFactory.getCurrentSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery cq = cb.createQuery(OrderStatus.class);
		Root<OrderStatus> roles = cq.from(OrderStatus.class);
		cq.where(cb.equal(roles.get("status"), orderStatus));

		List<OrderStatus> result = session.createQuery(cq).getResultList();
		return result.size() == 0 ? null : result.get(0);
	}
}
