package jschool.service;

import jschool.dto.UserDTO;
import jschool.model.Address;
import jschool.model.User;
import jschool.validator.EmailExistsException;
import jschool.validator.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService {
     List<User> listUsers();

    @Transactional
    List<UserDTO> listUsersDTO();

    User getUserById(int id);
     User getUserByEmail(String email);
     boolean isUserAdmin(int id);
     void addUser(User p);

     @Transactional
     void changeUserPassword(User user, String password);

     boolean checkIfValidOldPassword(User user, String oldPassword);

     void addUser(Message m, UserDTO p);
     void updateUser(User p);
     void updateUser(UserDTO p);

     void removeUser(int id);
     void setActiveAddressToUser(int idAddr, int idUser);
     void addAddressById(int idAddr, int idUser );
     void removeAddressById(int idAddr, int idUser);
     void addAddress(User p, Address a);
     User getCurUser();

     @Transactional
     UserDTO getCurUserDTO();

     Message addAddress(Message m, String json);
     Message setActiveAddress(Message m, String json);
     Message removeAddress(Message m, String json);
     Message editAddress(Message m, String json);
     Message changeUserPassword(Message m, String json);

     User loggedIn();

 @Transactional
 Message changeAdminStatus(Message m, String json);
}
