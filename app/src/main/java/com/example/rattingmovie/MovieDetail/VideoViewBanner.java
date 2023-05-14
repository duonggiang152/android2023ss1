package com.example.rattingmovie.MovieDetail;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.rattingmovie.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoViewBanner#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoViewBanner extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public VideoViewBanner() {
        // Required empty public constructor
    }
    public static VideoViewBanner newInstance(String param1) {
        VideoViewBanner fragment = new VideoViewBanner();
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
      View view = inflater.inflate(R.layout.fragment_video_view_banner, container, false);
      VideoView videoView = view.findViewById(R.id.video_banner);
      Uri uri = Uri.parse(mParam1);
      MediaController controller = new MediaController(getContext());
      controller.setAnchorView(videoView);
      controller.setMediaPlayer(videoView);
      videoView.setMediaController(controller);
      videoView.setVideoURI(uri);
      videoView.start();
        return view;
    }
}
