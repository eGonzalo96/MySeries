package com.example.android.myseries.data.entities;

import com.google.gson.annotations.SerializedName;

public class Show {

    @SerializedName("image")
    public Images mImages;

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

    @SerializedName("webChannel")
    public Channel mWebChannel;

}
