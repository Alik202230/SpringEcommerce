package com.javadev.spring.ecommers.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Product;
import com.javadev.spring.ecommers.repository.CategoryRepository;
import com.javadev.spring.ecommers.repository.ProductRepository;
import com.javadev.spring.ecommers.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public void addProduct(Product product) {
    this.productRepository.save(product);
  }

  @Override
  public Optional<Product> getProductById(int id) {
    Optional<Product> producOptional = this.productRepository.findById(id);
    if (!producOptional.isPresent()) throw new RuntimeException("Product does not exist");
    return producOptional;
  }

  @Override
  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  @Override
  public List<Product> getProductsByCategoryName(String categoryName) {
    Category category = this.categoryRepository.findByName(categoryName)
        .orElseThrow(() -> new RuntimeException("Category not found"));
    return this.productRepository.findProductsByCategoryName(category.getName());
  }
  
}
