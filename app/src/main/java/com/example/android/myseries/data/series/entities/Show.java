package com.example.android.myseries.data.series.entities;

import com.google.gson.annotations.SerializedName;

public class Show {

    @SerializedName("id")
    public int mShowId;

    @SerializedName("name")
    public String mName;

    @SerializedName("summary")
    public String mSummary;

    @SerializedName("schedule")
    public Schedule mShedule;

    @SerializedName("officialSite")
    public String mOfficialSite;

    @SerializedName("network")
    public Channel mNetwork;

    @SerializedName("image")
    public Images mImages;

    @SerializedName("webChannel")
    public Channel mWebChannel;

    public Show() {
        mShedule = new Schedule();
        mNetwork = new Channel();
        mImages = new Images();
        mWebChannel = new Channel();
    }
}
