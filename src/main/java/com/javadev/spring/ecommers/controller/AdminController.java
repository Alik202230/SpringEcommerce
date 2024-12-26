package com.javadev.spring.ecommers.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Product;
import com.javadev.spring.ecommers.model.Role;
import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.service.CategoryService;
import com.javadev.spring.ecommers.service.ProductService;
import com.javadev.spring.ecommers.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
  
  private final CategoryService categoryService;
  private final ProductService productService;
  private final UserService userService;

  public AdminController(CategoryService categoryService, ProductService productService, UserService userService) {
    this.categoryService = categoryService;
    this.productService = productService;
    this.userService = userService;
  }

  @GetMapping
  public String getMethodName(ModelMap modelMap) {
    List<Product> products = this.productService.getAllProducts();
    Set<Category> categories = this.categoryService.getAllCategories();
    List<User> users = this.userService.getAllUsersExceptAdmin(Role.USER);

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

    if (users != null) {
      modelMap.put("users", users);
    }

    return "admin/home";
  }

}
