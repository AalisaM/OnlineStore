package com.journaldev.spring.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="address")
public class Address {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="address")
    private String address;

    @OneToMany(mappedBy="active_address_id")
    private Set<User> activeUsers;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "address_user",
            joinColumns = { @JoinColumn(name = "address_id") },
            inverseJoinColumns = { @JoinColumn(name = "client_id") }
    )
    Set<User> users = new HashSet();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Set<User> activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Set<User> getUsers() {
        return activeUsers;
    }

    public void setUsers(Set<User> activeUsers) {
        this.activeUsers = activeUsers;
    }
}
