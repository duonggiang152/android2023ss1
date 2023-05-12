package com.example.rattingmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rattingmovie.data.LoginRepository;
import com.example.rattingmovie.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button loginBtn;
      loginBtn = findViewById(R.id.loginbtn);
      getSupportActionBar().hide();
      Intent loginIntent = new Intent(this, LoginActivity.class);
      loginBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          System.out.println("hallo");
          startActivity(loginIntent);
        }
      });
    }
}
