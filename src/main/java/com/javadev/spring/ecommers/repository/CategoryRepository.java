package com.javadev.spring.ecommers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javadev.spring.ecommers.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
  Optional<Category> findByName(String name);
}
