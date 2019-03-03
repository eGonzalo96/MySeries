package com.example.android.myseries.data.series.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Serie {

    @SerializedName("show")
    public Show mShow;

    public Serie() {
        mShow = new Show();
    }

}
