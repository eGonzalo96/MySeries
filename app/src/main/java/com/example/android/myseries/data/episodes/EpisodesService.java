package com.example.android.myseries.data.episodes;

import com.example.android.myseries.data.episodes.entities.Episode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EpisodesService {

    @GET("shows/{id}/episodes")
    Call<ArrayList<Episode>> getEpisodesFromServer(@Path("id") String serieId);

}
