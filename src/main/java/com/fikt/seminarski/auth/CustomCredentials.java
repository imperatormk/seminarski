package com.fikt.seminarski.auth;

import java.util.UUID;

public class CustomCredentials {
  private UUID token;
  private int userId;

 public UUID getToken() {
    return token;
  }

  public void setToken(UUID token) {
    this.token = token;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getUserId() {
    return userId;
  }
}