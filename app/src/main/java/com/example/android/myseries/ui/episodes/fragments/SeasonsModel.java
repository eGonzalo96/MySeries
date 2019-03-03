package com.example.android.myseries.ui.episodes.fragments;

import com.example.android.myseries.data.episodes.EpisodesService;
import com.example.android.myseries.data.episodes.entities.Episode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeasonsModel {

    EpisodesService mEpisodesApi;

    interface SeasonsModelListener {
        void onSuccess(ArrayList<Episode> e);
        void onFailure(Throwable t);
    }

    public SeasonsModel() {

        Retrofit retrofitObject =
                new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mEpisodesApi = retrofitObject.create(EpisodesService.class);

    }


    public void getEpisodesFromServer(
            int serieId, final SeasonsModelListener listener) {

        mEpisodesApi.getEpisodesFromServer(
                Integer.toString(serieId)).enqueue(new Callback<ArrayList<Episode>>() {
            @Override
            public void onResponse(Call<ArrayList<Episode>> call, Response<ArrayList<Episode>> response) {
                ArrayList<Episode> body = response.body();
                if(body != null) {
                    listener.onSuccess(body);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Episode>> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }


    public ArrayList<Integer> getSeasonsFromEpisodes(ArrayList<Episode> e) {

            ArrayList<Integer> seasons = new ArrayList<>();
            for(Episode i: e) {
                if(!seasons.contains(i.mEpisodeSeason)) {
                    seasons.add(i.mEpisodeSeason);
                }
            }

            return seasons;
    }


}
