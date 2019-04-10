package jschool.service.impl;

import jschool.dao.OrderProductDAO;
import jschool.model.*;
import jschool.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class OrderProductServiceImpl implements OrderProductService {

    private OrderProductDAO orderProductDAO;

    @Autowired
    public void setOrderDAO(OrderProductDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public OrderProduct getById(int id) {
        return this.orderProductDAO.findById(id);
    }

    @Override
    public void update(OrderProduct p) {
        this.orderProductDAO.update(p);
    }


    @Override
    public void add(OrderProduct p) {
        this.orderProductDAO.add(p);
    }

    @Override
    public void remove(OrderProduct id) {
        this.orderProductDAO.remove(id);
    }
}
