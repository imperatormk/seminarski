package com.fikt.seminarski.auth;

import javax.ws.rs.core.SecurityContext;

import com.fikt.seminarski.model.User;

import java.security.Principal;

public class CustomSecurityContext implements SecurityContext {
  private final User principal;
  private final SecurityContext securityContext;

  public CustomSecurityContext(User principal, SecurityContext securityContext) {
    this.principal = principal;
    this.securityContext = securityContext;
  }

  @Override
  public Principal getUserPrincipal() {
    return principal;
  }

  @Override
  public boolean isUserInRole(String role) {
    return role.equals(principal.getRole());
  }

  @Override
  public boolean isSecure() {
    return securityContext.isSecure();
  }

  @Override
  public String getAuthenticationScheme() {
    return "CUSTOM_TOKEN";
  }
}