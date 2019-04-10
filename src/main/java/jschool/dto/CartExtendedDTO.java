package jschool.dto;

import java.util.LinkedHashMap;
import java.util.List;

public class CartExtendedDTO {
    private CartDTO curCart;
    private LinkedHashMap<String,Object> cartAnon;
    private List<PaymentTypeDTO> paymentType;
    private List<ShippingDTO> shippingType;
    private UserDTO user;

    public CartDTO getCurCart() {
        return curCart;
    }

    public void setCurCart(CartDTO curCart) {
        this.curCart = curCart;
    }

    public LinkedHashMap<String,Object> getCartAnon() {
        return cartAnon;
    }

    public void setCartAnon(LinkedHashMap<String,Object> cartAnon) {
        this.cartAnon = cartAnon;
    }

    public List<PaymentTypeDTO> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<PaymentTypeDTO> paymentType) {
        this.paymentType = paymentType;
    }

    public List<ShippingDTO> getShippingType() {
        return shippingType;
    }

    public void setShippingType(List<ShippingDTO> shippingType) {
        this.shippingType = shippingType;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
