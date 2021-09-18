package com.example.mvvm.request;

import com.example.mvvm.utils.Credentials;
import com.example.mvvm.utils.MovieApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Credentials.BASE_URL);

    private static final Retrofit retrofit = retrofitBuilder.build();

    private static final MovieApi movieApi = retrofit.create(MovieApi.class);

    public static MovieApi getMovieApi() {
        return movieApi;
    }

}
