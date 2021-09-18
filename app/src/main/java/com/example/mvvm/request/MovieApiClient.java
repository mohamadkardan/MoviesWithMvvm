package com.example.mvvm.request;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.AppExecutors;
import com.example.mvvm.model.MovieModel;
import com.example.mvvm.response.MovieSearchResponse;
import com.example.mvvm.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {

    //  LiveData
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public void searchMovieApi(String query, int pageNumber) {

        final Future myHandler = AppExecutors.getInstance().getNetworkIO().submit(() -> {
            Call<MovieSearchResponse> call = Service.getMovieApi().searchMovie(Credentials.API_KEY, query, pageNumber);
            call.enqueue(new Callback<MovieSearchResponse>() {
                @Override
                public void onResponse(@NonNull Call<MovieSearchResponse> call, @NonNull Response<MovieSearchResponse> response) {
                    List<MovieModel> list = response.body().getMovies();
                    if (pageNumber == 1) {
//                        sending data to live data
//                        postValue : used for background thread
//                        setValue : not for bg thread
                        mMovies.postValue(list);
                    } else {
                        List<MovieModel> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieSearchResponse> call, @NonNull Throwable t) {
                    Log.d("Tag", "error: " + t.getMessage());
                    mMovies.postValue(null);
                }
            });
        });

        AppExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
//              Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MICROSECONDS);
    }

}
