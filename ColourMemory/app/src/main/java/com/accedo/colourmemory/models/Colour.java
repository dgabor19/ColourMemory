package com.accedo.colourmemory.models;

import com.accedo.colourmemory.R;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public enum  Colour {
    RED(R.drawable.colour1),
    YELLOW(R.drawable.colour2),
    GREEN(R.drawable.colour3),
    GREENISH_BLUE(R.drawable.colour4),
    BLUE(R.drawable.colour5),
    DARK_BLUE(R.drawable.colour6),
    PURPLE(R.drawable.colour7),
    BROWN(R.drawable.colour8);

    public int resId;

    Colour(int resId) {
        this.resId = resId;
    }

}
