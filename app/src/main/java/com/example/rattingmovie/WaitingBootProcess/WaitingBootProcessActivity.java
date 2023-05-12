package com.example.rattingmovie.WaitingBootProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.rattingmovie.R;
import com.example.rattingmovie.ui.login.LoginActivity;

public class WaitingBootProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.waiting_boot_process);
      SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
      String jwt = sharedPreferences.getString("jwt", null);
      if(jwt == null) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
      }
      getSupportActionBar().hide();
    }
}
