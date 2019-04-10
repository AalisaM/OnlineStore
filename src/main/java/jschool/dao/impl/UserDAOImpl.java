package jschool.dao.impl;

import jschool.dao.UserDAO;
import jschool.model.Address;
import jschool.model.Role;
import jschool.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    @Override
    public List<User> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> personsList = session.createQuery("FROM " + User.class.getSimpleName()).list();
        for(User p : personsList){
            logger.info("User List::"+p);
        }
        return personsList;
    }

    @Override
    public User findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = session.load(User.class, id);
        logger.info("User loaded successfully, User details="+p.getFullName());
        return p;
    }

    @Override
    public User findByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(User.class);
        Root<User> roles = cq.from(User.class);
        cq.where(cb.equal(roles.get("email"), email));

        List<User> result = session.createQuery(cq).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public boolean isUserAdmin(int id) {
        return false;
    }

    @Override
    public void add(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("User saved successfully, User Details="+p.getFullName());
    }

    @Override
    public void update(User p) {
        Session session = this.sessionFactory.getCurrentSession();
        User old = session.load(User.class, p.getId());
        old.setFullName(p.getFullName());
        old.setEmail(p.getEmail());
        System.out.println(p.getBirth());
        old.setBirth(p.getBirth());
        old.setPassword(p.getPassword());
        session.update(old);
        logger.info("User updated successfully, User Details="+old);
    }

    @Override
    public void updateAdminStatus(User p){
        Session session = this.sessionFactory.getCurrentSession();
        User old = session.load(User.class, p.getId());
        Role role = session.load(Role.class, 2);
        old.setAdmin(p.isAdmin());
        if (p.isAdmin()){
            old.getRoles().add(role);
        }else{
            old.getRoles().remove(role);
        }
        session.update(old);
    }

    @Override
    public void updatePassword(User p){
        System.out.println("in update password");
        System.out.println(p.getPassword());
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
    }
    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User p = session.load(User.class, id);
        if (!Objects.isNull(p)){
            session.delete(p);
        }
        logger.info("User deleted successfully, User details="+p);
    }

    @Override
    public void setActiveAddressToUser(int idAddr, int idUser) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, idUser);
        Address address = (Address)  session.get(Address.class, idAddr);
        user.setActiveAddressId(address);
        session.save(user);
    }

    @Override
    public void addAddressById(int idAddr, int idUser) {

    }

    @Override
    public void removeAddressById(int idAddr, int idUser) {
        Session session = this.sessionFactory.getCurrentSession();
        Address address = (Address)  session.get(Address.class, idAddr);
        User user = session.get(User.class, idUser);
        user.getAddresses().remove(address);
        if (user.getActiveAddressId().getId() == idAddr){
            user.setActiveAddressId(null);
        }
        session.save(user);
    }

    @Override
    public void addAddress(User p, Address a) {
        Session session = this.sessionFactory.getCurrentSession();
        try{
          //  session.beginTransaction();
            User user = session.get(User.class, p.getId());
            Address address = session.get(Address.class, a.getId());
            user.getAddresses().add(address);
            session.save(user);
          //  session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        logger.info("User added address successfully, User details="+ (session.get(User.class, p.getId())).toString());
    }
}
