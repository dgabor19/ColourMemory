package com.accedo.colourmemory.views;

/**
 * Created by gabordudas on 21/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnScoringListener;
import com.accedo.colourmemory.models.Card;
import com.accedo.colourmemory.utils.CardGenerator;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom LinearLayout to store cards in grid
 */
public class CardGridLayout extends LinearLayoutCompat {
    public static final String TAG = CardGridLayout.class.getSimpleName();

    private List<Card> mCards;
    private OnScoringListener mListener;
    private boolean mCardFlippingEnabler = true;

    public CardGridLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public CardGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CardGridLayout(Context context) {
        super(context);

    }

    /**
     * Initialize the layout after creating
     * @param columnCount
     * @param rowCount
     * @param listener
     */
    public void init(final int columnCount, final int rowCount, OnScoringListener listener) {

        removeAllViews();

        setOrientation(VERTICAL);
        mListener = listener;

        mCards = CardGenerator.newInstance().getShuffledCards();

        final Bitmap cardFaceBitmap = BitmapFactory.decodeResource(getContext().getResources(), mCards.get(0).getColour().resId);

        // Rows
        for (int i = 0; i < rowCount; i++) {

            final LinearLayoutCompat rowLayout = new LinearLayoutCompat(getContext());
            rowLayout.setOrientation(HORIZONTAL);

            // Columns
            for (int j = 0; j < columnCount; j++) {
                final View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, this, false);

                ((ImageView) view.findViewById(R.id.imageFaceCard)).setImageResource(mCards.get(i * columnCount + j).getColour().resId);

                final View card = view.findViewById(R.id.card);

                card.setTag(R.string.tag_row, i);
                card.setTag(R.string.tag_column, j);

                card.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int row = (int) v.getTag(R.string.tag_row);
                        final int column = (int) v.getTag(R.string.tag_column);

                        final int index = row * columnCount + column;
                        int cardOnFaceSum = 0;

                        Card card = mCards.get(index);

                        // Skip card which is already paired
                        if (!card.isPaired() && mCardFlippingEnabler) {

                            card.setFaceUp(!card.isFaceUp());

                            // Counting the cards with face up
                            for (Card c : mCards) {
                                if (c.isFaceUp() && !c.isPaired()) {
                                    ++cardOnFaceSum;
                                }
                            }

                            // Animate the card to face up
                            CardUtils.animateCardFlip(getContext(), v, null);

                            if (card.isFaceUp()) {

                                // If there are already 2 cards with face up
                                if (cardOnFaceSum > 1) {

                                    mCardFlippingEnabler = false;

                                    // Find the card was faced up beside the current one
                                    Card otherCardFaceUp = null;
                                    for (int i = 0; i < mCards.size(); i++) {
                                        Card tempCard = mCards.get(i);
                                        if (tempCard.isFaceUp() && i != index && !tempCard.isPaired()) {
                                            otherCardFaceUp = tempCard;
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

                                            // Notifying the fragment about match or
                                            // game over, all card faced up and paired
                                            mListener.onScore(Constants.MATCH_SCORE, cardPairedSum == columnCount * rowCount);

                                            if (!mCardFlippingEnabler) {
                                                mCardFlippingEnabler = true;
                                            }
                                        }


                                    } else { // Flipping the cards back with face up

                                        final List<View> cardsToFaceDown = new ArrayList<>();
                                        for (int i = 0; i < mCards.size(); i++) {
                                            Card c = mCards.get(i);

                                            if (c.isFaceUp() && !c.isPaired()) {
                                                int flipBackRow = i / columnCount;
                                                int flipBackColumn = i % rowCount;

                                                cardsToFaceDown.add(((ViewGroup) getChildAt(flipBackRow)).getChildAt(flipBackColumn));
                                                c.setFaceUp(false);
                                            }
                                        }

                                        // Adding some delay for the user to see and memorize the cards
                                        getHandler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Flipping all of the flipped cards to face down
                                                for (View v : cardsToFaceDown) {

                                                    CardUtils.animateCardFlip(getContext(), v, new Animation.AnimationListener() {
                                                        @Override
                                                        public void onAnimationStart(Animation animation) {
                                                        }

                                                        @Override
                                                        public void onAnimationEnd(Animation animation) {
                                                            if (!mCardFlippingEnabler) {
                                                                mCardFlippingEnabler = true;
                                                            }
                                                        }

                                                        @Override
                                                        public void onAnimationRepeat(Animation animation) {

                                                        }
                                                    });
                                                }

                                                // Notifying the fragment that there is no match
                                                if (mListener != null) {
                                                    mListener.onScore(Constants.NO_MATCH_SCORE, false);
                                                }
                                            }
                                        }, 1000);
                                    }
                                }
                            }
                        }
                    }

                });

                // Resizing card view height according to images aspect-ratio
                view.post(new Runnable() {
                    @Override
                    public void run() {

                        LinearLayoutCompat.LayoutParams params = (LinearLayoutCompat.LayoutParams) view.getLayoutParams();
                        params.height = (int)((float)view.getWidth() * (float)cardFaceBitmap.getHeight() / (float)cardFaceBitmap.getWidth());


                        view.setLayoutParams(params);
                    }
                });

                rowLayout.addView(view);
            }

            addView(rowLayout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
