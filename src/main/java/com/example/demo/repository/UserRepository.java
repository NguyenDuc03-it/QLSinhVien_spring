package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByNameContainingOrEmailContainingOrPhoneContaining(String name, String email, String phone);
    List<User> findByName(String name);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByNameAndPassword(String name, String password);
}

