package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserService {

    int addUser(UserDTO userDTO);
    UserDetails loadUserByUserName(String username);
    User findByUsername(String username);
}
