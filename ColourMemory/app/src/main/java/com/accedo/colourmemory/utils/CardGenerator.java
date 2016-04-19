package com.accedo.colourmemory.utils;

import com.accedo.colourmemory.models.Colour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class CardGenerator {
    public static final String TAG = CardGenerator.class.getSimpleName();

    private static List<Colour> AVAILABLE_CARDS = Arrays.asList(
            Colour.RED,
            Colour.YELLOW,
            Colour.GREEN,
            Colour.GREENISH_BLUE,
            Colour.BLUE,
            Colour.DARK_BLUE,
            Colour.PURPLE,
            Colour.BROWN

    );

    public static List<Colour> getShuffledCards(int cardsNum) {
        List<Colour> shuffledCards = new ArrayList<>();
        shuffledCards.addAll(AVAILABLE_CARDS);
        shuffledCards.addAll(AVAILABLE_CARDS);

        Collections.shuffle(shuffledCards);

        return shuffledCards;
    }

}
