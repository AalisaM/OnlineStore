package com.journaldev.spring.dao;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.ShippingType;
import com.journaldev.spring.model.User;

import java.util.List;
import java.util.Set;

public interface AddressDAO {

    public List<Address> listAddresses();

    public void addAddress(Address p);
    public void updateAddress(Address p);
    public Address getById(int id);
    public Set<User> getAllUsersById(int id);
    public Set<User> getAciveUsersById(int id);
    public void removeAddress(int id);
}
