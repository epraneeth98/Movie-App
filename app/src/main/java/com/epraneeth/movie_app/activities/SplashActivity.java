package com.epraneeth.movie_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.epraneeth.movie_app.R;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    private void init() {
        new Handler().postDelayed(() -> {
            // This method will be executed once the timer is over
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.INVISIBLE);
            //toolbar = findViewById(R.id.);
            finish();
        }, 1500);
    }
}