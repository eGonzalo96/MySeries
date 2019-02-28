package com.example.android.myseries.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.ui.main.fragments.SeriesFragment;

public class MainSeriesActivity
    extends AppCompatActivity {

    SearchView mSeriesSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_series);

        attachSeriesFragment();

        mSeriesSearchView = (SearchView) findViewById(R.id.mainSearchView);
        
    }


    private void attachSeriesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.mainSeriesFragment, new SeriesFragment())
                .commitNow();
    }
}
