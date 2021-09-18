package com.example.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvm.R;
import com.example.mvvm.model.MovieModel;
import com.example.mvvm.view.MovieListActivity;

import java.util.List;

public class MovieRecyclerviewAdapter extends RecyclerView.Adapter<MovieRecyclerviewAdapter.ViewHolder> {
    private List<MovieModel> modelList;
    private OnMovieListener onMovieListener;

    public MovieRecyclerviewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    public void setModelList(List<MovieModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieRecyclerviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerviewAdapter.ViewHolder holder, int position) {
        holder.title.setText(modelList.get(position).getTitle());
        holder.language.setText(modelList.get(position).getLanguage());
        holder.voteAverage.setText(String.valueOf(modelList.get(position).getVote_average() / 2));
        holder.ratingBar.setRating(modelList.get(position).getVote_average());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" +
                modelList.get(position).getPoster_path()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (modelList != null) {
            return modelList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title, voteAverage, language;
        private ImageView imageView;
        private AppCompatRatingBar ratingBar;

        private OnMovieListener onMovieListener;

        public ViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            voteAverage = itemView.findViewById(R.id.movie_voteAverage);
            language = itemView.findViewById(R.id.movie_language);
            imageView = itemView.findViewById(R.id.movie_image);
            ratingBar = itemView.findViewById(R.id.rating_bar);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieListener.onMovieClick(getAdapterPosition());
        }
    }
}
