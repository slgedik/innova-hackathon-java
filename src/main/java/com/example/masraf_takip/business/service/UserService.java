package com.example.masraf_takip.business.service;

import java.util.List;

import com.example.masraf_takip.entities.User;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
}