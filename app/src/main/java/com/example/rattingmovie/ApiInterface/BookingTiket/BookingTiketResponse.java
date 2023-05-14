package com.example.rattingmovie.ApiInterface.BookingTiket;

import com.google.gson.annotations.SerializedName;

public class BookingTiketResponse {
  @SerializedName("theater")
  private String theater;
  @SerializedName("date")
  private String date;
  @SerializedName("time")
  private String time;
  @SerializedName("position")
  private String position;
  @SerializedName("buy")
  private boolean buy;
  @SerializedName("username")
  private String username;

  @SerializedName("price")
  private String price;

  // Constructor
  public BookingTiketResponse(String theater, String date, String time, String position, boolean buy, String username, String price) {
    this.theater = theater;
    this.date = date;
    this.time = time;
    this.position = position;
    this.buy = buy;
    this.username = username;
    this.price = price;
  }

  // Getters and Setters

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
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

  public boolean isBuy() {
    return buy;
  }

  public void setBuy(boolean buy) {
    this.buy = buy;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
