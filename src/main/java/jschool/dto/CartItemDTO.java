package jschool.dto;

import jschool.model.Cart;
import jschool.model.Product;

import javax.persistence.*;

public class CartItemDTO {
    private int id;
    private ProductRawDTO product;
    private int amount;
    private int price;

    public ProductRawDTO getProduct() {
        return product;
    }

    public void setProduct(ProductRawDTO product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
