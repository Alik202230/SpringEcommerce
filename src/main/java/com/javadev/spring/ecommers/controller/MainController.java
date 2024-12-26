package com.javadev.spring.ecommers.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javadev.spring.ecommers.model.Category;
import com.javadev.spring.ecommers.model.Role;
import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.service.CategoryService;
import com.javadev.spring.ecommers.service.security.CurrentUser;
import com.javadev.spring.ecommers.utils.AuthenticationUtil;

@Controller
public class MainController {

  private final CategoryService categoryService;

  @Value("${product.upload.path}")
  private String uploadPath;

  public MainController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/")
  public String mainPage(ModelMap modelMap) {
    CurrentUser currentUser = AuthenticationUtil.getAuthenticatedUser.get();
    Set<Category> categories = this.categoryService.getAllCategories();

    if (currentUser != null && currentUser.getUser() != null) modelMap.put("user", currentUser.getUser());
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

  @GetMapping(value = "/get-image", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody byte[] getImage(@RequestParam("img") String img) throws IOException{
    File file = new File(uploadPath, img);
    if (file.exists()) {
      try(InputStream inputStream = new FileInputStream(file)) {
        return IOUtils.toByteArray(inputStream);
      }
    }
    return null;
  }

}
