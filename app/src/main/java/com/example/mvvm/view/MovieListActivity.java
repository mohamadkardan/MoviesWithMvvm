package com.example.mvvm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.mvvm.R;
import com.example.mvvm.model.MovieModel;
import com.example.mvvm.request.Service;
import com.example.mvvm.response.MovieSearchResponse;
import com.example.mvvm.utils.Credentials;
import com.example.mvvm.utils.MovieApi;
import com.example.mvvm.viewmodel.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
//        declare ViewModel to View
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
    }

    private void observeData() {
        viewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

            }
        });
    }

    private void GetRetrofitResponse() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY, "fast");

        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieSearchResponse> call, @NonNull Response<MovieSearchResponse> response) {
                if (response.code() == 200) {
                    Log.d("OnResponse", "onResponse: " + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
                    for (MovieModel movie : movies) {
                        Log.d("OnResponse", "onResponse: " + movie.getRelease_date());
                    }
                } else {
                    try {
                        Log.d("OnResponse", "onResponse: " + "error:: " + response.body().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieSearchResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getMovieById() {
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> call = movieApi.getMovie(550, Credentials.API_KEY);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                Log.d("GETMOVIE", "onResponse: " + response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.e("GETMOVIE", "onFailure: " + t.getMessage());
            }
        });
    }
}