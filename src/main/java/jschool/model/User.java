package jschool.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name="USER")
public  class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="fullName")
    private String fullName;

    @Column(name="email")
    private String email;


    @Column(name="birth")
    private Date birth;

    @Column(name="password")
    private String password;

    @Column(name="isAdmin")
    private boolean admin;


    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    private Address activeAddressId;

    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE,CascadeType.ALL })
    @JoinTable(
            name = "address_user",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
    private Set<Address> addresses = new HashSet();

    @ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.REMOVE,CascadeType.ALL })
    @JoinTable(
            name = "role_user",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles = new HashSet();

    @OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade=CascadeType.ALL)
    private Set<Order> orders;


    @Column(name="enabled")
    private Boolean enabled;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address getActiveAddressId() {
        return activeAddressId;
    }

    public void setActiveAddressId(Address activeAddressId) {
        this.activeAddressId = activeAddressId;
    }


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


    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString(){
        return "Name : " + this.getFullName() + "Addresses : " + Arrays.toString(this.getAddresses().toArray());
    }

    @Override
    public int hashCode(){
        if (Objects.isNull(this.email)){
            this.email = "";
        }
        return (Integer.valueOf(this.id) +
                this.email.hashCode());
    }
    @Override
    public boolean equals(Object o){
        if (o instanceof User){
            User u2 = (User) o;
            return u2 == this || (u2.id == this.id) ||
                    (u2.email == this.email);
        }
        return false;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
