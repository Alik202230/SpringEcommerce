package com.javadev.spring.ecommers.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.repository.CategoryRepository;
import com.javadev.spring.ecommers.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

  private final CategoryRepository categoryRepository;

  @Override
  public void addCategory(Category category) {
    Optional<Category> categoryOptional = this.categoryRepository.findById(category.getId());
    if (categoryOptional.isPresent()) throw new RuntimeException("Category " + category.getName() + " already exist");
    this.categoryRepository.save(category);
  }

  @Override
  public Optional<Category> getCategoryById(int id) {
    Optional<Category> categoryOptional = this.categoryRepository.findById(id);
    if (categoryOptional.isPresent()) return categoryOptional;
    return Optional.empty();
  }

  @Override
  public Set<Category> getAllCategories() {
    return new HashSet<>(this.categoryRepository.findAll());
  }
  
}
