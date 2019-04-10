package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jschool.dao.CartDAO;
import jschool.dao.CartItemDAO;
import jschool.dao.ProductDAO;
import jschool.dao.UserDAO;
import jschool.model.*;
import jschool.service.CartService;
import jschool.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {


    @Override
    public List<OrderHistory> list() {
        return null;
    }

    @Override
    public OrderHistory findById(int id) {
        return null;
    }

    @Override
    public void update(OrderHistory c) {

    }

    @Override
    public void add(OrderHistory c) {

    }

    @Override
    public void remove(OrderHistory c) {

    }
}
