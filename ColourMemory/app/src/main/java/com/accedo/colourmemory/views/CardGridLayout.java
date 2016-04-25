package com.accedo.colourmemory.views;

import android.content.Context;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.accedo.colourmemory.BaseActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnScoringListener;
import com.accedo.colourmemory.models.Card;
import com.accedo.colourmemory.utils.CardGenerator;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabordudas on 21/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardGridLayout extends LinearLayoutCompat {
    public static final String TAG = CardGridLayout.class.getSimpleName();

    private List<Card> mCards;
    private OnScoringListener mListener;

    public CardGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public CardGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CardGridLayout(Context context) {
        super(context);

    }

    public void init(final int columnCount, final int rowCount, OnScoringListener listener) {

        removeAllViews();

        setOrientation(VERTICAL);
        mListener = listener;

        mCards = CardGenerator.newInstance().getShuffledCards();

        // Rows
        for (int i = 0; i < rowCount; i++) {

            LinearLayoutCompat rowLayout = new LinearLayoutCompat(getContext());
            rowLayout.setOrientation(HORIZONTAL);

            // Columns
            for (int j = 0; j < columnCount; j++) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, this, false);

                ((ImageView) view.findViewById(R.id.imageFaceCard)).setImageResource(mCards.get(i * columnCount + j).getColour().resId);

                Log.d(TAG, "CARD row " + i + " column " + j + " pos " + (i * columnCount + j));

                view.setTag(R.string.tag_row, i);
                view.setTag(R.string.tag_column, j);

                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int row = (int) v.getTag(R.string.tag_row);
                        final int column = (int) v.getTag(R.string.tag_column);

                        final int index = row * columnCount + column;
                        int cardOnFaceSum = 0;

                        Card card = mCards.get(index);

                        Log.d(TAG, "CARD clicked on " + index);

                        // Skip card which is already paired
                        if (!card.isPaired()) {

                            card.setFaceUp(true);

                            // Counting the cards with face up
                            for (Card c : mCards) {
                                if (c.isFaceUp() && !c.isPaired()) {
                                    ++cardOnFaceSum;
                                }
                            }

                            CardUtils.animateCardFlip(getContext(), v, card);


                            // If there are already 2 cards with face up
                            if (cardOnFaceSum > 1) {

                                // Find the card was faced up beside the current one
                                Card otherCardFaceUp = null;
                                int otherCardFaceUpPosition = -1;
                                for (int i = 0; i < mCards.size(); i++) {
                                    Card tempCard = mCards.get(i);
                                    if (tempCard.isFaceUp() && i != index && !tempCard.isPaired()) {
                                        otherCardFaceUp = tempCard;
                                        otherCardFaceUpPosition = i;
                                    }
                                }

                                // Finding the cards match
                                if (otherCardFaceUp != null && card.getColour() == otherCardFaceUp.getColour()) {
                                    card.setPaired(true);
                                    otherCardFaceUp.setPaired(true);

                                    if (mListener != null) {
                                        int cardPairedSum = 0;
                                        for (Card c : mCards) {
                                            if (c.isPaired()) {
                                                ++cardPairedSum;
                                            }
                                        }

                                        // Game over, all card faced up and paired
                                        mListener.onScore(Constants.MATCH_SCORE, cardPairedSum == columnCount * rowCount);
                                    }


                                } else { // Flipping the cards back with face up
                                    final List<Integer> cardsToFaceDown = new ArrayList<>();
                                    for (int i = 0; i < mCards.size(); i++) {
                                        Card c = mCards.get(i);

                                        if (c.isFaceUp() && !c.isPaired()) {
                                            cardsToFaceDown.add(i);
                                            c.setFaceUp(false);
                                        }
                                    }

                                    // Adding some delay for the user to see and memorize the cards
                                    getHandler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            for (int i : cardsToFaceDown) {
                                                Card card = mCards.get(i);

                                                int row = i / columnCount;
                                                int column = i % rowCount;

                                                CardUtils.animateCardFlip(getContext(), ((ViewGroup)getChildAt(row)).getChildAt(column), card);

                                                card.setFaceUp(false);
                                            }

                                            if (mListener != null) {
                                                mListener.onScore(Constants.NO_MATCH_SCORE, false);
                                            }
                                        }
                                    }, 1000);
                                }
                            }
                        }
                    }

                });

                rowLayout.addView(view);
            }

            addView(rowLayout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
