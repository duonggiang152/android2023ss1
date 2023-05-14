package com.example.rattingmovie.BookingTicket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rattingmovie.ApiInterface.BookingTiket.BookingTiketApi;
import com.example.rattingmovie.ApiInterface.BookingTiket.BookingTiketResponse;
import com.example.rattingmovie.ApiInterface.movieDetail.MovieDetailApi;
import com.example.rattingmovie.ApiInterface.movieDetail.MovieDetailResponse;
import com.example.rattingmovie.MovieDetail.MovieBannerAdapter;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.MovieDescriptionModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingTicketActivity extends AppCompatActivity {
  private Spinner theaterSpinner;
  private Button buyTiket;

  private List<BookingTiketResponse> tiketValiable;

  private Spinner dateSpinner;

  private Spinner timeSpinner;

  private Spinner positionSpinner;

  private List<String> dates = new ArrayList<>();

  private List<String> time = new ArrayList<>();

  private List<String> position = new ArrayList<>();

  private TextView priceTextView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_booking_ticket);
    theaterSpinner = findViewById(R.id.theater_spiner);
    dateSpinner = findViewById(R.id.ticket_date);
    timeSpinner = findViewById(R.id.ticket_time);
    positionSpinner = findViewById(R.id.ticket_position);
    priceTextView = findViewById(R.id.ticket_price);

    buyTiket = findViewById(R.id.buy_tiket);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_theater, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    theaterSpinner.setAdapter(adapter);
    MovieDescriptionModel movieDescriptionModel = (MovieDescriptionModel) getIntent().getSerializableExtra("movie");

    String BASE_URL = "http://10.0.2.2:3000";
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    String movieId = movieDescriptionModel.getId(); // replace with the actual movie ID
    BookingTiketApi bookingTiketApi = retrofit.create(BookingTiketApi.class);
    Call<List<BookingTiketResponse>> call = bookingTiketApi.getTikets(movieId);

    theaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        List<String> dates = getDateBaseOnTheater(selectedTheater);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BookingTicketActivity.this, android.R.layout.simple_list_item_1, dates);
        dateSpinner.setAdapter(adapter);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        String selectedDate = dateSpinner.getSelectedItem().toString();
        List<String> times = getTimeBaseOnTheaterAndDate(selectedTheater, selectedDate);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BookingTicketActivity.this, android.R.layout.simple_list_item_1, times);
        timeSpinner.setAdapter(adapter);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        String selectedDate = dateSpinner.getSelectedItem().toString();
        String selectedTime = timeSpinner.getSelectedItem().toString();
        List<String> positions = getPositionBaseOnTheaterAndDateAndTime(selectedTheater, selectedDate, selectedTime);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(BookingTicketActivity.this, android.R.layout.simple_list_item_1, positions);
        positionSpinner.setAdapter(adapter);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    positionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedTheater = theaterSpinner.getSelectedItem().toString();
        String selectedDate = dateSpinner.getSelectedItem().toString();
        String selectedTime = timeSpinner.getSelectedItem().toString();
        String selectedPosition = positionSpinner.getSelectedItem().toString();
        String price = getPrice(selectedTheater, selectedDate, selectedTime, selectedPosition );
        priceTextView.setText(
          price
        );
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });
    call.enqueue(new Callback<List<BookingTiketResponse>>() {
      @Override
      public void onResponse(Call<List<BookingTiketResponse>> call, Response<List<BookingTiketResponse>> response) {
        if (response.isSuccessful()) {
          List<BookingTiketResponse> movieDetailResponses = response.body();

          List<String> theaters = new ArrayList<String>();
          tiketValiable = response.body();
          for (int i = 0; i < movieDetailResponses.size(); i++) {
            if (!theaters.contains(movieDetailResponses.get(i).getTheater())) {
              theaters.add(movieDetailResponses.get(i).getTheater());
            }
          }
          ArrayAdapter<String> adapter = new ArrayAdapter<>(BookingTicketActivity.this, android.R.layout.simple_list_item_1, theaters);
          theaterSpinner.setAdapter(adapter);

        } else {
          // handle error
        }
      }

      @Override
      public void onFailure(Call<List<BookingTiketResponse>> call, Throwable t) {
        // handle error
      }
    });

    buyTiket.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  private List<String> getDateBaseOnTheater(String theater) {
    List<String> dates = new ArrayList<>();
    for (int i = 0; i < tiketValiable.size(); i++) {
      if (!dates.contains(tiketValiable.get(i).getDate()) && tiketValiable.get(i).getTheater().equals(theater)) {
        dates.add(tiketValiable.get(i).getDate());
      }
    }
    return dates;
  }

  private List<String> getTimeBaseOnTheaterAndDate(String theater, String date) {
    List<String> time = new ArrayList<>();
    for (int i = 0; i < tiketValiable.size(); i++) {
      if (!time.contains(tiketValiable.get(i).getTime()) &&
        tiketValiable.get(i).getTheater().equals(theater) &&
        tiketValiable.get(i).getDate().equals(date)) {
        time.add(tiketValiable.get(i).getTime());
      }
    }
    return time;
  }

  private List<String> getPositionBaseOnTheaterAndDateAndTime(String theater, String date, String time) {
    List<String> position = new ArrayList<>();
    for (int i = 0; i < tiketValiable.size(); i++) {
      if (!position.contains(tiketValiable.get(i).getTime()) &&
        tiketValiable.get(i).getTheater().equals(theater) &&
        tiketValiable.get(i).getDate().equals(date) &&
        tiketValiable.get(i).getTime().equals(time)) {
        position.add(tiketValiable.get(i).getPosition());
      }
    }
    return position;
  }

  private String getPrice(String theater, String date, String time, String position) {
    String priceTicket = "";
    for (int i = 0; i < tiketValiable.size(); i++) {
      if (tiketValiable.get(i).getTheater().equals(theater) &&
        tiketValiable.get(i).getDate().equals(date) &&
        tiketValiable.get(i).getTime().equals(time) &&
      tiketValiable.get(i).getPosition().equals(position)) {
        priceTicket = tiketValiable.get(i).getPrice();
        System.out.println("price: "+priceTicket);
      }
    }
    return priceTicket;
  }
  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}
