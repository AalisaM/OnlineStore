package jschool.dao;

import jschool.model.Cart;
import jschool.model.CartItem;

import java.util.List;
import java.util.Set;

public interface CartItemDAO {
    void addCartItem(CartItem p);
    void updateCartItem(CartItem p);
    void removeCartItemByID(CartItem id);
}
