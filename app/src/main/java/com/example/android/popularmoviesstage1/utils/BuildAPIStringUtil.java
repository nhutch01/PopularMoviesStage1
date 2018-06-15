package com.example.android.popularmoviesstage1.utils;

import android.net.Uri;

import com.example.android.popularmoviesstage1.BuildConfig;

/**
 * Created by Nicki Hutchens on 5/30/2018.
 * Build the API String to retrieve the movie data
 */

public class BuildAPIStringUtil {
    // API string elements
    private static final String SCHEME = "http";
    private static final String AUTHORITY = "api.themoviedb.org";
    private static final String PATH_ELEMENT_1 = "3";
    private static final String PATH_ELEMENT_2 = "movie";
    private static final String PATH_ELEMENT_TOP_RATED = "top_rated";
    private static final String PATH_ELEMENT_POPULAR = "popular";
    private static final String API_KEY = "api_key";

    // sorting options
    public static final int SORT_BY_TOP_RATED = 0;
    public static final int SORT_BY_POPULAR = 1;

    public static String buildAPIString(int sortType){
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme(SCHEME);
        uriBuilder.authority(AUTHORITY);
        uriBuilder.appendPath(PATH_ELEMENT_1).appendPath(PATH_ELEMENT_2);
        if(sortType == SORT_BY_POPULAR){
            uriBuilder.appendPath(PATH_ELEMENT_POPULAR);
        }
        else if(sortType == SORT_BY_TOP_RATED){
            uriBuilder.appendPath(PATH_ELEMENT_TOP_RATED);
        }
        uriBuilder.appendQueryParameter(API_KEY, BuildConfig.API_KEY);
        return uriBuilder.build().toString();

    }

    // Method to return the API string with sort by popular option as the default
    public static String buildAPIString(){
        return buildAPIString(SORT_BY_POPULAR);
    }
}
