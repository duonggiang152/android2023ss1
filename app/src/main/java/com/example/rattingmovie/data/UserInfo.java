package com.example.rattingmovie.data;

import java.io.Serializable;

public class UserInfo implements Serializable {
  private String name;

  public UserInfo(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
