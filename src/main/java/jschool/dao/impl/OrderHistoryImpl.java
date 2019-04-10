package jschool.dao.impl;

import jschool.dao.CartDAO;
import jschool.dao.OrderHistoryDAO;
import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.OrderHistory;
import jschool.model.Product;
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
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class OrderHistoryImpl implements OrderHistoryDAO {
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
    public List<OrderHistory> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<OrderHistory> categories = session.createQuery("FROM " + OrderHistory.class.getSimpleName()).list();
        for(OrderHistory p : categories){
            logger.info("OrderHistory List::"+p);
        }
        return categories;
    }


    @Override
    public void update(OrderHistory c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("OrderHistory updated successfully, Order Details="+c);
    }

    @Override
    public void add(OrderHistory c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
        logger.info("OrderHistory saved successfully, Category Details="+c);
    }

    @Override
    public void remove(OrderHistory c) {

    }

    @Override
    public List<OrderHistory> getOrdersByDatePeriod(LocalDate from, LocalDate to) {
        Session session = this.sessionFactory.getCurrentSession();
        Date dbFrom = Date.valueOf(from);
        Date dbTo = Date.valueOf(to);
        System.out.println(to);
//        List<OrderHistory> orders = session.createQuery("FROM " + OrderHistory.class.getSimpleName() + " H "
//            + " WHERE H.date BETWEEN " + dbFrom
//            + " AND " + dbTo)
//            .list();

        List<OrderHistory> orders = session.createQuery("SELECT H FROM " + OrderHistory.class.getSimpleName() + " H WHERE H.date BETWEEN :fromDate AND :toDate ")
                .setParameter("fromDate", dbFrom)
                .setParameter("toDate", dbTo)
                .list();


        for(Object p : orders){
            logger.info("Paid OrderHistory by date period List::"+p);
        }
        return orders;
    }
}
