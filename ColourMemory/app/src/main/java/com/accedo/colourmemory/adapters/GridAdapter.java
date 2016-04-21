package com.accedo.colourmemory.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private boolean mCardFlipperEnabler = false;

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

//        if (card.isFaceUp()) {
//            card.setFaceUp(false);
//
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    CardUtils.flipOut(mContext, holder.itemView, null);
//
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            holder.mFlipper.showPrevious();
//                        }
//                    }, mContext.getResources().getInteger(R.integer.card_flip_time) / 2);
//                }
//            }, 1000);
//
//        }

        holder.mFaceImage.setImageResource(card.getColour().resId);

//        if (!card.isFaceUp()) {
//            CardUtils.animateCardFlip(mContext, this, holder, position);
//        }

//        if (mCardFlipperEnabler) {
//            mCardFlipperEnabler = false;
//            CardUtils.animateCardFlip(mContext, this, holder, position);
//        }

    }

    public void setCardFlipperEnabler(boolean isEnabled) {
        mCardFlipperEnabler = isEnabled;
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
        public ImageView mBackImage;
        public ImageView mFaceImage;
        public CardView mCard;
        private OnCardFlipListener mListener;

        public ViewHolder(View itemView, OnCardFlipListener listener) {
            super(itemView);

            mListener = listener;
            mFaceImage = (ImageView) itemView.findViewById(R.id.imageFaceCard);
            mBackImage = (ImageView) itemView.findViewById(R.id.imageBackCard);
            mCard = (CardView) itemView.findViewById(R.id.card);

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
