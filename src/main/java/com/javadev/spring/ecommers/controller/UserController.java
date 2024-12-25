package com.javadev.spring.ecommers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javadev.spring.ecommers.model.User;
import com.javadev.spring.ecommers.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
  
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
 
  @GetMapping("/register")
  public String userPage() {
    return "register";
  }

  @PostMapping("/register")
  public String addUser(@ModelAttribute User user) {
    this.userService.addUser(user);
    return "redirect:/";
  }

}
