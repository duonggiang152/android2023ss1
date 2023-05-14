package com.example.rattingmovie.MovieDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rattingmovie.ApiInterface.movieDetail.Banner;
import com.example.rattingmovie.ApiInterface.movieDetail.MovieDetailApi;
import com.example.rattingmovie.ApiInterface.movieDetail.MovieDetailResponse;
import com.example.rattingmovie.BookingTicket.BookingTicketActivity;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.MovieDescriptionModel;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {
  private Button bookingTiket;
  private TextView movie_tittle;
  private TextView movie_producer;
  private TextView movie_content;
  private ViewPager viewPager;
  private List<Banner> banners = new ArrayList<>();
  MovieDescriptionModel movieDescriptionModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movie_detail);

    movie_tittle = findViewById(R.id.movie_tittle);
    movie_producer = findViewById(R.id.movie_producer);
    movie_content = findViewById(R.id.movie_content);

    bookingTiket = findViewById(R.id.booking_tiket);
     viewPager = findViewById(R.id.movie_banner);
    MovieBannerAdapter movieBannerAdapter = new MovieBannerAdapter(
      getSupportFragmentManager(),
      FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    viewPager.setAdapter(movieBannerAdapter);
    viewPager.setCurrentItem(0);
    Intent bookingAvtivity = new Intent(this, BookingTicketActivity.class);
    movieDescriptionModel = (MovieDescriptionModel) getIntent().getSerializableExtra("movie");
    bookingTiket.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        bookingAvtivity.putExtra("movie", movieDescriptionModel);
        startActivity(bookingAvtivity);
      }
    });

    TextView textView = findViewById(R.id.movie_tittle);
    textView.setText(movieDescriptionModel.getName());
    getSupportActionBar().hide();


  }

  @Override
  protected void onStart() {
    super.onStart();
    String BASE_URL = "http://10.0.2.2:3000";
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    String movieId = movieDescriptionModel.getId(); // replace with the actual movie ID
    MovieDetailApi movieDetailApi = retrofit.create(MovieDetailApi.class);
    Call<List<MovieDetailResponse>> call = movieDetailApi.getMovieDetail(movieId);
    call.enqueue(new Callback<List<MovieDetailResponse>>() {
      @Override
      public void onResponse(Call<List<MovieDetailResponse>> call, Response<List<MovieDetailResponse>> response) {
        if (response.isSuccessful()) {
          List<MovieDetailResponse> movieDetailResponses = response.body();
          if (movieDetailResponses.size() > 0) {
            MovieDetailResponse movieDetailResponse = movieDetailResponses.get(0);
            movie_tittle.setText(movieDetailResponse.getName());
            movie_content.setText(movieDetailResponse.getContent());
            movie_producer.setText(movieDetailResponse.getProducer());
            banners = movieDetailResponse.getBanners();
            MovieBannerAdapter movieBannerAdapter = new MovieBannerAdapter(
              getSupportFragmentManager(),
              FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, banners);
            viewPager.setAdapter(movieBannerAdapter);
            viewPager.setCurrentItem(0);
          }
        } else {
          // handle error
        }
      }

      @Override
      public void onFailure(Call<List<MovieDetailResponse>> call, Throwable t) {
        // handle error
      }
    });
  }
}
