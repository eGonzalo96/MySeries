package com.example.android.myseries.data.series;


import com.example.android.myseries.data.series.entities.Serie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SeriesService {

    @GET("search/shows?")
    Call<ArrayList<Serie>> getSeriesList(@Query("q") String serieName);
}
