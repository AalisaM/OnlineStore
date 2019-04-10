package jschool.model;

import org.hibernate.engine.spi.CascadeStyle;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="PRODUCT")
public  class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="deleted")
    private Boolean deleted;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;


    @Column(name="weight")
    private int weight;

    @Column(name="volume")
    private int volume;

    @Column(name="amount")
    private int amount;

    @Column(name="minPlayerAmount")
    private int minPlayerAmount;

    @Column(name="maxPlayerAmount")
    private int maxPlayerAmount;

    @Column(name="imageSource")
    private String imageSource;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<CartItem> cartItem;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<OrderProduct> orderProducts;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<OrderHistory> orderHistories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Objects.isNull(name) ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMinPlayerAmount() {
        return minPlayerAmount;
    }

    public void setMinPlayerAmount(int minPlayerAmount) {
        this.minPlayerAmount = minPlayerAmount;
    }

    public int getMaxPlayerAmount() {
        return maxPlayerAmount;
    }

    public void setMaxPlayerAmount(int maxPlayerAmount) {
        this.maxPlayerAmount = maxPlayerAmount;
    }

    public String getImageSource() {
        return Objects.isNull(imageSource) ? "" : imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return  Objects.isNull(description) ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return (id == product.id) &&
                name.equals(product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<CartItem> getCartItem() {
        return cartItem;
    }

    public void setCartItem(Set<CartItem> cartItem) {
        this.cartItem = cartItem;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Set<OrderHistory> getOrderHistories() {
        return orderHistories;
    }

    public void setOrderHistories(Set<OrderHistory> orderHistories) {
        this.orderHistories = orderHistories;
    }

    public boolean isDeleted() {return deleted;}

    public void setDeleted(Boolean deleted) { this.deleted = deleted;}
}
