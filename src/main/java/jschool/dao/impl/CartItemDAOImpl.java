package jschool.dao.impl;

import jschool.dao.CartItemDAO;
import jschool.model.Cart;
import jschool.model.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CartItemDAOImpl implements CartItemDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    @Override
    public void addCartItem(CartItem p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
    }

    @Override
    public void updateCartItem(CartItem p){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }

    @Override
    public void removeCartItemByID(CartItem ci){
        Session session = this.sessionFactory.getCurrentSession();
        session.remove(ci);
    }

}
