package com.example.rattingmovie.MovieList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rattingmovie.R;
import com.example.rattingmovie.data.MovieDescriptionModel;

import java.util.List;

public class MovieInListAdapter
  extends RecyclerView.Adapter<MovieInListAdapter.ViewHolder> {

  public interface OnClickListener {
    void onClick(int position, MovieDescriptionModel movieDescriptionModel);
  }
  private MovieDescriptionModel[] movieDescriptionModels;
  private OnClickListener onClickListener;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final TextView yearTextView;
    private final TextView studioTextView;
    private final TextView starTextView;
    private final TextView actorTextView;
    private final ImageView imageView;


    public ViewHolder(View view) {
      super(view);
      nameTextView = (TextView) view.findViewById(R.id.item_movie_name);
      yearTextView = (TextView) view.findViewById(R.id.item_movie_year);
      studioTextView = (TextView) view.findViewById(R.id.item_movie_studio);
      starTextView = (TextView) view.findViewById(R.id.item_movie_star);
      actorTextView = (TextView) view.findViewById(R.id.item_movie_actor);
      imageView = (ImageView) view.findViewById(R.id.item_movie_img);
    }

    public TextView getNameTextView() {
      return nameTextView;
    }

    public TextView getYearTextView() {
      return yearTextView;
    }

    public TextView getStudioTextView() {
      return studioTextView;
    }

    public TextView getStarTextView() {
      return starTextView;
    }

    public TextView getActorTextView() {
      return actorTextView;
    }

    public ImageView getImageView() {
      return imageView;
    }
  }



  public void setOnClickListener(OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
  }
  public MovieInListAdapter(MovieDescriptionModel[] dataSet) {
    movieDescriptionModels = dataSet;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.fragment_movie_desciption_item, parent, false);

    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    MovieDescriptionModel movieDescriptionModel = movieDescriptionModels[position];
    holder.getImageView().setImageResource(R.mipmap.ic_launcher);
    holder.getNameTextView().setText(movieDescriptionModel.getName());
    holder.getActorTextView().setText(movieDescriptionModel.getActor());
    holder.getYearTextView().setText(movieDescriptionModel.getYear());
    holder.getStarTextView().setText("Star: " + movieDescriptionModel.getStar().toString());
    holder.getStudioTextView().setText("Studio: " + movieDescriptionModel.getStudio());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (onClickListener != null) {
          onClickListener.onClick(position, movieDescriptionModel);
        }
      }
    });

  }

  @Override
  public int getItemCount() {
    return movieDescriptionModels.length;
  }


}
