package com.example.rattingmovie.ApiInterface;

import com.google.gson.annotations.SerializedName;

public class RegisterData {
  @SerializedName("username")
  private String username;

  @SerializedName("password")
  private String password;

  public RegisterData(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
