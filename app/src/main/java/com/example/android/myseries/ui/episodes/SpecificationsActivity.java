package com.example.android.myseries.ui.episodes;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.series.entities.Images;
import com.example.android.myseries.data.series.entities.Serie;
import com.example.android.myseries.data.series.entities.Show;
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
        atachInfoToLaoyut();

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


    private void atachInfoToLaoyut() {

        String aux = mSerie.mShow.mSummary.replaceAll("\\<.*?>", "");

        Picasso.with(getBaseContext())
                .load(mSerie.mShow.mImages.mOriginalImage)
                .error(R.drawable.ic_launcher_background)
                .into(mSerieImageView);

        mSerieNameTextView.setText(mSerie.mShow.mName);
        mSerieSummaryTextView.setText(aux);
        mOfficialSiteTextView.setText(mSerie.mShow.mOfficialSite);

        if(mSerie.mShow.mImages != null) {

            String newString = "";

            for(String i: mSerie.mShow.mShedule.mDays) {
                newString += (i + " ");
            }

            mDayTextView.setText(newString);
            mHoursTextView.setText(mSerie.mShow.mShedule.mTime);
        } else {
            mDayTextView.setVisibility(View.GONE);
            mHoursTextView.setVisibility(View.GONE);
        }

        mChainTextView.setText(mSerie.mShow.mWebChannel.mName);

    }

}
