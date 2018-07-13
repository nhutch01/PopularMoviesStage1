package com.example.android.popularmoviesstage1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.popularmoviesstage1.utils.BuildAPIStringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicki Hutchens on 5/20/2018.
 */

public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.rg_sort_by)
    RadioGroup rgSortBy;
    @BindView(R.id.rb_most_popular)
    RadioButton rbMostPopular;
    @BindView(R.id.rb_top_rated)
    RadioButton rbTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ButterKnife.bind(this);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int selectedType = sharedPreferences.getInt(getString(R.string.preferenceSort), BuildAPIStringUtil.SORT_BY_POPULAR);

        if(selectedType == BuildAPIStringUtil.SORT_BY_POPULAR){
            rgSortBy.check(R.id.rb_most_popular);
        }else if(selectedType == BuildAPIStringUtil.SORT_BY_TOP_RATED){
            rgSortBy.check(R.id.rb_top_rated);
        }

        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();
                if(id == R.id.rb_most_popular){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(getString(R.string.preferenceSort),BuildAPIStringUtil.SORT_BY_POPULAR);
                    editor.apply();
                }else if (id == R.id.rb_top_rated){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(getString(R.string.preferenceSort),BuildAPIStringUtil.SORT_BY_TOP_RATED);
                    editor.apply();
                }
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}
