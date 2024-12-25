package com.javadev.spring.ecommers.controller;

import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Role;
import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.service.CategoryService;
import com.javadev.spring.ecommers.service.security.CurrentUser;
import com.javadev.spring.ecommers.utils.AuthenticationUtil;

@Controller
public class MainController {

  private final CategoryService categoryService;

  public MainController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/")
  public String mainPage(ModelMap modelMap) {
    CurrentUser currentUser = AuthenticationUtil.getAuthenticatedUser.get();
    Set<Category> categories = this.categoryService.getAllCategories();

    if (currentUser != null && currentUser.getUser() != null) modelMap.put("user", currentUser);
    if (categories != null) modelMap.put("categories", categories);

    return "index";
  }
  
  @GetMapping("/loginPage")
  public String loginPage() {
    CurrentUser currentUser = AuthenticationUtil.getAuthenticatedUser.get();
    if (currentUser != null && currentUser.getUser() != null) return "redirect:/";
    return "login";
  }

  @GetMapping("/loginSuccess")
  public String loginSuccess(@AuthenticationPrincipal CurrentUser currentUser) {
    if (currentUser != null && currentUser.getUser() != null) {
      User user = currentUser.getUser();
      if (user.getRole() == Role.ADMIN) {
        return "redirect:/admin";
      }
    }
    return "redirect:/";
  }

}
