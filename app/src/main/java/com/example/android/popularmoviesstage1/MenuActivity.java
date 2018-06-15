package com.example.android.popularmoviesstage1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import butterknife.BindView;

/**
 * Created by Nicki Hutchens on 5/20/2018.
 */

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.rg_sort_by)
    RadioGroup rgSortBy;
    @BindView(R.id.rb_most_popular)
    RadioGroup rbMostPopular;
    @BindView(R.id.rb_top_rated)
    RadioGroup rbTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getActionBar().hide();
        setContentView(R.layout.activity_menu);

    }

    public interface AdapterClickListener {
        void onItemClicked(int itemPosition);
    }

}
