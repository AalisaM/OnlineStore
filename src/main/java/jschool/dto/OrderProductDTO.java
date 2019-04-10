package jschool.dto;

import jschool.model.Product;

import java.util.Objects;

public class OrderProductDTO {

    private int productid;

    private int amount;

    private int price;

    private String productName;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return "oPdto : " + amount + "; " + price + "; " + productid + ";" + productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductDTO)) return false;
        OrderProductDTO that = (OrderProductDTO) o;
        return getProductid() == that.getProductid() &&
                getAmount() == that.getAmount() &&
                getPrice() == that.getPrice() &&
                Objects.equals(getProductName(), that.getProductName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductid(), getAmount(), getPrice(), getProductName());
    }
}
