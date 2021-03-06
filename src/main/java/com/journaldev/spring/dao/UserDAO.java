package com.journaldev.spring.dao;

import com.journaldev.spring.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> listUsers();
    public User getUserById(int id);
    public User getUserByEmail(String email);
    public boolean isUserAdmin(int id);
    public void addUser(User p);
    public void updateUser(User p);
    public void removeUser(int id);
}
