package com.accedo.colourmemory.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnCardFlipListener;
import com.accedo.colourmemory.models.Card;
import com.accedo.colourmemory.utils.CardGenerator;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.utils.Constants;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.List;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class GridAdapter extends RecyclerView.Adapter {
    public static final String TAG = GridAdapter.class.getSimpleName();

    private Context mContext;
    private List<Card> mCards;
    private OnCardFlipListener mCardFlipListener;
    private Handler mHandler;

    public GridAdapter(Context c, OnCardFlipListener listener) {
        mContext = c;
        mCards = CardGenerator.getShuffledCards();
        mCardFlipListener = listener;
        mHandler = new Handler();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_card, parent, false), mCardFlipListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vHolder, final int position) {
        final ViewHolder holder = (ViewHolder) vHolder;

        Card card = mCards.get(position);

        holder.mFaceImage.setImageResource(card.getColour().resId);

    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public List<Card> getCards() {
        return mCards;
    }

    public Card getCard(int position) {
        return mCards.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewFlipper mFlipper;
        public ImageView mBackImage;
        public ImageView mFaceImage;
        private OnCardFlipListener mListener;

        public ViewHolder(View itemView, OnCardFlipListener listener) {
            super(itemView);

            mListener = listener;

            mFlipper = (ViewFlipper) itemView.findViewById(R.id.flipperCard);
            mFaceImage = (ImageView) itemView.findViewById(R.id.imageFaceCard);
            mBackImage = (ImageView) itemView.findViewById(R.id.imageBackCard);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onCardFlip(v, this, getAdapterPosition());
            }
        }
    }
}
