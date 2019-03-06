package com.journaldev.spring.service;

import com.journaldev.spring.dao.AddressDAO;
import com.journaldev.spring.dao.ShippingTypeDAO;
import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.ShippingType;
import com.journaldev.spring.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService{
    private AddressDAO addressDAO;

    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    @Transactional
    public void  addAddress(Address p) {
        this.addressDAO.addAddress(p);
    }

    @Override
    @Transactional
    public void updateAddress(Address p) {
        this.addressDAO.updateAddress(p);
    }

    @Override
    @Transactional
    public List<Address> listAddresses() {
        return this.addressDAO.listAddresses();
    }

    @Override
    @Transactional
    public Address getById(int id) {
        return this.addressDAO.getById(id);
    }

    @Override
    public Set<User> getAllUsersById(int id) {
        return this.addressDAO.getAllUsersById(id);
    }

    @Override
    public Set<User> getAciveUsersById(int id) {
        return this.addressDAO.getAciveUsersById(id);
    }

    @Override
    @Transactional
    public void removeAddress(int id) {
        this.addressDAO.removeAddress(id);
    }
}
