package lk.ijse.userservice.service.impl;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.repo.UserRepo;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.VarList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    public int addUser(UserDTO userDTO) {
        if(userRepo.existsByUsername(userDTO.getUsername())) {
            return VarList.Not_Acceptable;
        }else {
            User user = modelMapper.map(userDTO, User.class);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepo.save(user);
            return VarList.Created;
        }
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return authorities;
    }

    @Override
    public UserDetails loadUserByUserName(String username) {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User Not Found" +username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthority(user)
        );
    }
    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
