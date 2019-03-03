package com.example.android.myseries.ui.main.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.myseries.R;
import com.example.android.myseries.data.series.entities.Serie;
import com.example.android.myseries.ui.main.SearchViewListener;
import com.example.android.myseries.ui.main.fragments.entities.SerieAdapter;

import java.util.ArrayList;

public class SeriesFragment
        extends Fragment implements SearchViewListener {

    SerieAdapter mSerieAdapter;
    RecyclerView mSeriesRecyclerView;
    Point mScreenDimensions;
    OnFragmentInteractionListener mFragmentListener;
    SeriesModel mModel;

    public interface OnFragmentInteractionListener {
        void getSerieSpecification(Serie s);
    }


    public SeriesFragment() {
        super();
        mModel = new SeriesModel();
    }

    public void setOnFragmentInteractionListener
            (OnFragmentInteractionListener l) {
        this.mFragmentListener = l;
    }


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
        mSeriesRecyclerView.setLayoutManager(
                new GridLayoutManager(getContext(), 3));

        //mSeriesRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
        //        DividerItemDecoration.VERTICAL));

    }


    // From SearchViewListener
    @Override
    public void getSeriesList(String serieName) {

        mModel.getSeriesFromServer(serieName,
                new SeriesModel.SeriesModelListener() {
                    @Override
                    public void onSuccess(ArrayList<Serie> serieList) {
                        mSerieAdapter = new SerieAdapter(serieList,
                                new SerieAdapter.OnSerieClickListener() {
                                    @Override
                                    public void getSerieFromItem(Serie s) {
                                        mFragmentListener.getSerieSpecification(s);
                                    }
                                }, mScreenDimensions);
                        mSeriesRecyclerView.setAdapter(mSerieAdapter);
                    }

                    @Override
                    public void onFailure() {

                    }
                });
    }

    public void setScreenDimensions(Point d) {
        this.mScreenDimensions = d;
    }
}