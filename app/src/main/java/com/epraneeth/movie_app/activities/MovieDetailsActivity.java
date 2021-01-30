package com.epraneeth.movie_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.epraneeth.movie_app.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity {

    TextView originalTitle, voteAverage, overview;
    ImageView movieImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        init();
    }

    private void init() {
        originalTitle = findViewById(R.id.movie_details_original_title);
        voteAverage = findViewById(R.id.movie_details_vote_average);
        overview = findViewById(R.id.movie_details_overview);
        movieImage = findViewById(R.id.movie_details_image);
        Intent intent = getIntent();
        originalTitle.setText(intent.getStringExtra("original_title"));
        voteAverage.setText(intent.getStringExtra("vote_average"));
        overview.setText(intent.getStringExtra("overview"));
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500" + intent.getStringExtra("backdrop_path"))
                .into(movieImage);
    }
}