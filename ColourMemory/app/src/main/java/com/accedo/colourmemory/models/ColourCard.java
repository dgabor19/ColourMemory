package com.accedo.colourmemory.models;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class ColourCard {
    public static final String TAG = ColourCard.class.getSimpleName();

    private Colour colour;
    private int index;

    public ColourCard(Colour colour, int index) {
        this.colour = colour;
        this.index = index;
    }


    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
