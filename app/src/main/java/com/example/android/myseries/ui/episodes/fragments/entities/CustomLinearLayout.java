package com.example.android.myseries.ui.episodes.fragments.entities;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class CustomLinearLayout
    extends LinearLayoutManager {

    public CustomLinearLayout(Context c) {
        super(c);
    }


    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
