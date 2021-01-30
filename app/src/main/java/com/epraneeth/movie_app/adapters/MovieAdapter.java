package com.epraneeth.movie_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.epraneeth.movie_app.R;
import com.epraneeth.movie_app.activities.MovieDetailsActivity;
import com.epraneeth.movie_app.classes.Movie;
import com.epraneeth.movie_app.view_holders.MovieViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    Context mContext;
    ArrayList<Movie> mList = new ArrayList<>();

    public MovieAdapter(Context mContext, ArrayList<Movie> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setData(ArrayList<Movie> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.movie_row, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mList.get(position);
        holder.textView.setText(movie.getOriginal_title());
        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500" + movie.getPoster_path())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("original_title", movie.getOriginal_title());
                intent.putExtra("overview", movie.getOverview());
                intent.putExtra("backdrop_path", movie.getBackdrop_path());
                intent.putExtra("vote_average", movie.getVote_average().toString());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
