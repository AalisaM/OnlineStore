package jschool.dto;

import java.util.List;
import java.util.Set;

public class CategoryDTO {
    private int id;
    private String name;
    private Integer parentId;
    private Set<ProductRawDTO> products;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Set<ProductRawDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductRawDTO> products) {
        this.products = products;
    }
}
