package com.javadev.spring.ecommers.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Product;
import com.javadev.spring.ecommers.service.CategoryService;
import com.javadev.spring.ecommers.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
  
  private final CategoryService categoryService;
  private final ProductService productService;

  public AdminController(CategoryService categoryService, ProductService productService) {
    this.categoryService = categoryService;
    this.productService = productService;
  }

  @GetMapping
  public String getMethodName(ModelMap modelMap) {
    List<Product> products = this.productService.getAllProducts();
    Set<Category> categories = this.categoryService.getAllCategories();

    if (products != null) {
      modelMap.put("products", products);
    } else {
      modelMap.put("products", Collections.emptyList());
    }

    if (categories != null) {
      modelMap.put("categories", categories);
    } else {
      modelMap.put("categories", Collections.emptySet());
    }
    return "admin/home";
  }

}
