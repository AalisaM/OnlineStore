package jschool.service;

import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.OrderHistory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface OrderHistoryService {
     List<OrderHistory> list();
     OrderHistory findById(int id);
     void update(OrderHistory c);
     void add(OrderHistory c);
     void remove(OrderHistory c);

}
