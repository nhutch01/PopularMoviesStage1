package com.example.android.popularmoviesstage1.model;

/**
 * Created by Nicki Hutchens on 5/15/2018.
 * Movie Model with Movie Data
 */

public class Movie {
    private String title, posterPath, overview, rating, date;
    private static final String BASE_URL = "http://image.tmdb.org/t/p/w185";

    /**
     * @param originalTitle movie title
     * @param posterPath path to movie poster
     * @param overView movie overview
     * @param userRating users rating of movie
     * @param releaseDate movie release date
     */
    public Movie(String originalTitle , String posterPath , String overView,String userRating,String releaseDate){
        title = originalTitle;
        this.posterPath = posterPath;
        overview = overView;
        rating = userRating;
        date = releaseDate;
    }

    public String getTitle() {

        return title;
    }

    public String getPosterPath() {

        return BASE_URL+this.posterPath;
    }

    public String getOverview() {

        return overview;
    }

    public String getRating() {

        return rating;
    }

    public String getDate() {

        return date;
    }
}
