package com.example.rattingmovie.ApiInterface;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
  @SerializedName("message")
  private String message;

  public RegisterResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
