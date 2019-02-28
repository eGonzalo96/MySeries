package com.example.android.myseries.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Schedule {

    @SerializedName("time")
    public String mTime;

    @SerializedName("days")
    public ArrayList<String> mDays;

}
