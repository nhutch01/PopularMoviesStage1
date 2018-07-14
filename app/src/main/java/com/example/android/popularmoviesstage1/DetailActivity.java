package com.example.android.popularmoviesstage1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.popularmoviesstage1.model.Movie;

/**
 * Created by Nicki Hutchens on 5/21/2018.
 */

public class DetailActivity extends AppCompatActivity{

    @BindView(R.id.tv_original_title)
    TextView tvOriginalTitle;
    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_overview)
    TextView tvPlot;
    @BindView(R.id.tv_user_rating)
    TextView tvRating;
    @BindView(R.id.tv_release_date)
    TextView tvReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        ActionBar mActionBar = getSupportActionBar();
        if(mActionBar != null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        receiveAndShowMovieData();
    }

    /**
     * Get data from the selected movie in the main activity class and displays
     * it in the details activity
     */
    private void receiveAndShowMovieData(){
        //Receiving
        Intent i = getIntent();
        int position = i.getExtras().getInt(getString(R.string.moviePosition));
        Movie selectedMovie = MainActivity.getMovies().get(position);
        String title = selectedMovie.getTitle();
        String posterPath = selectedMovie.getPosterPath();
        String plot = selectedMovie.getOverview();
        String rating = selectedMovie.getRating();
        String releaseDate = selectedMovie.getDate();
        float ratingFloat = Float.parseFloat(rating);
        int ratingColor;
        if(ratingFloat >= 8){
            ratingColor = getResources().getColor(R.color.bestRating);
        }else if(ratingFloat >= 6){
            ratingColor = getResources().getColor(R.color.aboveNormalRating);
        }else if(ratingFloat >= 4){
            ratingColor = getResources().getColor(R.color.normalRating);
        }else if(ratingFloat >= 2){
            ratingColor = getResources().getColor(R.color.belowNormalRating);
        }else {
            ratingColor = getResources().getColor(R.color.lowestRating);
        }
        //Displaying
        tvOriginalTitle.setText(title);
        Picasso.with(this)
                .load(posterPath)
                .error(android.R.drawable.stat_notify_error)
                .into(ivPoster);
        tvPlot.setText(plot);
        tvRating.setText(rating);
        tvRating.setTextColor(ratingColor);
        tvReleaseDate.setText(releaseDate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
