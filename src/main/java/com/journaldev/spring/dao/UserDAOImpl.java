package com.journaldev.spring.dao;

import com.journaldev.spring.model.ShippingType;
import com.journaldev.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(ShippingTypeDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("FROM User").list();
        System.out.println(Arrays.toString(session.createQuery("FROM User").list().toArray()));
        List<User> personsList = session.createQuery("FROM User").list();
        for(User p : personsList){
            logger.info("ShippingType List::"+p);
        }
        return personsList;
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        logger.info("ShippingType loaded successfully, ShippingType details="+p);
        return p;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean isUserAdmin(int id) {
        return false;
    }

    @Override
    public void addUser(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("ShippingType saved successfully, ShippingType Details="+p);
    }

    @Override
    public void updateUser(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("ShippingType updated successfully, ShippingType Details="+p);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = (User) session.load(User.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("ShippingType deleted successfully, person details="+p);
    }
}
