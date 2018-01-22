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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnScoringListener;
import com.accedo.colourmemory.manager.CardRulesManager;
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

    private int mColumnCount;
    private int mRowCount;

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
     *
     * @param columnCount
     * @param rowCount
     * @param listener
     */
    public void init(final int columnCount, final int rowCount, OnScoringListener listener) {
        mColumnCount = columnCount;
        mRowCount = rowCount;

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
                View cardView = getCardView(i, j, cardFaceBitmap.getWidth(), cardFaceBitmap.getHeight());

                rowLayout.addView(cardView);
            }

            addView(rowLayout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

    private View getCardView(final int row, final int column, final int width, final int height) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_card, this, false);

        ((ImageView) view.findViewById(R.id.imageFaceCard)).setImageResource(mCards.get(row * mColumnCount + column).getColour().resId);

        final View card = view.findViewById(R.id.card);

        card.setTag(R.string.tag_row, row);
        card.setTag(R.string.tag_column, column);

        card.setOnClickListener(onClickListener);

        // Resizing card view height according to images aspect-ratio
        view.post(new Runnable() {
            @Override
            public void run() {

                LinearLayoutCompat.LayoutParams params = (LinearLayoutCompat.LayoutParams) view.getLayoutParams();
                params.height = (int) ((float) view.getWidth() * (float) height / (float) width);


                view.setLayoutParams(params);
            }
        });

        return view;
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            final int row = (int) v.getTag(R.string.tag_row);
            final int column = (int) v.getTag(R.string.tag_column);

            final int index = row * mColumnCount + column;
            int cardOnFaceSum = 0;

            Card card = mCards.get(index);

            // Skip card which is already paired
            if (!card.isPaired() && mCardFlippingEnabler) {

                card.setFaceUp(!card.isFaceUp());

                cardOnFaceSum = CardRulesManager.getCardsCountWithFaceUp(mCards);

                // Animate the card to face up
                CardUtils.animateCardFlip(getContext(), v, null);

                if (card.isFaceUp()) {

                    // If there are already 2 cards with face up
                    if (cardOnFaceSum > 1) {

                        mCardFlippingEnabler = false;

                        // Finding the card was faced up beside the current one
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
                                int cardPairedSum = CardRulesManager.getPairedCardsCount(mCards);

                                // Notifying the fragment about match or
                                // game over, all card faced up and paired
                                mListener.onScore(Constants.MATCH_SCORE, cardPairedSum == mColumnCount * mRowCount);

                                if (!mCardFlippingEnabler) {
                                    mCardFlippingEnabler = true;
                                }
                            }


                        } else { // Flipping the cards back with face up

                            final List<View> cardsToFaceDown = new ArrayList<>();
                            for (int i = 0; i < mCards.size(); i++) {
                                Card c = mCards.get(i);

                                if (c.isFaceUp() && !c.isPaired()) {
                                    int flipBackRow = i / mColumnCount;
                                    int flipBackColumn = i % mRowCount;

                                    cardsToFaceDown.add(((ViewGroup) getChildAt(flipBackRow)).getChildAt(flipBackColumn));
                                    c.setFaceUp(false);
                                }
                            }

                            // Adding some delay for the user to see and memorize the cards
                            getHandler().postDelayed(new DelayTask(cardsToFaceDown), 1000);
                        }
                    }
                } else {
                    if (mListener != null) {
                        mListener.onScore(Constants.NO_MATCH_SCORE, false);
                    }
                }
            }
        }
    };


    private class DelayTask implements Runnable {

        final List<View> mCardsToFaceDown;

        public DelayTask(List<View> cardsToFaceDown) {
            mCardsToFaceDown = cardsToFaceDown;
        }

        @Override
        public void run() {
            // Flipping all of the flipped cards to face down
            for (View v : mCardsToFaceDown) {

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
    }

    ;
}
