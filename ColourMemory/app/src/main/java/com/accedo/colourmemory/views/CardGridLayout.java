package com.accedo.colourmemory.views;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnCardFlipListener;
import com.accedo.colourmemory.models.Card;
import com.accedo.colourmemory.utils.CardGenerator;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.utils.Constants;

import java.util.List;

/**
 * Created by gabordudas on 21/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardGridLayout extends GridLayout {
    public static final String TAG = CardGridLayout.class.getSimpleName();

    private List<Card> mCards;
    private OnCardFlipListener mOnCardFlipListener;

    public CardGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public CardGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CardGridLayout(Context context) {
        super(context);

    }

    public void init(OnCardFlipListener onCardFlipListener) {
        mOnCardFlipListener = onCardFlipListener;

        setColumnCount(Constants.COLUMN_COUNT);
        setRowCount(Constants.ROW_COUNT);

        mCards = CardGenerator.getShuffledCards();

        for (int i = 0; i < mCards.size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, this, false);

            ((ImageView) view.findViewById(R.id.imageFaceCard)).setImageResource(mCards.get(i).getColour().resId);



            final View card = view.findViewById(R.id.card);
            card.setTag(i);
            card.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCardFlipListener != null) {

                        final int index = (int) v.getTag();
                        int cardOnFaceSum = 0;

                        Card card = mCards.get(index);

                        for (int i = 0; i < mCards.size(); i++) {
                            Card c = mCards.get(i);
                            if (c.isFaceUp()) {
                                ++cardOnFaceSum;
                            }
                        }

                        if (cardOnFaceSum < 2) {
                            card.setFaceUp(true);

//                            mOnCardFlipListener.onCardFlip(v, index);
                            CardUtils.animateCardFlip(getContext(), getChildAt(index), card);

                        }

                        Log.d(TAG, "CARD cardOnFaceSum " + cardOnFaceSum + " " + mCards);

                        if (cardOnFaceSum > 0) {

                            Card otherCardFaceUp = null;
                            for (int i = 0; i < mCards.size(); i++) {
                                Card tempCard = mCards.get(i);
                                if (tempCard.isFaceUp() && i != index) {
                                    otherCardFaceUp = tempCard;
                                }
                            }

                            if (otherCardFaceUp != null && card.getColour() == otherCardFaceUp.getColour()) {
                                Toast.makeText(getContext(), "MATCH!!! " + card.getColour(), Toast.LENGTH_LONG).show();

                            } else {

                                getHandler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i = 0; i < mCards.size(); i++) {
                                            Card card = mCards.get(i);

                                            if (card.isFaceUp()) {
                                                Log.d(TAG, "CARD removed " + i + " " + mCards);

                                                CardUtils.animateCardFlip(getContext(), getChildAt(i), card);

                                                card.setFaceUp(false);
                                            }
                                        }
                                    }
                                }, 1000);
                            }
                        }
                    }
                }
            });

            addView(view);
        }
    }
}
