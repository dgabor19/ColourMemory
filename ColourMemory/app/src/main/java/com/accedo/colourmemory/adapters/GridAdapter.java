package com.accedo.colourmemory.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnCardFlipListener;
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
    private OnCardFlipListener mCardFlipListener;

    public GridAdapter(Context c, OnCardFlipListener listener) {
        mContext = c;
        mColours = CardGenerator.getShuffledCards(Constants.CARD_COUNT);
        mCardFlipListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_card, parent, false), mCardFlipListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, int position) {
        final ViewHolder holder = (ViewHolder) vHolder;

        holder.mPosition = position;
        holder.mFrontImage.setImageResource(mColours.get(position).resId);
//        holder.mFlipper.setInAnimation();
//        holder.mFlipper.setInAnimation(mContext, android.R.anim.slide_in_left);
//        holder.mFlipper.setOutAnimation(mContext, android.R.anim.slide_out_right);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mColours.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewFlipper mFlipper;
        public ImageView mBackImage;
        public ImageView mFrontImage;
        public int mPosition;
        private OnCardFlipListener mListener;

        public ViewHolder(View itemView, OnCardFlipListener listener) {
            super(itemView);

            mListener = listener;

            mFlipper = (ViewFlipper) itemView.findViewById(R.id.flipperCard);
            mFrontImage = (ImageView) itemView.findViewById(R.id.imageFrontCard);
            mBackImage = (ImageView) itemView.findViewById(R.id.imageBackCard);

            itemView.setTag(mPosition);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                final int pos = (int) v.getTag();
                mListener.onCardFlip(v, this, pos);
            }
        }
    }
}
