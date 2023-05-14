package com.example.rattingmovie.MovieList;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rattingmovie.ApiInterface.movie.MovieApi;
import com.example.rattingmovie.ApiInterface.movie.MovieResponse;
import com.example.rattingmovie.MovieDetail.MovieDetailActivity;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.MovieDescriptionModel;
import com.example.rattingmovie.data.Type.ListFragmentType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMovieFragment extends Fragment {
  final static String KEY = "type";
  private String type;
  private RecyclerView recyclerView;

  private Intent movieIntent;
  public ListMovieFragment() {
    // Required empty public constructor
  }

  public static ListMovieFragment newInstance(ListFragmentType fragmenttype) {
    Bundle args = new Bundle();
    ListMovieFragment listMovieFragment = new ListMovieFragment();
    args.putString(KEY, fragmenttype.toString());
    listMovieFragment.setArguments(args);
    return listMovieFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      String param1 = getArguments().getString(KEY);
      if (param1.compareTo(ListFragmentType.NEW.toString()) == 0) {
        type = ListFragmentType.NEW.toString();
      }
      if (param1.compareTo(ListFragmentType.HIGHESTVIEW.toString()) == 0) {
        type = ListFragmentType.HIGHESTVIEW.toString();
      }
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    List<MovieDescriptionModel> movieDescriptionModels = new ArrayList<>() ;
    MovieInListAdapter movieInListAdapter = new MovieInListAdapter( movieDescriptionModels.toArray(new MovieDescriptionModel[0]));
    // Inflate the layout for this fragment
    String BASE_URL = "http://10.0.2.2:3000";
    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build();
    MovieApi myApi = retrofit.create(MovieApi.class);
    Call<List<MovieResponse>> call = myApi.getMovies();
    if(type.compareTo(ListFragmentType.NEW.toString()) == 0) {
      call = myApi.getMovies();
    }
    if(type.compareTo(ListFragmentType.HIGHESTVIEW.toString()) == 0) {
      call = myApi.getTopMovies();
    }

    call.enqueue(new Callback<List<MovieResponse>>() {
      @Override
      public void onResponse(Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {
        if (!response.isSuccessful()) {
          // Handle error

          return;
        }
        List<MovieResponse> movies = response.body();
        for(int i = 0; i < movies.size(); i++) {
          MovieResponse movieResponse = movies.get(i);
          MovieDescriptionModel movieDescriptionModel = new MovieDescriptionModel(movieResponse.getId(),
            movieResponse.getName(),
            movieResponse.getYear(),
            movieResponse.getStudio(),
            movieResponse.getStar(),
            movieResponse.getActor(),
            movieResponse.getImageMovie()
          );
          movieDescriptionModels.add(movieDescriptionModel);
          movieInListAdapter.updateData(getContext(),movieDescriptionModels.toArray(new MovieDescriptionModel[0]));
//          MovieInListAdapter newAdapter = new MovieInListAdapter(getContext(), movieDescriptionModels.toArray(new MovieDescriptionModel[0]));
//          recyclerView.setAdapter(newAdapter);
        }

        // Process the list of movies
      }

      @Override
      public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
        // Handle failure
        Toast.makeText(getContext(), "Faile", Toast.LENGTH_SHORT).show();
      }
    });
    movieIntent = new Intent(getContext(), MovieDetailActivity.class);
    View view = inflater.inflate(R.layout.fragment_list_movie, container, false);
    recyclerView = view.findViewById(R.id.recycle_list_movie);

    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



    recyclerView.setAdapter(movieInListAdapter);

    movieInListAdapter.setOnClickListener(new MovieInListAdapter.OnClickListener() {
      @Override
      public void onClick(int position, MovieDescriptionModel movieDescriptionModel) {

        movieIntent.putExtra("movie", movieDescriptionModel);
        startActivity(movieIntent);
      }
    });
    if (type.compareTo(ListFragmentType.NEW.toString()) == 0) {
      GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(getContext(), R.drawable.new_movie_gradient);
      view.setBackground(drawable);
    }
    if (type.compareTo(ListFragmentType.HIGHESTVIEW.toString()) == 0) {
      GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(getContext(), R.drawable.top_movie_gradient);
      view.setBackground(drawable);
    }
    return view;
  }
}
