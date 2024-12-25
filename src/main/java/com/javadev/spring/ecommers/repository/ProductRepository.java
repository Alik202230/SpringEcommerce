package com.javadev.spring.ecommers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javadev.spring.ecommers.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
  List<Product> findProductsByCategoryName(String categoryName);
}
