package jschool.dto;

import jschool.model.Address;
import com.sun.istack.internal.NotNull;
import jschool.validator.annotations.PasswordMatches;
import jschool.validator.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@PasswordMatches
public class UserDTO {
    private int id;
    @NotNull
    private String fullName;
    @NotNull
    @ValidEmail
    private String email;
    @NotNull
    private Date birth;
    @NotNull
    private String password;
    private AddressDTO activeAddressId;
    private Set<AddressDTO> addresses = new HashSet();
    private boolean isAdmin;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressDTO getActiveAddressId() {
        return activeAddressId;
    }

    public void setActiveAddressId(AddressDTO activeAddressId) {
        this.activeAddressId = activeAddressId;
    }


    public Set<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
