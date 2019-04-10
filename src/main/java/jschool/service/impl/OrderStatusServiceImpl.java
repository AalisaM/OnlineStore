package jschool.service.impl;

import jschool.dao.OrderStatusDAO;
import jschool.dao.ShippingTypeDAO;
import jschool.model.OrderStatus;
import jschool.model.ShippingType;
import jschool.service.OrderStatusService;
import jschool.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusDAO orderStatusDAO;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }


    @Override
    public void update(OrderStatus p) {
        this.orderStatusDAO.update(p);
    }

    @Override
    public void add(OrderStatus p) {
        this.orderStatusDAO.add(p);
    }

    @Override
    public void remove(int id) {
        this.orderStatusDAO.remove(id);
    }

}
