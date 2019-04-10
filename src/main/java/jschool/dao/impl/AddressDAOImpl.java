package jschool.dao.impl;

import jschool.dao.AddressDAO;
import jschool.model.Address;
import jschool.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAOImpl implements AddressDAO {
    private static final Logger logger = LoggerFactory.getLogger(ShippingTypeDAOImpl.class);

    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }


    @Override
    public void add(Address p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Address saved successfulle="+p.getAddress() + " " + p.getUsers());
    }



    @Override
    public Address getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        Address p = (Address) session.load(Address.class, new Integer(id));
        logger.info("Address loaded successfully, Address details="+p.getAddress());
        return p;
    }


    @Override
    public void update(int id, String address) {
        Session session = this.sessionFactory.getCurrentSession();
            Address a = (Address) session.get(Address.class, new Integer(id));
            if (Objects.isNull(a)){
                a = new Address();
                a.setAddress(address);
                session.persist(a);
                return;
            }
            a.setAddress(address);
            session.save(a);
        }
}
