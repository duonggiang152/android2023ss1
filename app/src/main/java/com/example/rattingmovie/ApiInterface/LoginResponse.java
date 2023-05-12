package com.example.rattingmovie.ApiInterface;
import com.google.gson.annotations.SerializedName;
public class LoginResponse {
  @SerializedName("jwt")
  private String jwt;

  public LoginResponse(String jwt) {
    this.jwt = jwt;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
