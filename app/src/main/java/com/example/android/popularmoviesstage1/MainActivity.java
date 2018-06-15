package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.popularmoviesstage1.model.Movie;
import com.example.android.popularmoviesstage1.utils.BuildAPIStringUtil;
import com.example.android.popularmoviesstage1.utils.JSONUtils;
import com.example.android.popularmoviesstage1.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements AdapterClickListener, BackgroundTaskCallBacks<String,String> {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private static ArrayList<Movie> movies;
    String api;
    NetworkUtils networkUtils;
    MovieAdapter movieAdapter;
    RunBackgroundTask backgroundTask;
    private static final int SETTINGS_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int sortType = prefs.getInt(getString(R.string.preferenceSort), BuildAPIStringUtil.SORT_BY_POPULAR);

        api = BuildAPIStringUtil.buildAPIString(sortType);

        networkUtils = new NetworkUtils();

        backgroundTask = new RunBackgroundTask(api,this);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null){
            backgroundTask.execute();
        }else {
            Toast.makeText(getBaseContext(), R.string.networkFailure,Toast.LENGTH_LONG).show();
        }

        RecyclerView.LayoutManager layoutManager
                = new GridLayoutManager(MainActivity.this,2, LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void onItemClicked(int itemPosition) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra(getString(R.string.moviePosition), itemPosition);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.itm_main_menu){
            startActivityForResult(new Intent(MainActivity.this, MenuActivity.class),SETTINGS_REQUEST_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_REQUEST_CODE && resultCode == RESULT_OK){
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if(netInfo != null){
                refreshAdapter();
            }else {
                Toast.makeText(getBaseContext(), R.string.networkFailure,Toast.LENGTH_LONG).show();
            }

        }
    }

    public void refreshAdapter(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int sortType = prefs.getInt(getString(R.string.preferenceSort), BuildAPIStringUtil.SORT_BY_POPULAR);
        api = BuildAPIStringUtil.buildAPIString(sortType);
        RunBackgroundTask myBackgroundTask = new RunBackgroundTask(api,this);
        myBackgroundTask.execute();
    }

    /**
     * This is the getter method for the retrieved movies
     * @return Movies array
     */
    public static ArrayList<Movie> getMovies(){
        return movies;
    }

    @Override
    public String onBackground(String api) {
        try {
            networkUtils.connectAndRetrieveMovieData(api);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return networkUtils.retrieveData();
    }

    @Override
    public void onTaskFinished(String result) {
        movies = JSONUtils.parseMovieJson(result);
        movieAdapter = new MovieAdapter(movies,MainActivity.this);
        mRecyclerView.setAdapter(movieAdapter);
    }
}
