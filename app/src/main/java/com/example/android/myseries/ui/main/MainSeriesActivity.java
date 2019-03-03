package com.example.android.myseries.ui.main;

import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Display;

import com.example.android.myseries.R;
import com.example.android.myseries.data.series.entities.Serie;
import com.example.android.myseries.ui.episodes.SpecificationsActivity;
import com.example.android.myseries.ui.main.fragments.SeriesFragment;

public class MainSeriesActivity 
        extends AppCompatActivity {

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
                mSearchViewListener.getSeriesList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.endsWith(" ")) {
                    mSearchViewListener.getSeriesList(s);
                }
                return false;
            }
        });


    }


    private void attachSeriesFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SeriesFragment sf = new SeriesFragment();
        sf.setScreenDimensions(getScreenSize());
        fm.beginTransaction()
                .add(R.id.mainSeriesFragment, sf)
                .commitNow();

        mSearchViewListener = (SearchViewListener) sf;
        sf.setOnFragmentInteractionListener(
                new SeriesFragment.OnFragmentInteractionListener() {
                    @Override
                    public void getSerieSpecification(Serie s) {
                        Intent i = new Intent(getBaseContext(), SpecificationsActivity.class);

                        i.putExtra("id", Integer.toString(s.mShow.mShowId));
                        i.putExtra("name", s.mShow.mName);
                        i.putExtra("summary", s.mShow.mSummary);
                        i.putExtra("officialSite", s.mShow.mOfficialSite);

                        if(s.mShow.mNetwork != null)
                            i.putExtra("network", s.mShow.mNetwork.mName);

                        if(s.mShow.mImages != null) {
                            i.putExtra("medium", s.mShow.mImages.mMediumImage);
                            i.putExtra("original", s.mShow.mImages.mOriginalImage);
                        }

                        if(s.mShow.mWebChannel!= null)
                            i.putExtra("webChannel", s.mShow.mWebChannel.mName);

                        if(s.mShow.mShedule != null) {
                            i.putExtra("time", s.mShow.mShedule.mTime);
                            i.putExtra("days", s.mShow.mShedule.mDays);
                        }

                        startActivity(i);
                    }
                }
        );
    }


    private Point getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
}
