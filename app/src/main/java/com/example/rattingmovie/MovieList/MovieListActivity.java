package com.example.rattingmovie.MovieList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.rattingmovie.R;
import com.example.rattingmovie.data.UserInfo;
import com.google.android.material.tabs.TabLayout;

public class MovieListActivity extends AppCompatActivity {
  private UserInfo userInfo;
  private top_bar_main topBar;

  private TabLayout tabLayout;
  private ViewPager viewPager;
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_movie_list);
    viewPager = findViewById(R.id.list_movie_pager);
    tabLayout = findViewById(R.id.tab_layout_list_movie);
    MovieListViewPagerAdapter movieListViewPagerAdapter = new MovieListViewPagerAdapter(
      getSupportFragmentManager(),
      FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    );
    viewPager.setAdapter(movieListViewPagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
//    userInfo = (UserInfo) getIntent().getSerializableExtra("user_info");

//    Bundle bundle = new Bundle();
//    Log.d("UserInfo", "userInfo: " + userInfo);
//    bundle.putSerializable("user_info", userInfo.getName());
//    topBar = new top_bar_main();
//    topBar.setArguments(bundle);


//    FragmentManager fragmentManager = getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//    fragmentTransaction.replace(R.id.fragment_app_top_bar, topBar);
//    fragmentTransaction.commit();
    getSupportActionBar().hide();


  }
}
