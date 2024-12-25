package com.javadev.spring.ecommers.service.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

  private final UserRepository userRepository;

  public UserDetailsServiceImpl (UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userByEmail = this.userRepository.findByEmail(username);
    if (userByEmail.isPresent()) {
      User userFromDb = userByEmail.get();
      return new CurrentUser(userFromDb);
    }
    throw new UsernameNotFoundException("User with " + username + " does not exist");
  }
  
}
