package com.example.rattingmovie.Tiket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.rattingmovie.QR.QRActivity;
import com.example.rattingmovie.R;

public class TiketActivity extends AppCompatActivity {

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tiket);
    recyclerView = findViewById(R.id.list_tiket);
    TiketModel tiketModel = new TiketModel("1231", "Avenger",
      "Thanh Xuan", "13/5", "18:30", "D4");
    TiketModel[] tiketModels = {tiketModel, tiketModel};
    TiketRecycleAdapter tiketRecycleAdapter = new TiketRecycleAdapter(tiketModels);
    recyclerView.setAdapter(tiketRecycleAdapter);
    Intent qrIntent = new Intent(this, QRActivity.class);
    tiketRecycleAdapter.setOnClickListener(new TiketRecycleAdapter.OnClickListener() {
      @Override
      public void onClick(int position, TiketModel tiketModel) {
        startActivity(qrIntent);
      }
    });
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    getSupportActionBar().hide();

  }


}
