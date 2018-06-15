package com.example.android.popularmoviesstage1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.android.popularmoviesstage1.model.Movie;

/**
 * Created by Nicki Hutchens on 5/15/2018.
 * JSON Utilities to parse movie data
 */

public class JSONUtils {
    public static final String KEY_TITLE = "title";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_RATING = "vote_average";
    public static final String KEY_RELEASE_DATE = "release_date";
    public static final String KEY_RESULTS = "results";

    public static ArrayList<Movie> parseMovieJson(String json) {
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsAry = jsonObject.getJSONArray(KEY_RESULTS);

            ArrayList<Movie> movieAry = new ArrayList<>();

            for(int i=0; i<resultsAry.length();i++){
                JSONObject movieObject = resultsAry.getJSONObject(i);
                movieAry.add(new Movie(movieObject.getString(KEY_TITLE),movieObject.getString(KEY_POSTER_PATH),movieObject.getString(KEY_OVERVIEW),movieObject.getString(KEY_RATING),movieObject.getString(KEY_RELEASE_DATE)));
            }
            return movieAry;
        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }

    }

}
