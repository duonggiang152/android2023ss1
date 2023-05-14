package com.example.rattingmovie.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rattingmovie.ApiInterface.LoginApi;
import com.example.rattingmovie.ApiInterface.LoginData;
import com.example.rattingmovie.ApiInterface.LoginResponse;
import com.example.rattingmovie.MovieList.MovieListActivity;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.DataGlobal;
import com.example.rattingmovie.data.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
  private Button registerBtn;
  private Button loginBtn;
  private EditText emailTextView;
  private EditText passwordTextView;
  private Intent registerIntent;
  private Intent movieListViewIntent;
  private TextView warningText;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    registerBtn = findViewById(R.id.registerbtn);
    loginBtn = findViewById(R.id.loginbtn);
    emailTextView = findViewById(R.id.username);
    passwordTextView = findViewById(R.id.password);
    registerIntent = new Intent(this, RegisterActivity.class);
    movieListViewIntent = new Intent(this, MovieListActivity.class);
    warningText = findViewById(R.id.warninginput);
    getSupportActionBar().hide();

    loginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        warningText.setText("");
        String regexUsername = "[a-zA-Z0-9\\._\\-]{3,}";
        String regexPassword = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$";
        String username = emailTextView.getText().toString();

        String password = passwordTextView.getText().toString();
        if (username.matches(regexUsername) && username.matches(regexPassword)) {
          Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000").addConverterFactory(GsonConverterFactory.create()).build();

          LoginApi myApi = retrofit.create(LoginApi.class);
          LoginData request = new LoginData(username, password);


          Call<LoginResponse> call = myApi.login(request);
          call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
              if (response.raw().code() == 401) {
                Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
              }
              if (response.raw().code() == 200) {

                UserInfo userInfo = new UserInfo(request.getUsername());
                movieListViewIntent.putExtra("user_info", userInfo);
                startActivity(movieListViewIntent);

              }
              System.out.println(response.body());
              // handle the response
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
              Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
              System.out.println(call.toString());
              System.out.println(t.toString());
              // handle the error
            }
          });
        } else {
          Toast.makeText(LoginActivity.this, "Input không hợp lệ", Toast.LENGTH_SHORT).show();
        }
      }
    });

    registerBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(registerIntent);
      }
    });
    getSupportActionBar().hide();

  }
}
