package com.example.rattingmovie.ApiInterface.movieDetail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailResponse {
  @SerializedName("id")
  private String id;

  @SerializedName("movieId")
  private String movieId;

  @SerializedName("producer")
  private String producer;

  @SerializedName("name")
  private String name;

  @SerializedName("content")
  private String content;


  @SerializedName("banners")
  private List<Banner> banners;

  public MovieDetailResponse(String id, String movieId, String producer, String name, String content, List<Banner> banners) {
    this.id = id;
    this.movieId = movieId;
    this.producer = producer;
    this.name = name;
    this.content = content;
    this.banners = banners;

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMovieId() {
    return movieId;
  }

  public void setMovieId(String movieId) {
    this.movieId = movieId;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<Banner> getBanners() {
    return banners;
  }

  public void setBanners(List<Banner> banners) {
    this.banners = banners;
  }
}
