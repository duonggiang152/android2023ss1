package com.example.rattingmovie.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rattingmovie.ApiInterface.RegisterApi;
import com.example.rattingmovie.ApiInterface.RegisterData;
import com.example.rattingmovie.ApiInterface.RegisterResponse;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.DataGlobal;
import com.example.rattingmovie.data.LoginRepository;
import com.example.rattingmovie.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
  private Button loginBtn;
  private Button registerBtn;
  private TextView usernameTextView;
  private TextView passwordTextView;
  private TextView passwordr2TextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);
    loginBtn = findViewById(R.id.loginbtn);
    registerBtn = findViewById(R.id.registerbtn);
    usernameTextView = findViewById(R.id.username);
    passwordTextView = findViewById(R.id.password);
    passwordr2TextView = findViewById(R.id.password_r);
    String regexUsername = "[a-zA-Z0-9\\._\\-]{3,}";
    String regexPassword = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$";


    getSupportActionBar().hide();
    Intent loginIntent = new Intent(this, LoginActivity.class);
    registerBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String username = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        if (!usernameTextView.getText().toString().matches(regexUsername)) {
          Toast.makeText(RegisterActivity.this, "User name không hợp lệ", Toast.LENGTH_SHORT).show();
          return;
        }
        if (passwordTextView.getText().toString().isEmpty() ) {
          Toast.makeText(RegisterActivity.this, "Password không được để trống", Toast.LENGTH_SHORT).show();
          return;
        }
        if(!passwordTextView.getText().toString().matches(regexPassword)){
          Toast.makeText(RegisterActivity.this, "Password không hợp lệ", Toast.LENGTH_SHORT).show();
          return;
        }
        if (passwordr2TextView.getText().toString().compareTo(passwordTextView.getText().toString()) != 0) {
          Toast.makeText(RegisterActivity.this, "Pasword nhập lại không trùng", Toast.LENGTH_SHORT).show();
          return;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();
        RegisterApi registerApi = retrofit.create(RegisterApi.class);
        RegisterData registerData = new RegisterData(usernameTextView.getText().toString(), passwordTextView.getText().toString());
        Call<RegisterResponse> call = registerApi.register(registerData);
        call.enqueue(new Callback<RegisterResponse>() {
          @Override
          public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
            if(response.raw().code() == 401) {
              Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();

            }
            if(response.raw().code() == 200) {
              startActivity(loginIntent);
            }
          }

          @Override
          public void onFailure(Call<RegisterResponse> call, Throwable t) {

          }
        });
      }
    });
    loginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        startActivity(loginIntent);
      }
    });
  }
}
