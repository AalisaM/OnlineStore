package jschool.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class ProductRawDTO {
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
    private CategoryDTO category;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRawDTO)) return false;
        ProductRawDTO that = (ProductRawDTO) o;
        return getId() == that.getId() &&
                getPrice() == that.getPrice() &&
                getWeight() == that.getWeight() &&
                getVolume() == that.getVolume() &&
                getAmount() == that.getAmount() &&
                getMinPlayerAmount() == that.getMinPlayerAmount() &&
                getMaxPlayerAmount() == that.getMaxPlayerAmount() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getUploadFile(), that.getUploadFile()) &&
                Objects.equals(getImageSource(), that.getImageSource()) &&
                Objects.equals(getCategory(), that.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getWeight(), getVolume(), getAmount(), getMinPlayerAmount(), getMaxPlayerAmount(), getUploadFile(), getImageSource(), getCategory());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public String getImageSource() {
        return imageSource;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
}
