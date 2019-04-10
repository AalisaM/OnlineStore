package jschool.service.impl;

import jschool.dao.PaymentStatusDAO;
import jschool.dao.ShippingTypeDAO;
import jschool.model.PaymentStatus;
import jschool.model.ShippingType;
import jschool.service.PaymentStatusService;
import jschool.service.ShippingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaymentStatusServiceImpl implements PaymentStatusService {
    private final PaymentStatusDAO paymentStatusDAO;

    @Autowired
    public PaymentStatusServiceImpl(PaymentStatusDAO paymentStatusDAO) {
        this.paymentStatusDAO = paymentStatusDAO;
    }


    @Override
    public void update(PaymentStatus p) {
        this.paymentStatusDAO.update(p);
    }

    @Override
    public void add(PaymentStatus p) {
        this.paymentStatusDAO.add(p);
    }

    @Override
    public void remove(int id) {
        this.paymentStatusDAO.remove(id);
    }

}
