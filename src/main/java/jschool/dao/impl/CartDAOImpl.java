package jschool.dao.impl;

import jschool.dao.CartDAO;
import jschool.model.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
@Repository
public class CartDAOImpl implements CartDAO {
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
    public Cart findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        try{
            Cart p = (Cart) session.load(Cart.class, new Integer(id));
            logger.info("Category loaded successfully, Category details="+p);
            return p;
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public CartItem findCartItemByProductId(int id, int cartid){
        logger.info("=================in cart item find by product filter===============");
        Session session = this.sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        //select *  from cartitems where Cat_id = Catid and product id
        CriteriaQuery<CartItem> q = cb.createQuery(CartItem.class);
        Root<CartItem> c = q.from(CartItem.class);
        Join<CartItem, Product> products = c.join("product");
        Join<CartItem, Cart> carts = c.join("cart");
        q.select(c)
                .where(
                        cb.equal(products.get("id"),id),
                        cb.equal(carts.get("user_id"),cartid)
                        );
        TypedQuery<CartItem> typedQuery = session.createQuery(q);
        logger.info(Arrays.toString(typedQuery.getResultList().toArray()));
        List<CartItem> result = typedQuery.getResultList();
        logger.info(result);

        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public void update(Cart c){
        logger.info("Cart saved successfulle="+c.getUser().toString() + c.getTotalAmount() + c.getTotalPrice());
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
        logger.info("Cart saved successfulle="+c.getUser().toString() + c.getTotalAmount() + c.getTotalPrice());

    }

    @Override
    public void add(Cart c) {
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println(c.getUser_id());
        System.out.println(c.getUser().getId());
        session.persist(c);

        logger.info("Cart saved successfulle="+c.getUser().toString() + c.getTotalAmount() + c.getTotalPrice());
    }

    @Override
    public void remove(Cart ci){
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(ci);
    }
}
