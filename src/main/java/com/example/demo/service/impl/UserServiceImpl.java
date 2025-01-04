package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> searchUser(String searchTerm) {
      //  return userRepository.findByName(searchTerm);
        return userRepository.findByNameContainingOrEmailContainingOrPhoneContaining(searchTerm, searchTerm, searchTerm);
    }

    @Override
    public User loginUser(String email, String password) {
        List<User> user = userRepository.findByEmailAndPassword(email, password);
        if (!user.isEmpty()) {
            return user.get(0);
        }
        else{
            return null;
        }
    }


}
