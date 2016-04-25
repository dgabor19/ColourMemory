package com.accedo.colourmemory.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.models.Card;

/**
 * Created by gabordudas on 19/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardUtils {
    public static final String TAG = CardUtils.class.getSimpleName();

    public static void animateCardFlip(Context context, View cardItem, Animation.AnimationListener animationListener) {

        View backView = cardItem.findViewById(R.id.imageBackCard);
        View faceView = cardItem.findViewById(R.id.imageFaceCard);
        View cardView = cardItem.findViewById(R.id.card);

        FlipAnimation flipAnimation = new FlipAnimation(context, backView, faceView);

        boolean isFaceUp = faceView.getVisibility() == View.VISIBLE;
        if (isFaceUp) {
            flipAnimation.reverse();

        }

        flipAnimation.setAnimationListener(animationListener);

        cardView.startAnimation(flipAnimation);

    }
}
