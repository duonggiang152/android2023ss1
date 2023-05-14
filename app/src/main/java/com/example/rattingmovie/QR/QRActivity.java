package com.example.rattingmovie.QR;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rattingmovie.R;

public class QRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qractivity);
        getSupportActionBar().hide();
    }
}
