package jschool.dao.impl;

import jschool.dao.CartItemDAO;
import jschool.dao.OrderProductDAO;
import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.Order;
import jschool.model.OrderProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public List<OrderProduct> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<OrderProduct> categories = session.createQuery("FROM " + OrderProduct.class.getSimpleName()).list();
        return categories;
    }

    @Override
    public OrderProduct findById(int id) {
        return null;
    }

    @Override
    public void update(OrderProduct p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }

    @Override
    public void add(OrderProduct p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void remove(OrderProduct id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(id);
    }


}
