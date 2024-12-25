package com.javadev.spring.ecommers.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.repository.UserRepository;
import com.javadev.spring.ecommers.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void addUser(User user) {
    Optional<User> userByEmail = this.userRepository.findByEmail(user.getEmail());
    if (userByEmail.isPresent()) {
      throw new RuntimeException("User with " + user.getEmail() + " already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    this.userRepository.save(user);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
   return this.userRepository.findByEmail(email);
  }
}
