package jschool.dto;

import jschool.model.CartItem;
import jschool.model.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class CartDTO {
    private int user_id;
    private UserDTO user;

    private List<CartItemDTO> cartItem;
    private int totalPrice;
    private int totalAmount;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CartItemDTO> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItemDTO> cartItem) {
        this.cartItem = cartItem;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
