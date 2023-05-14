package com.example.rattingmovie.Tiket;

public class TiketModel {
  private String id;
  private String title;
  private String theater;

  private String date;
  private String time;
  private String position;

  public TiketModel(String id, String title, String theater, String date, String time, String position) {
    this.id = id;
    this.title = title;
    this.theater = theater;
    this.date = date;
    this.time = time;
    this.position = position;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTheater() {
    return theater;
  }

  public void setTheater(String theater) {
    this.theater = theater;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
