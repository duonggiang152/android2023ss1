package com.example.rattingmovie.MovieList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rattingmovie.MovieDetail.MovieDetailActivity;
import com.example.rattingmovie.R;
import com.example.rattingmovie.RegisterActivity;
import com.example.rattingmovie.data.MovieDescriptionModel;
import com.example.rattingmovie.data.Type.ListFragmentType;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;

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
    // Inflate the layout for this fragment
    movieIntent = new Intent(getContext(), MovieDetailActivity.class);
    View view = inflater.inflate(R.layout.fragment_list_movie, container, false);
    recyclerView = view.findViewById(R.id.recycle_list_movie);
    MovieDescriptionModel movieDescriptionModel = new MovieDescriptionModel(
      "123123",
      "avenger",
      "2014",
      "marvel",
      4.5,
      "crish hamsworth, natasha romanoth");
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    MovieDescriptionModel[] movieDescriptionModels = {movieDescriptionModel,movieDescriptionModel,movieDescriptionModel,movieDescriptionModel,movieDescriptionModel,movieDescriptionModel};
    MovieInListAdapter movieInListAdapter = new MovieInListAdapter( movieDescriptionModels);
    recyclerView.setAdapter(movieInListAdapter);

    movieInListAdapter.setOnClickListener(new MovieInListAdapter.OnClickListener() {
      @Override
      public void onClick(int position, MovieDescriptionModel movieDescriptionModel) {
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
