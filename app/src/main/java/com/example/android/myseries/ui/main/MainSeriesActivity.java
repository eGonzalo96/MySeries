package com.example.android.myseries.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.ui.main.fragments.SeriesFragment;

public class MainSeriesActivity extends AppCompatActivity {

    SearchView mSeriesSearchView;
    SearchViewListener mSearchViewListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_series);

        attachSeriesFragment();

        mSeriesSearchView = (SearchView) findViewById(R.id.mainSearchView);
        mSeriesSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.endsWith(" ")) {
                    Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
                    //mSearchViewListener.getSeriesList(s);
                }
                return false;
            }
        });
    }


    private void attachSeriesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SeriesFragment sf = new SeriesFragment();
        fm.beginTransaction()
                .add(R.id.mainSeriesFragment, sf)
                .commitNow();

        mSearchViewListener = (SearchViewListener) sf;
    }
}
