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
import org.springframework.web.multipart.MultipartFile;

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
  public String addProduct(@ModelAttribute Product product, MultipartFile multipartFile) {
    this.productService.addProduct(product, multipartFile);
    return "redirect:/admin";
  }

  @GetMapping("/category")
  public String getProductByCategoryName(@RequestParam("name") String categoryName, ModelMap modelMap) {
    List<Product> products = this.productService.getProductsByCategoryName(categoryName);
    modelMap.put("products", products);
    return "products";
  }

  @GetMapping("/edit")
  public String editProductPage(@RequestParam int id, ModelMap modelMap) {
    Product productById = this.productService.getProductById(id).get();
    if (productById != null) {
      modelMap.put("product", productById);
      return "admin/editProduct";
    }
    return "redirect:/admin";
  }

  @PostMapping("/edit")
  public String editProduct(@ModelAttribute Product product) {
    this.productService.editProduct(product);
    return "redirect:/admin";
  }

  @GetMapping("/delete")
  public String deleteProduct(@RequestParam int id) {
    this.productService.deleteProduct(id);
    return "redirect:/admin";
  }

}
