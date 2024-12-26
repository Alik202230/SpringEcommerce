package com.javadev.spring.ecommers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.javadev.spring.ecommers.model.Product;

public interface ProductService {
  void addProduct(Product product, MultipartFile multipartFile);
  Optional<Product> getProductById(int id);
  List<Product> getAllProducts();
  List<Product> getProductsByCategoryName(String categoryName);
  void editProduct(Product product);
  void deleteProduct(int id);
}
