package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * MovieAdapter displays movie posters in RecyclerView
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    //The data
    private ArrayList<Movie> movies;

    private Context mContext;

    private AdapterClickListener mAdapterClickListener;

    /**
     * This constructor gives the adapter its initial data
     * @param moviesArrayList the movies array that contains movie data
     */
    public MovieAdapter(ArrayList<Movie> moviesArrayList, AdapterClickListener clickListener){
        movies = moviesArrayList;
        mAdapterClickListener = clickListener;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_movie_poster, parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(movies.get(position).getPosterPath())
                .error(android.R.drawable.stat_notify_error)
                .into(holder.mainPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * This class represents the item in the recyclerView
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster)
        ImageView mainPoster;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mainPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAdapterClickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
