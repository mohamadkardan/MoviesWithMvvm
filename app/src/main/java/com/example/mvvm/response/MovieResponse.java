package com.example.mvvm.response;

import androidx.annotation.NonNull;

import com.example.mvvm.model.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getMovie() {
        return movie;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
