package com.example.rattingmovie.MovieDetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rattingmovie.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageViewBanner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageViewBanner extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public ImageViewBanner() {
    // Required empty public constructor
  }

  public static ImageViewBanner newInstance(String param1) {
    ImageViewBanner fragment = new ImageViewBanner();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_image_view_banner, container, false);
    ImageView imageView = view.findViewById(R.id.image_view_banner);
    Glide.with(getContext())
      .load(mParam1)
      .into(imageView);
    return view;
  }

}
