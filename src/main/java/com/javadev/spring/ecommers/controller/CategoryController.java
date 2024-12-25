package com.javadev.spring.ecommers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
  
  private final CategoryService categoryService;

  @GetMapping("/add")
  public String categoryPage() {
    return "admin/category";
  }

  @PostMapping("/add")
  public String addCategory(@ModelAttribute Category category) {
    this.categoryService.addCategory(category);
    return "redirect:/admin";
  } 
}
