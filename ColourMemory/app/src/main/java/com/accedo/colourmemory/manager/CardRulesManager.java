package com.accedo.colourmemory.manager;

import com.accedo.colourmemory.models.Card;

import java.util.List;

/**
 * Created by gabordudas on 2018-01-22.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

public class CardRulesManager {

    /**
     * Counting the cards with face up
     *
     * @param cards
     * @return
     */
    public static int getCardsCountWithFaceUp(final List<Card> cards) {
        int cardOnFaceSum = 0;
        for (Card c : cards) {
            if (c.isFaceUp() && !c.isPaired()) {
                ++cardOnFaceSum;
            }
        }

        return cardOnFaceSum;
    }

    public static int getPairedCardsCount(final List<Card> cards) {
        int cardPairedSum = 0;
        for (Card c : cards) {
            if (c.isPaired()) {
                ++cardPairedSum;
            }
        }

        return cardPairedSum;
    }
}
