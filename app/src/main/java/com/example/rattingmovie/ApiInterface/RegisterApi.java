package com.example.rattingmovie.ApiInterface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApi {
  @POST("/register")
  Call<RegisterResponse> register(@Body RegisterData request);
}
