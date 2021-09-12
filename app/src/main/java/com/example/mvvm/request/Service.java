package com.example.mvvm.request;

import com.example.mvvm.utils.Credentials;
import com.example.mvvm.utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Credentials.BASE_URL);

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi() {
        return movieApi;
    }

}
