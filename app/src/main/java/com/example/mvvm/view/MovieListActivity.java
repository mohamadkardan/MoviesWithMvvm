package com.example.mvvm.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.mvvm.R;
import com.example.mvvm.adapter.MovieRecyclerviewAdapter;
import com.example.mvvm.adapter.OnMovieListener;
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

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    //    viewModel
    private MovieListViewModel viewModel;
    private MovieRecyclerviewAdapter recyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
//        declare ViewModel to View
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        setRecyclerView();
//        calling the observer
        observeData();
        searchMovie("fast", 1);

//        Testing the Call
//        button = findViewById(R.id.button);
//        button.setOnClickListener(view -> {
//            searchMovie("Fast", 1);
//        });
    }

    private void observeData() {
        viewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    for (MovieModel model : movieModels) {
                        Log.d("OBSERVE", "onChanged: " + model.getTitle());
                        recyclerviewAdapter.setModelList(movieModels);
                    }
                }
            }
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerviewAdapter = new MovieRecyclerviewAdapter(this);
        recyclerView.setAdapter(recyclerviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    public void searchMovie(String query, int pageNumber) {
        viewModel.searchMovie(query, pageNumber);
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }

//    private void GetRetrofitResponse() {
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(Credentials.API_KEY, "fast", 1);
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<MovieSearchResponse> call, @NonNull Response<MovieSearchResponse> response) {
//                if (response.code() == 200) {
//                    Log.d("OnResponse", "onResponse: " + response.body().toString());
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//                    for (MovieModel movie : movies) {
//                        Log.d("OnResponse", "onResponse: " + movie.getRelease_date());
//                    }
//                } else {
//                    try {
//                        Log.d("OnResponse", "onResponse: " + "error:: " + response.body().toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<MovieSearchResponse> call, @NonNull Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//
//    private void getMovieById() {
//        MovieApi movieApi = Service.getMovieApi();
//        Call<MovieModel> call = movieApi.getMovie(550, Credentials.API_KEY);
//        call.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
//                Log.d("GETMOVIE", "onResponse: " + response.body().getTitle());
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//                Log.e("GETMOVIE", "onFailure: " + t.getMessage());
//            }
//        });
//    }
}