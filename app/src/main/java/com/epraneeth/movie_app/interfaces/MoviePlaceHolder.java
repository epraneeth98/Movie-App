package com.epraneeth.movie_app.interfaces;

import com.epraneeth.movie_app.classes.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviePlaceHolder {
    @GET("20?")//?page=1&api_key=4056e9ab60d125ddec6ec2a67d91aea7/")
    Call<MovieResponse> getMovieResponse(@Query("page") String Page, @Query("api_key") String APIKey);
}
