package com.example.cristina.retrofitexample1.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.cristina.retrofitexample1.R;
import com.example.cristina.retrofitexample1.api.HeroApi;
import com.example.cristina.retrofitexample1.model.Hero;
import com.example.cristina.retrofitexample1.api.MovieApi;
import com.example.cristina.retrofitexample1.model.Movie;
import com.example.cristina.retrofitexample1.model.MovieResponse;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private final static String API_KEY = "67bd9236cf89d9b21b9fa775586c7453";
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setHero();
        setMovie();


    }

    private void initRecyclerView(List<Movie> movieList){
        //FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager();
        //flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        recyclerView = findViewById(R.id.rv_movie);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        movieAdapter = new MovieAdapter(movieList, this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(movieAdapter);
    }

    private void setMovie(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MovieResponse> responseCall = movieApi.getTopRatedMovies(API_KEY);

        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                //Log.e("allan", response.body().getResults().get(2).getTitle());
                movieList = response.body().getResults();
                initRecyclerView(movieList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void setHero(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HeroApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroApi heroApi = retrofit.create(HeroApi.class);

        Call<List<Hero>> call = heroApi.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                Log.e("allan", response.body().get(0).getImageurl());
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, movieList.get(position).getTitle(), Toast.LENGTH_LONG).show();
    }
}
