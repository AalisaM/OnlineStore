package jschool.dao.impl;

import jschool.dao.CategoryDAO;
import jschool.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    @Override
    public Category findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category p = (Category) session.load(Category.class, new Integer(id));
        logger.info("Category loaded successfully, Category details="+p);
        return p;
    }

    @Override
    public List<Category> listCategory() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categories = session.createQuery("FROM " + Category.class.getSimpleName()).list();
        for(Category p : categories){
            logger.info("Category List::"+p);
        }
        return categories;
    }

    @Override
    public void update(Category p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Category updated successfully, Category Details="+p);
    }

    @Override
    public void add(Category p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(p);
        logger.info("Category saved successfully, Category Details="+p);
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category p = (Category) session.load(Category.class, new Integer(id));
        if(null != p){
            session.delete(p);
        }
        logger.info("Category deleted successfully, Category details="+p);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
