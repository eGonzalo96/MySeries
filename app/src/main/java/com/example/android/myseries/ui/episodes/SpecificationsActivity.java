package com.example.android.myseries.ui.episodes;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.series.entities.Images;
import com.example.android.myseries.data.series.entities.Serie;
import com.example.android.myseries.data.series.entities.Show;
import com.example.android.myseries.ui.episodes.fragments.SeasonsFragment;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class SpecificationsActivity extends AppCompatActivity {

    Serie mSerie;
    ImageView mSerieImageView;
    TextView mSerieNameTextView;
    TextView mSerieSummaryTextView;
    TextView mDayTextView;
    TextView mHoursTextView;
    TextView mChainTextView;
    TextView mOfficialSiteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications);

        getSerieFromIntent();
        initializeViewMembers();
        atachSerieInfoToLayout();


        SeasonsFragment fragment = new SeasonsFragment();
        fragment.setmEpisodeId(mSerie.mShow.mShowId);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.episodesFragment, fragment)
                .commitNow();
    }


    private void getSerieFromIntent() {

        Intent i = getIntent();

        mSerie = new Serie();

        mSerie.mShow.mShowId = Integer.parseInt(i.getStringExtra("id"));
        mSerie.mShow.mName = i.getStringExtra("name");
        mSerie.mShow.mSummary = i.getStringExtra("summary");
        mSerie.mShow.mShedule.mTime = i.getStringExtra("time");
        mSerie.mShow.mShedule.mDays = i.getStringArrayListExtra("days");
        mSerie.mShow.mOfficialSite = i.getStringExtra("officialSite");
        mSerie.mShow.mNetwork.mName = i.getStringExtra("network");
        mSerie.mShow.mImages.mMediumImage = i.getStringExtra("medium");
        mSerie.mShow.mImages.mOriginalImage = i.getStringExtra("original");
        mSerie.mShow.mWebChannel.mName = i.getStringExtra("webChannel");
    }


    private void initializeViewMembers() {
        mSerieImageView = (ImageView) findViewById(R.id.serieEpisodesImage);
        mSerieNameTextView = (TextView) findViewById(R.id.episodeSerieTitle);
        mSerieSummaryTextView = (TextView) findViewById(R.id.episodeSerieSummary);
        mDayTextView = (TextView) findViewById(R.id.episodeSerieDays);
        mHoursTextView = (TextView) findViewById(R.id.episodeSerieHours);
        mChainTextView = (TextView) findViewById(R.id.episodeSerieChain);
        mOfficialSiteTextView = (TextView) findViewById(R.id.episodeSerieOfficialSite);
    }


    private void atachSerieInfoToLayout() {

        String aux = mSerie.mShow.mSummary.replaceAll("\\<.*?>", "");

        Picasso.with(getBaseContext())
                .load(mSerie.mShow.mImages.mOriginalImage)
                .into(mSerieImageView);

        mSerieNameTextView.append(mSerie.mShow.mName);
        mSerieSummaryTextView.append(aux);

        if(mSerie.mShow.mOfficialSite != null) {
            mOfficialSiteTextView.append(mSerie.mShow.mOfficialSite);

            mOfficialSiteTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToBrowser(mSerie.mShow.mOfficialSite);
                }
            });
        } else {
            mOfficialSiteTextView.append("Official Site Not Available");
        }

        if(mSerie.mShow.mShedule.mDays != null) {

            String newString = "";

            for(String i: mSerie.mShow.mShedule.mDays) {
                newString += (i + " ");
            }

            mDayTextView.append(newString);
        } else {
            mDayTextView.append("Days Information Not Available");
        }

        if(mSerie.mShow.mShedule.mTime != null) {
            mHoursTextView.append(mSerie.mShow.mShedule.mTime);
        } else {
            mHoursTextView.append("Hours Information Not Available");
        }

        if(mSerie.mShow.mWebChannel.mName != null) {
            mChainTextView.append(mSerie.mShow.mWebChannel.mName+", ");
        } else {
            mChainTextView.append("Web Channel Information Not Available, ");
        }

        if(mSerie.mShow.mNetwork.mName != null) {
            mChainTextView.append(mSerie.mShow.mNetwork.mName);
        } else {
            mChainTextView.append("Television Channel Information Not Available");
        }
    }


    private void goToBrowser(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(i);
    }

}
