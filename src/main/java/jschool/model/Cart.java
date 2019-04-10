package jschool.model;

import com.sun.security.ntlm.Client;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="CART")
public  class Cart {

    @Id
    @Column(name="user_id",unique = true, nullable = false)
    private int user_id;

    /** user with this associated settings */
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private Set<CartItem> cartItem;

    @Column(name = "totalPrice")
    private int totalPrice;

    @Column(name = "totalAmount")
    private int totalAmount;


    public Set<CartItem> getCartItem() {
        return cartItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCartItem(Set<CartItem> cartItem) {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return getUser_id() == cart.getUser_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser_id(), getTotalPrice(), getTotalAmount());
    }
}
