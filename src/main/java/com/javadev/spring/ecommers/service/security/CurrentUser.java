package com.javadev.spring.ecommers.service.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.javadev.spring.ecommers.model.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User{

  private transient User user;

  public CurrentUser(User user) {
    super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name())); 
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    CurrentUser other = (CurrentUser) obj;
    if (user == null) {
      if (other.user != null)
        return false;
    } else if (!user.equals(other.user))
      return false;
    return true;
  }

}
