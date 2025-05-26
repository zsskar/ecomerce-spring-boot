package com.example.demo.user.service;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.User;

import java.util.List;

public interface UserService {

    User registerUser(UserDTO user);

    String loginUser(UserDTO user);

    void logoutUser(UserDTO user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
