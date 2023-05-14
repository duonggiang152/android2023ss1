package com.example.rattingmovie.Tiket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rattingmovie.MovieList.MovieInListAdapter;
import com.example.rattingmovie.R;
import com.example.rattingmovie.data.MovieDescriptionModel;

public class TiketRecycleAdapter
  extends RecyclerView.Adapter<TiketRecycleAdapter.ViewHolder> {
  private TiketModel[] tiketModels;

  public TiketRecycleAdapter(TiketModel[] tiketModels) {
    this.tiketModels = tiketModels;
  }

  public interface OnClickListener {
    void onClick(int position, TiketModel tiketModel);
  }
  private OnClickListener onClickListener;

  public void setOnClickListener(TiketRecycleAdapter.OnClickListener onClickListener) {
    this.onClickListener = onClickListener;
  }
  public static class ViewHolder extends RecyclerView.ViewHolder {
    private TextView tittle_tiket;
    private TextView address_thread;
    private TextView time_start;
    private TextView position_tiket;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tittle_tiket = (TextView) itemView.findViewById(R.id.title_tiket);
      address_thread = (TextView) itemView.findViewById(R.id.address_theater);
      position_tiket = (TextView) itemView.findViewById(R.id.position_tiket);
      time_start  = (TextView) itemView.findViewById(R.id.position_tiket);
    }

    public TextView getTittle_tiket() {
      return tittle_tiket;
    }

    public TextView getAddress_thread() {
      return address_thread;
    }

    public TextView getTime_start() {
      return time_start;
    }


    public TextView getPosition_tiket() {
      return position_tiket;
    }

    public void setPosition_tiket(TextView position_tiket) {
      this.position_tiket = position_tiket;
    }

    public void setTittle_tiket(TextView tittle_tiket) {
      this.tittle_tiket = tittle_tiket;
    }

    public void setAddress_thread(TextView address_thread) {
      this.address_thread = address_thread;
    }

    public void setTime_start(TextView time_start) {
      this.time_start = time_start;
    }


  }

  @NonNull
  @Override
  public TiketRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.tiket_list_item, parent, false);
    return new ViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull TiketRecycleAdapter.ViewHolder holder, int position) {
    TiketModel tiketModel = tiketModels[position];
    holder.getPosition_tiket().setText(tiketModel.getPosition());
    holder.getTittle_tiket().setText(tiketModel.getTitle());
    holder.getAddress_thread().setText(tiketModel.getTheater());
    holder.getTime_start().setText(tiketModel.getDate()+"/"+tiketModel.getTime());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (onClickListener != null) {
          onClickListener.onClick(position, tiketModel);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return tiketModels.length;
  }


}
