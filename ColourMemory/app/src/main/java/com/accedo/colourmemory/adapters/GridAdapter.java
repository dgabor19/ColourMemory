package com.accedo.colourmemory.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.models.Colour;
import com.accedo.colourmemory.utils.CardGenerator;
import com.accedo.colourmemory.utils.Constants;

import java.util.List;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class GridAdapter extends RecyclerView.Adapter {
    public static final String TAG = GridAdapter.class.getSimpleName();

    private Context mContext;
    private List<Colour> mColours;

    public GridAdapter(Context c) {
        mContext = c;
        mColours = CardGenerator.getShuffledCards(Constants.CARD_COUNT);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        final ViewHolder holder = (ViewHolder) vHolder;

        holder.mImage.setImageResource(mColours.get(position).resId);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mColours.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mImage = (ImageView) itemView.findViewById(R.id.imageCard);
        }
    }
}
