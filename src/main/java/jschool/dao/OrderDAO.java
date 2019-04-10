package jschool.dao;

import jschool.dto.OrderDTO;
import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.Order;
import jschool.model.OrderProduct;

import java.util.List;
import java.util.Set;

public interface OrderDAO {
    List<Order> list();
    Order findById(int id);
    void update(Order o);
    void add(Order c);

    List<Order> getUnpaidOrderList(int id);
    List<Order> getPaidOrderList(int id);
    List<Order> getProcessedOrderList(int id);
}
