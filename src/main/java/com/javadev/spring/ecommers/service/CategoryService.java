package com.javadev.spring.ecommers.service;

import java.util.Optional;
import java.util.Set;

import com.javadev.spring.ecommers.model.Category;

public interface CategoryService {
  void addCategory(Category category);
  Optional<Category> getCategoryById(int id);
  Set<Category> getAllCategories();
}
