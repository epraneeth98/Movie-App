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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

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
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://api.themoviedb.org/4/list/20?page=1&api_key=4056e9ab60d125ddec6ec2a67d91aea7",
                response -> {
                    Gson gson = new GsonBuilder().create();
                    movieResponse = gson.fromJson(response, MovieResponse.class);
                    movieArrayList.addAll(movieResponse.getResults());
                    movieAdapter.setData(movieArrayList);
                },
                error -> {
                    Log.d("abc", "Error Buddy!");
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

        recyclerView = findViewById(R.id.movies_list_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        movieAdapter = new MovieAdapter(this, movieArrayList);
        recyclerView.setAdapter(movieAdapter);
    }
}