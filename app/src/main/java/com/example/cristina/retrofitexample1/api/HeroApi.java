package com.example.cristina.retrofitexample1.api;

import com.example.cristina.retrofitexample1.model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cristina on 17/02/18.
 */

public interface HeroApi {

    static final String  BASE_URL = "https://www.simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
