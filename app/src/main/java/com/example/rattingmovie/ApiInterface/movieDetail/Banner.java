package com.example.rattingmovie.ApiInterface.movieDetail;

import com.google.gson.annotations.SerializedName;

public class Banner {
  @SerializedName("type")
  private String type;

  @SerializedName("url")
  private String url;

  public Banner(String type, String url) {
    this.type = type;
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
