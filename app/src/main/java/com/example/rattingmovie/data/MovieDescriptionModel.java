package com.example.rattingmovie.data;

import java.io.Serializable;

public class MovieDescriptionModel implements Serializable {
  private static final long serialVersionUID = 1L;
  private String id;
  private String name;
  private String year;
  private String studio;
  private Double star;
  private String actor;
  private String imageMovie;

  public MovieDescriptionModel(String id, String name, String year, String studio, Double star, String actor, String imageMovie) {
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
