package com.example.android.myseries.data.episodes.entities;

import com.example.android.myseries.data.series.entities.Images;
import com.google.gson.annotations.SerializedName;

public class Episode {

    @SerializedName("name")
    public String mEpisodeName;

    @SerializedName("number")
    public int mEpisodeNumber;

    @SerializedName("season")
    public int mEpisodeSeason;

    @SerializedName("summary")
    public String mEpisodeSummary;

    @SerializedName("image")
    public Images mEpisodesImages;
}
