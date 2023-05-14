package com.example.rattingmovie.ApiInterface.movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
  @GET("/new-movie")
  Call<List<MovieResponse>> getMovies();
  @GET("/top-movie")
  Call<List<MovieResponse>> getTopMovies();
}
