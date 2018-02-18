package com.example.cristina.retrofitexample1.api;

import com.example.cristina.retrofitexample1.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cristina on 18/02/18.
 */

public interface MovieApi {

    @GET("movie/popular")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

}
