package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserById(Long id);

    List<User> searchUser(String searchTerm);

    User loginUser(String email, String password);


}

