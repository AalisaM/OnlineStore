package jschool.dto;

import java.util.Objects;

public class CategoryRawDTO {
    private int id;
    private String title;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryRawDTO)) return false;
        CategoryRawDTO that = (CategoryRawDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getParentId(), that.getParentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getParentId());
    }
}
