package com.accedo.colourmemory.interfaces;

import android.view.View;

import com.accedo.colourmemory.adapters.GridAdapter;

/**
 * Created by gabordudas on 19/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public interface OnCardFlipListener {
    void onCardFlip(View view, GridAdapter.ViewHolder holder, int position);
}
