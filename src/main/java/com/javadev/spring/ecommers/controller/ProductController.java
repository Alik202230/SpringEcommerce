package com.javadev.spring.ecommers.controller;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Product;
import com.javadev.spring.ecommers.service.CategoryService;
import com.javadev.spring.ecommers.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
  
  private final ProductService productService;
  private final CategoryService categoryService;

  public ProductController(ProductService productService, CategoryService categoryService) {
    this.productService = productService;
    this.categoryService = categoryService;
  }

  @GetMapping("/add")
  public String productPage(ModelMap modelMap) {
    Set<Category> categories = this.categoryService.getAllCategories();
    modelMap.put("categories", categories);
    return "admin/product";
  }

  @PostMapping("/add")
  public String addProduct(@ModelAttribute Product product) {
    this.productService.addProduct(product);
    return "redirect:/admin";
  }

  @GetMapping("/category")
  public String getProductByCategoryName(@RequestParam("name") String categoryName, ModelMap modelMap) {
    List<Product> products = this.productService.getProductsByCategoryName(categoryName);
    modelMap.put("products", products);
    return "products";
  }

}
