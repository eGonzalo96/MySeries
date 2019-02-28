package com.example.android.myseries.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.myseries.R;
import com.example.android.myseries.ui.main.SearchViewListener;

public class SeriesFragment
        extends Fragment implements SearchViewListener {

    RecyclerView mSeriesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_main_activity, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mSeriesRecyclerView =
                (RecyclerView) view.findViewById(R.id.fragmentRecyclerView);
    }


    // From SearchViewListener
    @Override
    public void getSeriesList(String serieName) {

    }
}
