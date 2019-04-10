package jschool.dao;

import jschool.model.Address;
import jschool.model.User;

import java.util.List;
import java.util.Set;


public interface AddressDAO {
    void add(Address p);
    Address getById(int id);
    void update(int id, String address);
}
