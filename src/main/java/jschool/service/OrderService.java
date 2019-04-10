package jschool.service;

import jschool.dto.*;

import jschool.model.*;
import jschool.validator.Message;

import java.util.LinkedHashMap;
import java.util.List;

public interface OrderService {
    List<Order> listOrders();

    List<ShippingDTO> listShippingType();
    List<PaymentTypeDTO> listPaymentType();
    CartDTO getCurUserCart();
    void update(Order o);
    void add(Order c);
    Message add(OrderDTO c);

    Integer getCurUserId();

    OrderDTO convertToDTO(Order o);

    Message cancelOrder(OrderDTO o);
    Message updateOrder(OrderDTO o);
    List<OrderDTO> getUnpaidOrderList(boolean isAdmin);
    List<OrderDTO> getPaidOrderList(boolean isAdmin);
    List<OrderDTO> getProcessedOrderList(boolean isAdmin);


    List<OrderStatus> getNextOrderStatus(OrderStatus status);

    LinkedHashMap<OrderStatusDTO, List<OrderStatusDTO>> getStatusGraph();

    CartExtendedDTO getDTOForCart();

    CartExtendedDTO getDTOForCart(String anonCookie);

    CategorizedOrdersDTO getCategorizedOrdersDTO(boolean isAdmin);

}
