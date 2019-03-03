package com.example.android.myseries.ui.main.fragments;

import android.util.Log;

import com.example.android.myseries.data.series.SeriesService;
import com.example.android.myseries.data.series.entities.Serie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeriesModel {

    SeriesService seriesApi;

    interface SeriesModelListener {
        public void onSuccess(ArrayList<Serie> serieList);
        public void onFailure();
    }

    SeriesModel() {

        Retrofit retroObj = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        seriesApi = retroObj.create(SeriesService.class);

    }


    public void getSeriesFromServer(
            final String serieName, final SeriesModelListener listener) {


        seriesApi
                .getSeriesList(formatSerieName(serieName))
                .enqueue(new Callback<ArrayList<Serie>>() {

            @Override
            public void onResponse(Call<ArrayList<Serie>> call, Response<ArrayList<Serie>> response) {
                ArrayList<Serie> list = response.body();

                if(list != null) {
                    listener.onSuccess(list);

                    for(Serie i: list) {
                        if(i.mShow.mImages == null)
                        Log.v("IMAGES", i.mShow.mName + " es mImages null " + i.mShow.mOfficialSite);
                    }
                } else {
                    Log.v("ERROR2", formatSerieName(serieName));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Serie>> call, Throwable t) {
                Log.v("ERROR3", "ERROR3");
            }

        });
    }


    private String formatSerieName(String s){
        return String.join("+", s.toLowerCase().trim().split(" "));
    }

}
