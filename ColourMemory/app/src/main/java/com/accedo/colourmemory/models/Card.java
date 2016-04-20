package com.accedo.colourmemory.models;

import com.accedo.colourmemory.R;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class Card {

    private Colour colour;
    private boolean isFaceUp;

    public Card(Colour colour, boolean isFaceUp) {
        this.colour = colour;
        this.isFaceUp = isFaceUp;
    }

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public enum Colour {
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
}
