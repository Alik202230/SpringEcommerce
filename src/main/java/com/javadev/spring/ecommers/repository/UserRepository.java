package com.javadev.spring.ecommers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javadev.spring.ecommers.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
  Optional<User> findByEmail(String email);
}
