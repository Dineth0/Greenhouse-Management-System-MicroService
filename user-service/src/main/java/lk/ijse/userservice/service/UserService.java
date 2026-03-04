package lk.ijse.userservice.service;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {

    int addUser(UserDTO userDTO);
    UserDetails loadUserByUserName(String username);
    User findByUsername(String username);
    UserDTO loadUserDetailsByUsername(String username);

    }
