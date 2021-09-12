package com.example.mvvm.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.AppExecutors;
import com.example.mvvm.model.MovieModel;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

//    searching and retrieving the data from api
    public void searchMovieApi() {
        final Future myHandler = AppExecutors.getInstance().getNetworkIO().submit(new Runnable() {
            @Override
            public void run() {
//                     Retrieve data from api

            }
        });

        AppExecutors.getInstance().getNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
//              Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MICROSECONDS);
    }
}
