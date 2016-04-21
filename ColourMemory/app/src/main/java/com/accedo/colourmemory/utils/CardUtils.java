package com.accedo.colourmemory.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.adapters.GridAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by gabordudas on 19/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardUtils {
    public static final String TAG = CardUtils.class.getSimpleName();

    public static void animateCardFlip(Context context, GridAdapter adapter, GridAdapter.ViewHolder holder, int position) {

        FlipAnimation flipAnimation = new FlipAnimation(context, holder.mBackImage, holder.mFaceImage);

        if (adapter != null) {
            boolean isFaceUp = holder.mFaceImage.getVisibility() == View.VISIBLE;
            if (isFaceUp) {
                flipAnimation.reverse();

            }

            adapter.getCard(position).setFaceUp(!isFaceUp);

            Log.d(TAG, "CARD SET " + position + " " + !isFaceUp);

            holder.mCard.startAnimation(flipAnimation);
        }
    }

    public static void flipIn(Context context, View view, Animator.AnimatorListener listener) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", -180.0f, 0.0f);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.setDuration(context.getResources().getInteger(R.integer.card_flip_time));
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    public static void flipOut(Context context, View view, Animator.AnimatorListener listener) {

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, -180.0f);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.setDuration(context.getResources().getInteger(R.integer.card_flip_time));
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    public static void fadeIn(Context context, View view, Animator.AnimatorListener listener) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.setDuration(context.getResources().getInteger(R.integer.card_flip_time));
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
    }
}
