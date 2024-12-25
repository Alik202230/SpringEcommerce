package com.javadev.spring.ecommers.utils;

import java.util.function.Supplier;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.javadev.spring.ecommers.service.security.CurrentUser;

public abstract class AuthenticationUtil {

  public static Supplier<CurrentUser> getAuthenticatedUser = () -> {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    if (principal instanceof CurrentUser currentUser) {
      return currentUser;
    }
    return null;
  };

}
