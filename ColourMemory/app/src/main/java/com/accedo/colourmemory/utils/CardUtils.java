package com.accedo.colourmemory.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by gabordudas on 19/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardUtils {

    public static void flip(View view, Animator.AnimatorListener listener) {

        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", 0.0f, 180.0f);
        if (listener != null) {
            animation.addListener(listener);
        }
        animation.setDuration(400);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.start();
    }
}
