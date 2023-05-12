package com.example.rattingmovie.MovieList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rattingmovie.R;
import com.example.rattingmovie.data.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link top_bar_main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class top_bar_main extends Fragment {
  private TextView userTextView;
  public top_bar_main() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_top_bar_main, container, false);
    Bundle data = getArguments();
    if(data != null ) {
      TextView userNameTextView  = view.findViewById(R.id.name_user);
      userNameTextView.setText(data.getString("user_info"));
    }

    return view;
  }

}
