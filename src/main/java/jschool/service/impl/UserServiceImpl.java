package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import jschool.dao.AddressDAO;
import jschool.dao.RoleDAO;
import jschool.dao.UserDAO;
import jschool.dto.UserDTO;
import jschool.model.Address;
import jschool.model.Role;
import jschool.model.User;
import jschool.service.DTOConverterService;
import jschool.service.UserService;
import jschool.validator.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private DTOConverterService dtoConverterService;
    @Autowired
    public void setDtoConverterService(DTOConverterService dtoConverterService) {
        this.dtoConverterService = dtoConverterService;
    }

    private RoleDAO roleDAO;
    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    private UserDAO userDAO;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private AddressDAO addressDAO;
    @Autowired
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    @Transactional
    public User getCurUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curU = userDAO.findByEmail(auth.getName());
        return curU;
    }

    @Override
    @Transactional
    public UserDTO getCurUserDTO() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curU = userDAO.findByEmail(auth.getName());
        return dtoConverterService.getUserDTO(curU);
    }

    /**
     * gets address from json, adds new or edits old.
     * */
    @Override
    @Transactional
    public Message addAddress(Message m, String json) {
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        addAddressById(jsonNode.get("address_id").asInt(), jsonNode.get("user_id").asInt());
        int userId = jsonNode.get("user_id").asInt();
        int addrID = jsonNode.get("address_id").asInt();
        String addr = jsonNode.get("address_str").asText();

        User u = getUserById(userId);
        System.out.println("user caught");
        if (!Objects.isNull(u)) {
            System.out.println("user caught not null" + u.getFullName());
            Address a = new Address();
            if (addrID > 0) {
                System.out.println("id > 0");

                a = this.addressDAO.getById(addrID);
                System.out.println("got addr " + a.getAddress());

            }
            if (Objects.isNull(a) || addrID <= 0) {
                System.out.println("add address");
                System.out.println(a);
                a = new Address();
                a.setAddress(addr);
                this.addressDAO.add(a);
                System.out.println("added");
            }
            addAddress(u, a);
        }
        m.getConfirms().add("address added successfully");
        return m;
    }

    @Override
    @Transactional
    public Message setActiveAddress(Message m, String json) {
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        setActiveAddressToUser(jsonNode.get("address_id").asInt(), jsonNode.get("user_id").asInt());
        m.getConfirms().add("active address set successfully");
        return m;
    }

    @Override
    @Transactional
    public Message removeAddress(Message m, String json) {
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        removeAddressById(jsonNode.get("address_id").asInt(), jsonNode.get("user_id").asInt());
        m.getConfirms().add("address removed successfully");
        return m;
    }

    @Override
    @Transactional
    public Message editAddress(Message m, String json) {
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        this.addressDAO.update(jsonNode.get("address_id").asInt(),jsonNode.get("address_str").asText());
        m.getConfirms().add("address edited successfully");
        return m;
    }

    /**
     * Gets password from json, checks old one and sets new.
     * */
    @Override
    @Transactional
    public Message changeUserPassword(Message m,String json) {
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        String oldPassword = "";
        String password = "";
        password = jsonNode.get("password").asText();
        oldPassword = jsonNode.get("oldPassword").asText();

        User user = getCurUser();

        if (!checkIfValidOldPassword(user, oldPassword)) {
            m.getErrors().add("Invalid Old password");
            return m;
        }
        changeUserPassword(user, password);
        m.getConfirms().add("Password has changed");
        return m;

    }


    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDAO.list();
    }

    @Override
    @Transactional
    public List<UserDTO> listUsersDTO(){
        return this.dtoConverterService.getUserDTOList(this.listUsers());
    }
    @Override
    @Transactional
    public User getUserById(int id) {
        return this.userDAO.findById(id);
    }

    @Override
    @Transactional
    public User getUserByEmail(String email) {
        return this.userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean isUserAdmin(int id) {
        return this.userDAO.isUserAdmin(id);
    }

    @Override
    @Transactional
    public void addUser(User p) {
        this.userDAO.add(p);
    }

    @Override
    @Transactional
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userDAO.updatePassword(user);
    }

    /**
     * checks user entered correct old password
     * */
    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    @Transactional
    public void addUser(Message message, UserDTO dto) {
        if (emailExists(dto.getEmail())) {
            message.getErrors().add("Email is already registered.");
            return;
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setBirth(dto.getBirth());
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleDAO.getByRole("USER"));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        this.userDAO.add(user);
    }

    private boolean emailExists(String email) {
        User user = userDAO.findByEmail(email);
        return !Objects.isNull(user);
    }

    @Override
    @Transactional
    public void updateUser(User p) {
        this.userDAO.update(p);
    }

    /**updated users personal info. for password, address and admin role tere are another methods*/
    @Override
    @Transactional
    public void updateUser(UserDTO p){
        System.out.println(p.getFullName());
        System.out.println(p.getEmail());
        System.out.println(p.getPassword());
        System.out.println(p.getBirth());
        System.out.println(p.getId());

        User u = this.getUserById(p.getId());
        u.setBirth(p.getBirth());
        u.setFullName(p.getFullName());
        u.setBirth(p.getBirth());
        updateUser(u);
    }
    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDAO.remove(id);
    }

    @Override
    @Transactional
    public void setActiveAddressToUser(int idAddr, int idUser) {
            this.userDAO.setActiveAddressToUser(idAddr, idUser);
    }

    @Override
    @Transactional
    public void addAddressById(int idAddr, int idUser) {
            this.userDAO.addAddressById(idAddr, idUser);
    }

    @Override
    @Transactional
    public void removeAddressById(int idAddr, int idUser) {
            this.userDAO.removeAddressById(idAddr, idUser);
    }


    @Override
    @Transactional
    public void addAddress(User p, Address a) {
        this.userDAO.addAddress(p, a);
    }

    /**
     * gets currently logged in user
     * */
    @Override
    @Transactional
    public User loggedIn(){
        return getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * Changes admin status and returns message object with error or success array for displaying in front.
     * */
    @Override
    @Transactional
    public Message changeAdminStatus(Message m, String json){
        JsonNode jsonNode = UtilService.parseJsonInput(json);
        if (Objects.isNull(jsonNode)){
            m.getErrors().add("wrong data");
            return m;
        }
        int id = jsonNode.get("id").asInt();
        boolean admin = jsonNode.get("admin").asBoolean();

        User u = this.getUserById(id);
        u.setAdmin(admin);
        this.userDAO.updateAdminStatus(u);
        m.getConfirms().add("User " + u.getFullName() + " admin status changed");
        return m;
    };

}
