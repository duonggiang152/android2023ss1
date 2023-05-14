package com.example.rattingmovie.MovieList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rattingmovie.R;
import com.example.rattingmovie.Tiket.TiketActivity;
import com.example.rattingmovie.UserInfo.UserInfoActivity;
import com.example.rattingmovie.data.UserInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MovieListActivity extends AppCompatActivity {
  private UserInfo userInfo;
  private top_bar_main topBar;

  private TabLayout tabLayout;
  private ViewPager viewPager;

  DrawerLayout drawerLayout;
  NavigationView navigationView;
  ActionBarDrawerToggle drawerToggle;

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if(drawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

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

    drawerLayout = findViewById(R.id.drawable_layout);
    navigationView = findViewById(R.id.navigationView);
    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
    drawerLayout.addDrawerListener(drawerToggle);
    drawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    Intent detailIntent = new Intent(this, UserInfoActivity.class);
    Intent tiketIntent = new Intent(this, TiketActivity.class);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
          case R.id.detail_user:
            startActivity(detailIntent);
            break;
          case R.id.tiket:
            startActivity(tiketIntent);
            break;
        }
        return false;
      }
    });
    userInfo  = (UserInfo) getIntent().getSerializableExtra("user_info");
    TextView nameuser= findViewById(R.id.name_user);
    nameuser.setText("User: " + userInfo.getName());
//    getSupportActionBar().hide();
    setTitle("Danh Sách Phim");

  }

  @Override
  public void onBackPressed() {
    if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START);
    }
    super.onBackPressed();
  }
}
