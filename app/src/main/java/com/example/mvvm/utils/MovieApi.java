package com.example.mvvm.utils;

import com.example.mvvm.model.MovieModel;
import com.example.mvvm.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(@Query("api_key") String api_key, @Query("query") String query, @Query("page") int page);

    @GET("3/movie/{movie_id}")
    Call<MovieModel> getMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

}
