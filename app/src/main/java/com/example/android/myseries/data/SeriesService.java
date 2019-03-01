package com.example.android.myseries.data;


import com.example.android.myseries.data.entities.Serie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SeriesService {

    @GET("search/shows?")
    Call<ArrayList<Serie>> getSeriesList(@Query("q") String serieName);
}
