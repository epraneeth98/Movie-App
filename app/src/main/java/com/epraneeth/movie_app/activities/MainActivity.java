package com.epraneeth.movie_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.epraneeth.movie_app.R;
import com.epraneeth.movie_app.adapters.MovieAdapter;
import com.epraneeth.movie_app.classes.Movie;
import com.epraneeth.movie_app.classes.MovieResponse;
import com.epraneeth.movie_app.interfaces.MoviePlaceHolder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieResponse movieResponse;
    MovieAdapter movieAdapter;
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/4/list/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoviePlaceHolder moviePlaceHolderAPI = retrofit.create(MoviePlaceHolder.class);
        Call<MovieResponse> call = moviePlaceHolderAPI.getMovieResponse("1","4056e9ab60d125ddec6ec2a67d91aea7");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d("abc", "onResponse - Status : " + response.code());
                movieResponse=response.body();
                Log.d("abc", String.valueOf(movieResponse.getResults().size()));
                movieArrayList.addAll(movieResponse.getResults());
                movieAdapter.setData(movieArrayList);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("abc", "Error Buddy!-"+ t.getMessage());
            }
        });


        //init 2
        recyclerView = findViewById(R.id.movies_list_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        movieAdapter = new MovieAdapter(this, movieArrayList);
        recyclerView.setAdapter(movieAdapter);
    }
}