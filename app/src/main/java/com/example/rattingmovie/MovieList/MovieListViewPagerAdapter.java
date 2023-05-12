package com.example.rattingmovie.MovieList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.rattingmovie.data.Type.ListFragmentType;

public class MovieListViewPagerAdapter extends FragmentStatePagerAdapter {
  public MovieListViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return ListMovieFragment.newInstance(ListFragmentType.NEW);
      case 1:
        return ListMovieFragment.newInstance(ListFragmentType.HIGHESTVIEW);
    }
    return null;
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    String title = "";
    switch (position) {
      case 0:
        title = "New Movie";
          break;
      case 1:
        title = "Top View";
          break;
    }
    return title;
  }
}
