package jschool.dao;

import jschool.model.Cart;
import jschool.model.CartItem;

import java.util.List;
import java.util.Set;

public interface CartDAO{
        Cart findById(int id);
        CartItem findCartItemByProductId(int id, int cartid);
        void update(Cart c);
        void add(Cart c);
        void remove(Cart ci);
}
