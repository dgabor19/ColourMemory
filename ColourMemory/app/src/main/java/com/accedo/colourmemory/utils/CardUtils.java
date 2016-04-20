package com.accedo.colourmemory.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.accedo.colourmemory.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by gabordudas on 19/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardUtils {

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
