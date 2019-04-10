package jschool.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="ADDRESS")
public class Address {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="address")
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address1 = (Address) o;
        return getId() == address1.getId() &&
                Objects.equals(getAddress(), address1.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress());
    }

    @OneToMany(mappedBy="activeAddressId", cascade=CascadeType.ALL)
    private Set<User> activeUsers;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "addresses", cascade=CascadeType.ALL)
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

    @Override
    public String toString(){
        return this.address;
    }

}
