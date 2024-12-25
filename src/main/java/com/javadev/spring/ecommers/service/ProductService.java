package com.javadev.spring.ecommers.service;

import java.util.List;
import java.util.Optional;

import com.javadev.spring.ecommers.model.Product;

public interface ProductService {
  void addProduct(Product product);
  Optional<Product> getProductById(int id);
  List<Product> getAllProducts();
  List<Product> getProductsByCategoryName(String categoryName);
}
