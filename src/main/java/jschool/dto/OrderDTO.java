package jschool.dto;

import jschool.model.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OrderDTO {

    private int id;
    private String email;
    private String address;
    private PaymentTypeDTO paymentType;
    private PaymentStatusDTO paymentStatus;
    private ShippingDTO shippingType;
    private OrderStatusDTO orderStatus;
    private int totalPrice;
    private int amount;
    private List<OrderProductDTO> orderProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO)) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return getId() == orderDTO.getId() &&
                getTotalPrice() == orderDTO.getTotalPrice() &&
                getAmount() == orderDTO.getAmount() &&
                Objects.equals(getEmail(), orderDTO.getEmail()) &&
                Objects.equals(getAddress(), orderDTO.getAddress()) &&
                Objects.equals(paymentType, orderDTO.paymentType) &&
                Objects.equals(paymentStatus, orderDTO.paymentStatus) &&
                Objects.equals(shippingType, orderDTO.shippingType) &&
                Objects.equals(orderStatus, orderDTO.orderStatus) &&
                Objects.equals(getOrderProducts(), orderDTO.getOrderProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getAddress(), paymentType, paymentStatus, shippingType, orderStatus, getTotalPrice(), getAmount(), getOrderProducts());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String toString(){
        return (Objects.isNull(email) ? "" : email) + ": " +
                (Objects.isNull(address) ? "" : address) + ";\n PaymentType: " +
                (Objects.isNull(paymentType) ? "" : paymentType.getType()) + "; status: " +
                (Objects.isNull(paymentStatus) ? "" : paymentStatus) + ";" +
                "shipping : " +  (Objects.isNull(shippingType) ? "" : shippingType) + "; order status : " +
                (Objects.isNull(orderStatus) ? "" : orderStatus) + " ; total price : " +
                (Objects.isNull(totalPrice) ? "" : totalPrice)  + "\n Products :" +
                (Objects.isNull(orderProducts) ? "" : orderProducts.toString());
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ShippingDTO getShippingType() {
        return shippingType;
    }

    public OrderStatusDTO getOrderStatus() {
        return orderStatus;
    }

    public PaymentStatusDTO getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentTypeDTO getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentTypeDTO paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentStatus(PaymentStatusDTO paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setShippingType(ShippingDTO shippingType) {
        this.shippingType = shippingType;
    }

    public void setOrderStatus(OrderStatusDTO orderStatus) {
        this.orderStatus = orderStatus;
    }
}
