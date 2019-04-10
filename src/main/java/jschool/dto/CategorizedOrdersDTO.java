package jschool.dto;

import java.util.List;

public class CategorizedOrdersDTO {
    private List<OrderDTO> unpaidOrders;
    private List<OrderDTO> paidOrders;
    private List<OrderDTO> processedOrders;
    private Integer userRoleId;

    public List<OrderDTO> getUnpaidOrders() {
        return unpaidOrders;
    }

    public void setUnpaidOrders(List<OrderDTO> unpaidOrders) {
        this.unpaidOrders = unpaidOrders;
    }

    public List<OrderDTO> getPaidOrders() {
        return paidOrders;
    }

    public void setPaidOrders(List<OrderDTO> paidOrders) {
        this.paidOrders = paidOrders;
    }

    public List<OrderDTO> getProcessedOrders() {
        return processedOrders;
    }

    public void setProcessedOrders(List<OrderDTO> processedOrders) {
        this.processedOrders = processedOrders;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
}
