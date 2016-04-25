package com.accedo.colourmemory.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

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

    public static void animateView(View view) {
        final Animation animation = new AlphaAnimation(1f, 0f);
        animation.setDuration(1000); // Animate for 1 seconds
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animation);
    }
}
