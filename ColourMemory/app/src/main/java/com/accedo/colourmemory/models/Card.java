package com.accedo.colourmemory.models;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import com.accedo.colourmemory.R;

/**
 * Model class to store Card related attributes
 */
public class Card {

    private Colour colour;
    private boolean isFaceUp;
    private boolean isPaired;

    public Card(Colour colour, boolean isFaceUp, boolean isPaired) {
        this.colour = colour;
        this.isFaceUp = isFaceUp;
        this.isPaired = isPaired;
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

    public boolean isPaired() {
        return isPaired;
    }

    public void setPaired(boolean paired) {
        isPaired = paired;
    }

    @Override
    public String toString() {
        return "Card{" +
                "colour=" + colour +
                ", isFaceUp=" + isFaceUp +
                ", isPaired=" + isPaired +
                '}';
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
