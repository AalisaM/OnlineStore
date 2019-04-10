package jschool.dao;

import jschool.model.Cart;
import jschool.model.CartItem;
import jschool.model.OrderHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface OrderHistoryDAO {
        List<OrderHistory> list();
        void update(OrderHistory c);
        void add(OrderHistory c);
        void remove(OrderHistory c);

        List<OrderHistory> getOrdersByDatePeriod(LocalDate from, LocalDate to);
}
