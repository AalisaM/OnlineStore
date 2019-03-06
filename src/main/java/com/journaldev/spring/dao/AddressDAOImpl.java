package com.journaldev.spring.dao;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.ShippingType;
import com.journaldev.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class AddressDAOImpl implements AddressDAO{
    private static final Logger logger = LoggerFactory.getLogger(ShippingTypeDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    @Override
    public List<Address> listAddresses() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Address> personsList = session.createQuery("FROM Address").list();
        for(Address p : personsList){
            logger.info("ShippingType List::"+p);
        }
        return personsList;
    }

    @Override
    public void addAddress(Address p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("ShippingType saved successfully, ShippingType Details="+p);
    }

    @Override
    public void updateAddress(Address p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("ShippingType updated successfully, ShippingType Details="+p);
    }

    @Override
    public Address getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Address p = (Address) session.load(Address.class, new Integer(id));
        logger.info("ShippingType loaded successfully, ShippingType details="+p);
        return p;
    }

    @Override
    public Set<User> getAllUsersById(int id) {
        return null;
    }

    @Override
    public Set<User> getAciveUsersById(int id) {
        return null;
    }

    @Override
    public void removeAddress(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Address p = (Address) session.load(Address.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("ShippingType deleted successfully, person details="+p);
    }
}
