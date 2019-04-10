package jschool.dao.impl;

import jschool.dao.CartDAO;
import jschool.dao.OrderDAO;
import jschool.model.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public List<Order> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Order> categories = session.createQuery("FROM " + Order.class.getSimpleName()).list();
        for(Order p : categories){
            logger.info("Order List::"+p);
        }
        return categories;
    }

    @Override
    public Order findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Order p = (Order) session.load(Order.class, new Integer(id));
        logger.info("Order loaded successfully, Order details="+p);
        return p;
    }


    @Override
    public void update(Order o) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(o);
        logger.info("Order updated successfully, Order Details="+o);
    }

    @Override
    public void add(Order c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
        logger.info("Order saved successfully, Category Details="+c);
    }

    @Override
    public List<Order> getUnpaidOrderList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //select * from orderList where payment status not paid
        CriteriaQuery<Order> q = cb.createQuery(Order.class);
        Root<Order> c = q.from(Order.class);
        Join<Order, PaymentStatus> statuses = c.join("paymentStatus");

        if (id > 0) {
            Join<Order, User> users = c.join("user");

            q.select(c)
                    .where(
                            cb.equal(statuses.get("status"), "not paid"),
                            cb.equal(users.get("id"), id)

                    );
        }else {
            q.select(c)
                    .where(
                            cb.equal(statuses.get("status"), "not paid")
                    );
        }
        TypedQuery<Order> typedQuery = session.createQuery(q);
        return typedQuery.getResultList();
    }

    @Override
    public List<Order> getPaidOrderList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //select * from orderList where payment status not paid
        CriteriaQuery<Order> q = cb.createQuery(Order.class);
        Root<Order> c = q.from(Order.class);
        Join<Order, PaymentStatus> statuses = c.join("paymentStatus");
        Join<Order, OrderStatus> orderStatuses = c.join("orderStatus");

        if (id > 0) {
            Join<Order, User> users = c.join("user");
            q.select(c)
                    .where(
                            cb.equal(statuses.get("status"), "paid"),
                            cb.equal(orderStatuses.get("status"), "waiting for delivery"),
                            cb.equal(users.get("id"), id)

                    );
        }else {
            q.select(c)
                    .where(
                            cb.equal(statuses.get("status"), "paid"),
                            cb.equal(orderStatuses.get("status"), "waiting for delivery")
                    );
        }
        TypedQuery<Order> typedQuery = session.createQuery(q);
        return typedQuery.getResultList();
    }

    @Override
    public List<Order> getProcessedOrderList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //select * from orderList where orderStatus id = 3 or 4
        CriteriaQuery<Order> q = cb.createQuery(Order.class);
        Root<Order> c = q.from(Order.class);
        Join<Order, OrderStatus> statuses = c.join("orderStatus");

        if (id > 0) {
            Join<Order, User> users = c.join("user");

            q.select(c)
                    .where(
                            cb.or(
                                    cb.equal(statuses.get("status"), "delivered")
                            ),
                            cb.equal(users.get("id"), id)
                    );
        }else {
            q.select(c)
                    .where(
                            cb.or(
                                    cb.equal(statuses.get("status"), "delivered")
                            )
                    );
        }
        TypedQuery<Order> typedQuery = session.createQuery(q);
        return typedQuery.getResultList();
    }
}
