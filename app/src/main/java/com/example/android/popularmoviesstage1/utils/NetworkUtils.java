package com.example.android.popularmoviesstage1.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Nicki Hutchens on 5/15/2018.
 * Network utilities to connect to movies server and retrieve json movie data
 */

public class NetworkUtils {
    private static final String REQUEST_GET = "GET";
    private String jsonData;

    /**
     * Establish HTTP Connection and get movie data
     * @param api the api for the movie json data
     * @throws IOException IO Exception
     */
    public void connectAndRetrieveMovieData(String api) throws IOException {
        URL url = new URL(api);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(REQUEST_GET);
        urlConnection.connect();
        //read the json data with InputStream and Scanner
        InputStream in = urlConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        Scanner scanner = new Scanner(bufferedInputStream);
        scanner.useDelimiter("\\A");
        jsonData = scanner.next();
    }

    /**
     * This method returns the json data
     * @throws NullPointerException if the connectAndRetrieveMovieData(String) method failed to retrieve json data
     */
    public String retrieveData() throws NullPointerException{
        return jsonData;
    }
}
