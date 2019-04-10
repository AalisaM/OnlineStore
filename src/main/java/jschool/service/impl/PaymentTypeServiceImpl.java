package jschool.service.impl;

import jschool.dao.PaymentTypeDAO;
import jschool.dao.ShippingTypeDAO;
import jschool.model.PaymentType;
import jschool.model.ShippingType;
import jschool.service.PaymentTypeService;
import jschool.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeDAO paymentTypeDAO;

    @Autowired
    public PaymentTypeServiceImpl(PaymentTypeDAO paymentTypeDAO) {
        this.paymentTypeDAO = paymentTypeDAO;
    }


    @Override
    public void update(PaymentType p) {
        this.paymentTypeDAO.update(p);
    }

    @Override
    public void add(PaymentType p) {
        this.paymentTypeDAO.add(p);
    }

    @Override
    public void remove(int id) {
        this.paymentTypeDAO.remove(id);
    }

}
