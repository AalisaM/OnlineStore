package jschool.model;

import java.io.Serializable;
import java.util.List;

class ProductItemHistory{
    private int price;
    private int amount;
    private int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
public class ProductOrderHistory implements Serializable {
    private List<ProductItemHistory> items;

    public List<ProductItemHistory> getItems() {
        return items;
    }

    public void setItems(List<ProductItemHistory> items) {
        this.items = items;
    }
}
