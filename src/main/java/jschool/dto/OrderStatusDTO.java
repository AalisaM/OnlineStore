package jschool.dto;

import java.util.Objects;

public class OrderStatusDTO {
    private int id;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderStatusDTO)) return false;
        OrderStatusDTO that = (OrderStatusDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String type) {
        this.status = type;
    }
}
