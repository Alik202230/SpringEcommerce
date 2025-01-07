package com.javadev.spring.ecommers.service.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Product;
import com.javadev.spring.ecommers.repository.CategoryRepository;
import com.javadev.spring.ecommers.repository.ProductRepository;
import com.javadev.spring.ecommers.service.ProductService;

import lombok.SneakyThrows;

@Service
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  @Value("${product.upload.path}")
  private String uploadPath;

  public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  @SneakyThrows
  public void addProduct(Product product, MultipartFile multipartFile) {
    final String fileName;
    if (!multipartFile.isEmpty()) {
      fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
      File file = new File(uploadPath, fileName);
      multipartFile.transferTo(file);
      product.setImage(fileName);
    }
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

  @Override
  public void editProduct(Product product) {
    this.productRepository.save(product);
  }

  @Override
  public void deleteProduct(int id) {
    Optional<Product> optionalProduct = this.productRepository.findById(id);
    if (optionalProduct.isPresent()) {
      this.productRepository.deleteById(optionalProduct.get().getId());
    }
  }
  
}
