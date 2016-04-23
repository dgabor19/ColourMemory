package com.accedo.colourmemory.utils;

import com.accedo.colourmemory.models.Card;

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

    private static List<Card> AVAILABLE_CARDS = Arrays.asList(
            new Card(Card.Colour.RED, false, false),
            new Card(Card.Colour.YELLOW, false, false),
            new Card(Card.Colour.GREEN, false, false),
            new Card(Card.Colour.GREENISH_BLUE, false, false),
            new Card(Card.Colour.BLUE, false, false),
            new Card(Card.Colour.DARK_BLUE, false, false),
            new Card(Card.Colour.PURPLE, false, false),
            new Card(Card.Colour.BROWN, false, false),
            new Card(Card.Colour.RED, false, false),
            new Card(Card.Colour.YELLOW, false, false),
            new Card(Card.Colour.GREEN, false, false),
            new Card(Card.Colour.GREENISH_BLUE, false, false),
            new Card(Card.Colour.BLUE, false, false),
            new Card(Card.Colour.DARK_BLUE, false, false),
            new Card(Card.Colour.PURPLE, false, false),
            new Card(Card.Colour.BROWN, false, false)
    );

    /**
     * Generates shuffled card set
     *
     * @return
     */
    public static List<Card> getShuffledCards() {
        List<Card> shuffledCards = Arrays.asList(new Card[AVAILABLE_CARDS.size()]);
        Collections.copy(shuffledCards, AVAILABLE_CARDS);

        Collections.shuffle(shuffledCards);

        return shuffledCards;
    }

}
