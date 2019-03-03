package com.example.android.myseries.ui.episodes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.episodes.entities.Episode;
import com.example.android.myseries.ui.episodes.fragments.entities.CustomLinearLayout;
import com.example.android.myseries.ui.episodes.fragments.entities.SeasonsAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SeasonsFragment extends Fragment {

    ImageView mSeasonsImageView;
    RecyclerView mEpisodesRecycler;
    TextView mEpisodeSeasonTextView;
    SeasonsModel mSeasonsModel;
    SeasonsAdapter mSeasonsAdapter;
    PopupMenu mSeasonsMenu;
    MenuItem mPreviousMenuItem;
    ArrayList<Episode> mEpisodesList;
    ArrayList<Episode> mEpisodesListPerSeason;
    ArrayList<Integer> mNumberOfSeasons;
    int mEpisodeId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_seasons, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        mEpisodeSeasonTextView = (TextView) view.findViewById(R.id.episodes);
        mSeasonsImageView = (ImageView) view.findViewById(R.id.seasonOptionButton);
        mEpisodesRecycler = (RecyclerView) view.findViewById(R.id.seasonsRecycler);

        mSeasonsModel = new SeasonsModel();
        mEpisodesRecycler.setLayoutManager(new CustomLinearLayout(view.getContext()));
        setSeasonsOptionsListener(view.getContext());

        mSeasonsModel.getEpisodesFromServer(mEpisodeId,
                new SeasonsModel.SeasonsModelListener() {
                    @Override
                    public void onSuccess(ArrayList<Episode> e) {
                        mEpisodesList = e;
                        mEpisodesListPerSeason = getEpisodesOfSeason(1);
                        mNumberOfSeasons = mSeasonsModel.getSeasonsFromEpisodes(e);
                        mSeasonsAdapter = new SeasonsAdapter(mEpisodesListPerSeason);

                        mSeasonsMenu = new PopupMenu(view.getContext(), mSeasonsImageView);
                        prepareMenu(mSeasonsMenu.getMenu(), mNumberOfSeasons);
                        mSeasonsMenu.inflate(R.menu.episodes_menu);
                        mSeasonsMenu.setOnMenuItemClickListener(getMenuListener());

                        for(int i: mNumberOfSeasons) {
                            Log.v("SEASONS", Integer.toString(i));
                        }

                        mEpisodesRecycler.setAdapter(mSeasonsAdapter);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(view.getContext(), "fail: " + Integer.toString(mEpisodeId), Toast.LENGTH_SHORT).show();
                    }
                });


    }


    private void setSeasonsOptionsListener(final Context c) {
        mSeasonsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNumberOfSeasons.size() > 1) {
                    mSeasonsMenu.show();
                } else {
                    Toast.makeText(v.getContext(),
                            "Only Season 1 Is Available",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void prepareMenu(Menu menu, ArrayList<Integer> list) {
        menu.clear();
        for(int i: list) {
            menu.add(0, i, Menu.NONE, "Season " + Integer.toString(i));
        }
        mPreviousMenuItem = menu.getItem(0);
        mPreviousMenuItem.setVisible(false);
        mEpisodeSeasonTextView
                .setText("Episodes (Season " + Integer.toString(1) + ")");
    }


    public void setmEpisodeId(int mEpisodeId) {
        this.mEpisodeId = mEpisodeId;
    }


    private PopupMenu.OnMenuItemClickListener getMenuListener() {

        return new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                mPreviousMenuItem.setVisible(true);

                mPreviousMenuItem = menuItem;
                menuItem.setVisible(false);
                int selectedSeason = menuItem.getItemId();
                mEpisodeSeasonTextView
                        .setText("Episodes (Season " + Integer.toString(selectedSeason) + ")");
                mEpisodesListPerSeason =
                        getEpisodesOfSeason(selectedSeason);
                mSeasonsAdapter.setNewEpisodesList(mEpisodesListPerSeason);

                return false;
            }
        };
    }


    private ArrayList<Episode> getEpisodesOfSeason(int i) {

        ArrayList<Episode> episodes = new ArrayList<>(0);

        for(Episode e: mEpisodesList) {
            if(e.mEpisodeSeason == i) {
                episodes.add(e);
            }
        }

        return episodes;
    }

}