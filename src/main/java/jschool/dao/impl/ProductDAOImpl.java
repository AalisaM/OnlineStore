package jschool.dao.impl;

import jschool.dao.ProductDAO;
import jschool.model.Category;
import jschool.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;
    @Override
    public List<Product> list() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Product> products = session.createQuery("FROM " + Product.class.getSimpleName() + " WHERE deleted = false").list();
        for(Product p : products){
            logger.info("Category List::"+p);
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = null;
        try {
            p =(Product) session.load(Product.class, id);
            logger.info("Category loaded successfully, Category details="+p);
        }catch (Exception e){
            System.out.println("in exception");
            return null;
        }
        return p;
    }

    @Override
    public void update(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(p);
        logger.info("Category updated successfully, Category Details="+p);
    }

    @Override
    public int add(Product p) {
        Session session = this.sessionFactory.getCurrentSession();
        int pid = (int) session.save(p);
        logger.info("Product saved successfully, Product Details="+p);
        return pid;
    }

    @Override
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, id);
        if(null != p && !p.isDeleted()){
            p.setDeleted(true);
            p.setAmount(0);
            session.update(p);
        }
        logger.info("Product deleted successfully, Product details="+p);
    }

    @Override
    public void addImageToProduct(int id, String file) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = (Product) session.load(Product.class, id);
        p.setImageSource(file);
        session.save(p);
        logger.info("Product updated successfully, User Details="+p);
    }

    @Override
    public  List<Product> getProductsByFilter(int price, int minPlayer, int maxPlayer, int categoryId){
        logger.info("=================in dao filter===============");
        logger.info(" "+ price + " " + maxPlayer + " " + minPlayer + " ");
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //select *  from product where price < price and maxPlayer < maxPlayer and minPlayer > minPlayer and Cat_id = Catid
        CriteriaQuery<Product> q = cb.createQuery(Product.class);
        Root<Product> c = q.from(Product.class);
        if (categoryId >= 0){
            Join<Product, Category> categories = c.join("category");
            q.select(c)
                    .where(
                    cb.lessThanOrEqualTo(c.get("price"), price),
                    cb.greaterThanOrEqualTo(c.get("minPlayerAmount"), minPlayer),
                    cb.lessThanOrEqualTo(c.get("maxPlayerAmount"), maxPlayer),
                            cb.equal(categories.get("id"),categoryId)
            );
        }else{
            q.select(c)
                    .where(
                            cb.lessThanOrEqualTo(c.get("price"), price),
                            cb.greaterThanOrEqualTo(c.get("minPlayerAmount"), minPlayer),
                            cb.lessThanOrEqualTo(c.get("maxPlayerAmount"), maxPlayer));
        }
        TypedQuery<Product> typedQuery = session.createQuery(q);
        logger.info(Arrays.toString(typedQuery.getResultList().toArray()));
        return typedQuery.getResultList();

    };

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
