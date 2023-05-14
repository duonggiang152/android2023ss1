package com.example.rattingmovie.MovieDetail;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rattingmovie.ApiInterface.movieDetail.Banner;
import com.example.rattingmovie.ApiInterface.movieDetail.MovieDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieBannerAdapter extends FragmentStatePagerAdapter {
  private List<Banner> banners = new ArrayList<>();
  public MovieBannerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }
  public MovieBannerAdapter(@NonNull FragmentManager fm, int behavior, List<Banner> banners) {
    super(fm, behavior);
    this.banners = banners;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    System.out.println("position" + position);
      Banner banner = banners.get(position);
      if(banner.getType().equals("video")) return VideoViewBanner.newInstance("http://10.0.2.2:3000" + banner.getUrl());
      if(banner.getType().equals("img")) return ImageViewBanner.newInstance("http://10.0.2.2:3000" + banner.getUrl());
    return null;
  }

  @Override
  public int getCount() {
    return banners.size();
  }
}
