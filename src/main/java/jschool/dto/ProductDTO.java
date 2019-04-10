package jschool.dto;

import jschool.model.Category;
import jschool.model.OrderProduct;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private int price;
    private int weight;
    private int volume;
    private int amount;
    private int minPlayerAmount;
    private int maxPlayerAmount;
    private MultipartFile uploadFile;
    private String imageSource;
    private CategoryRawDTO category;

    private Set<OrderProductDTO> orderProducts;
    private List<CartItemDTO> cartItem;


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

    public CategoryRawDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryRawDTO category) {
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
        if (!(o instanceof ProductDTO)) return false;
        ProductDTO that = (ProductDTO) o;
        return getId() == that.getId() &&
                getPrice() == that.getPrice() &&
                getWeight() == that.getWeight() &&
                getVolume() == that.getVolume() &&
                getAmount() == that.getAmount() &&
                getMinPlayerAmount() == that.getMinPlayerAmount() &&
                getMaxPlayerAmount() == that.getMaxPlayerAmount() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getImageSource(), that.getImageSource()) &&
                Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getWeight(), getVolume(), getAmount(), getMinPlayerAmount(), getMaxPlayerAmount(), getImageSource(), getCategory());
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public Set<OrderProductDTO> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProductDTO> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<CartItemDTO> getCartItem() {
        return cartItem;
    }

    public void setCartItem(List<CartItemDTO> cartItem) {
        this.cartItem = cartItem;
    }
}
