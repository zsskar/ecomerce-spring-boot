package com.example.demo.user.service;

import com.example.demo.enums.Roles;
import com.example.demo.errors.EcomErrorDetails;
import com.example.demo.errors.ErrorCodes;
import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UsersRepo usersRepo;
    @Override
    public User registerUser(UserDTO user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());

        // Handle roles
        Set<Roles> userRoles = new HashSet<>();

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            // If no roles provided, assign default USER role
            userRoles.add(Roles.User);
        } else {
            // Convert string roles to ERole enum
            for (String roleStr : user.getRoles()) {
                try {
                    userRoles.add(Roles.fromString(roleStr));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid role: " + roleStr);
                }
            }
        }

        newUser.setRoles(userRoles);
        return usersRepo.save(newUser);
    }

    @Override
    public String loginUser(UserDTO user) {
        return null;
    }

    @Override
    public void logoutUser(UserDTO user) {

    }

    @Override
    public User getUserById(Long id) {
        return usersRepo.findById(id)
                .orElseThrow(() -> new EcomErrorDetails(
                        ErrorCodes.USER_NOT_FOUND,
                        "User not found",
                        "User with ID " + id + " does not exist"));
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        User user = usersRepo.findById(id)
                .orElseThrow(() -> new EcomErrorDetails(
                        ErrorCodes.USER_NOT_FOUND,
                        "User not found",
                        "User with ID " + id + " does not exist"));
        usersRepo.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
