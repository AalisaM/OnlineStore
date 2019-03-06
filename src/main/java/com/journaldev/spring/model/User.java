package com.journaldev.spring.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="fullName")
    private String fullName;
    @Column(name="birth")
    private Date birth;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @ManyToOne
    @JoinColumn(name="address_id", nullable=false)
    private Address active_address_id;

    @Column(name="isAdmin")
    private boolean isAdmin;


    @ManyToMany(mappedBy = "users")
    private Set<Address> addresses = new HashSet();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getActive_address_id() {
        return active_address_id;
    }

    public void setActive_address_id(Address active_address_id) {
        this.active_address_id = active_address_id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

 //   public Set<Address> getAddresses() {
 //       return addresses;
 //   }
}
