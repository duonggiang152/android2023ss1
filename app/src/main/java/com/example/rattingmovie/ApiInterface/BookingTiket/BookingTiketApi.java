package com.example.rattingmovie.ApiInterface.BookingTiket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookingTiketApi {
  @GET("/tiket/{id}")
  Call<List<BookingTiketResponse>> getTikets(@Path("id") String id);
}
