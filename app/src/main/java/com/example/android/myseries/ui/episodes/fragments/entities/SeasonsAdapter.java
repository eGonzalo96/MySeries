package com.example.android.myseries.ui.episodes.fragments.entities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myseries.R;
import com.example.android.myseries.data.episodes.entities.Episode;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SeasonsAdapter
    extends RecyclerView.Adapter<SeasonsAdapter.SeasonsViewHolder> {

    ArrayList<Episode> mEpisodesList;


    public SeasonsAdapter(ArrayList<Episode> l) {
        this.mEpisodesList = l;
    }


    @Override
    public void onBindViewHolder(@NonNull SeasonsViewHolder holder, int position) {
        holder.bind(mEpisodesList.get(position));
    }


    @NonNull
    @Override
    public SeasonsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.episodes_list_item, viewGroup, false);

        return new SeasonsViewHolder(rootView);
    }


    @Override
    public int getItemCount() {
        return mEpisodesList.size();
    }


    public void setNewEpisodesList(ArrayList<Episode> e) {
        this.mEpisodesList = e;
        notifyDataSetChanged();
    }

    public class SeasonsViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        ImageView episodeImage;
        TextView episodeName;
        TextView episodeNumber;
        TextView episodeSummary;

        public SeasonsViewHolder(View v) {
            super(v);

            rootView = v;
            episodeImage = (ImageView) v.findViewById(R.id.episodeImage);
            episodeName = (TextView) v.findViewById(R.id.episodeName);
            episodeNumber = (TextView) v.findViewById(R.id.episodeNumber);
            episodeSummary = (TextView) v.findViewById(R.id.episodeSummary);
        }

        public void bind(Episode e) {

            if(e.mEpisodesImages != null
                    && e.mEpisodesImages.mMediumImage!= null
                    && !e.mEpisodesImages.mMediumImage.equals(""))
                Picasso.with(rootView.getContext())
                        .load(e.mEpisodesImages.mMediumImage)
                        .into(episodeImage);

            String aux = e.mEpisodeSummary.replaceAll("\\<.*?>", "");

            episodeSummary.setText(aux);
            episodeName.setText(e.mEpisodeName);
            episodeNumber.setText("Episode " + Integer.toString(e.mEpisodeNumber));

        }
    }
}
