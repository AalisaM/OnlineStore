package jschool.dao.impl;

import jschool.dao.RoleDAO;
import jschool.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getByRole(String r) {
        Session session = this.sessionFactory.getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Role.class);
        Root<Role> roles = cq.from(Role.class);
        cq.where(cb.equal(roles.get("role"), r));

        List<Role> result = session.createQuery(cq).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }

}