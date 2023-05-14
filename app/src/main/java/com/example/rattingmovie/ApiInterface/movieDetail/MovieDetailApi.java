package com.example.rattingmovie.ApiInterface.movieDetail;

import com.example.rattingmovie.ApiInterface.movie.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDetailApi {

  @GET("/movie-detail/{id}")
  Call<List<MovieDetailResponse>> getMovieDetail(@Path("id") String id);
}
