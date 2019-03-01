package com.example.android.myseries.ui.main.fragments.entities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.entities.Serie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SerieAdapter
    extends RecyclerView.Adapter<SerieAdapter.SerieViewHolder> {

    ArrayList<Serie> mSeriesList;
    OnSerieClickListener mListener;

    public SerieAdapter(ArrayList<Serie> list, OnSerieClickListener listener) {
        super();
        mSeriesList = list;
        mListener = listener;
    }


    public interface OnSerieClickListener {
        void getSerieFromItem(Serie s);
    }


    @NonNull
    @Override
    public SerieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.series_list_item, viewGroup, false);

        return new SerieViewHolder(rootView);
    }


    @Override
    public void onBindViewHolder(
            @NonNull SerieViewHolder serieViewHolder, int position) {
        serieViewHolder.bind(position);
    }


    @Override
    public int getItemCount() {
        return mSeriesList.size();
    }


    public class SerieViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView serieName;
        TextView serieChain;
        ImageView serieImage;

        SerieViewHolder(View v) {
            super(v);
            rootView = v;
            serieImage = (ImageView) v.findViewById(R.id.serieItemImage);
            serieName = (TextView) v.findViewById(R.id.serieName);
            serieChain = (TextView) v.findViewById(R.id.serieChain);
        }


        public void bind(int position) {

            final Serie i = mSeriesList.get(position);

            if (i.mShow.mImages != null)
                Picasso.with(rootView.getContext())
                        .load(i.mShow.mImages.mMediumImage)
                        .into(serieImage);

            if (i.mShow.mNetwork != null) {
                serieChain.setText(i.mShow.mNetwork.mName);
            } else {
                serieChain.setText(i.mShow.mWebChannel.mName);
            }

            serieName.setText(mSeriesList.get(position).mShow.mName);

            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.getSerieFromItem(i);
                }
            });
        }
    }
}