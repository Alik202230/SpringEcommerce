package com.javadev.spring.ecommers.service;

import java.util.Optional;

import com.javadev.spring.ecommers.model.User;

public interface UserService {
  void addUser(User user);
  Optional<User> getUserByEmail(String email);
}
