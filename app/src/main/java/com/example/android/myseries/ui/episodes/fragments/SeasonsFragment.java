package com.example.android.myseries.ui.episodes.fragments;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.episodes.entities.Episode;
import com.example.android.myseries.ui.episodes.fragments.entities.CustomLinearLayout;
import com.example.android.myseries.ui.episodes.fragments.entities.SeasonsAdapter;

import java.util.ArrayList;

public class SeasonsFragment extends Fragment {

    ImageView mSeasonsImageView;
    RecyclerView mEpisodesRecycler;
    SeasonsModel mSeasonsModel;
    int mEpisodeId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_seasons, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        mSeasonsImageView = (ImageView) view.findViewById(R.id.seasonOptionButton);
        mEpisodesRecycler = (RecyclerView) view.findViewById(R.id.seasonsRecycler);

        mSeasonsModel = new SeasonsModel();
        mEpisodesRecycler.setLayoutManager(new CustomLinearLayout(view.getContext()));
        setSeaonsOptionsListener(view.getContext());
        //setSeasonsMenu()

        mSeasonsModel.getEpisodesFromServer(mEpisodeId,
                new SeasonsModel.SeasonsModelListener() {
                    @Override
                    public void onSuccess(ArrayList<Episode> e) {

                        Toast.makeText(view.getContext(), Integer.toString(e.size()), Toast.LENGTH_SHORT).show();
                        mEpisodesRecycler.setAdapter(
                                new SeasonsAdapter(e)
                        );

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(view.getContext(), "fail: " + Integer.toString(mEpisodeId), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void setSeaonsOptionsListener(final Context c) {
        mSeasonsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }



    public void setmEpisodeId(int mEpisodeId) {
        this.mEpisodeId = mEpisodeId;
    }

}
