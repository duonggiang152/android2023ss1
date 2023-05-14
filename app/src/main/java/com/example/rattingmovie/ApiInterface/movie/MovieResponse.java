package com.example.rattingmovie.ApiInterface.movie;

import com.google.gson.annotations.SerializedName;

public class MovieResponse {
  @SerializedName("id")
  private String id;
  @SerializedName("name")
  private String name;
  @SerializedName("year")
  private String year;
  @SerializedName("studio")
  private String studio;
  @SerializedName("star")
  private Double star;
  @SerializedName("actor")
  private String actor;
  @SerializedName("imageMovie")
  private String imageMovie;

  public MovieResponse(String id, String name, String year, String studio, Double star, String actor, String imageMovie) {
    this.id = id;
    this.name = name;
    this.year = year;
    this.studio = studio;
    this.star = star;
    this.actor = actor;
    this.imageMovie = imageMovie;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getStudio() {
    return studio;
  }

  public void setStudio(String studio) {
    this.studio = studio;
  }

  public Double getStar() {
    return star;
  }

  public void setStar(Double star) {
    this.star = star;
  }

  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }

  public String getImageMovie() {
    return imageMovie;
  }

  public void setImageMovie(String imageMovie) {
    this.imageMovie = imageMovie;
  }
}
