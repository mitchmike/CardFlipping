package com.cardflip;

import java.util.ArrayList;

public class CardSet {

    private ArrayList<Card> cards;

    public CardSet(final ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean addCards(final String cardString) {
        cards.clear();
        final String[] cardChars = cardString.split(", ");
        for (int i = 0; i < cardChars.length; i ++) {
            switch(cardChars[i]) {
                case "D":
                    cards.add(new Card(false));
                    break;
                case "U":
                    cards.add(new Card(true));
                    break;
                default:
                    System.out.println("Cannot add card, invalid char input: " + cardChars[i]);
                    return false;
            }
        }
        return true;
    }

    public boolean flipCard(final int cardIndex) {
        final Card cardToFlip;
        final Card cardToImmediateRight;
        if (cardIndex < cards.size()) {
            cardToFlip = cards.get(cardIndex);
            if (cardToFlip.isFaceUp()) {
                System.out.println("Not flipping card as it is face up");
                return false;
            } else {
                cardToFlip.flip();
                if (cardIndex + 1 < cards.size()) {
                    // Also flip card to immediate right if it exists
                    cardToImmediateRight = cards.get(cardIndex + 1);
                    cardToImmediateRight.flip();
                }
                return true;
            }
        }
        System.out.println("invalid index for flipping: "+ cardIndex);
        return false;
    }

    public boolean revertFlip(final int cardIndex) {
        if (cardIndex < cards.size()) {
            cards.get(cardIndex).flip();
            if (cardIndex + 1  < cards.size()) {
                cards.get(cardIndex + 1).flip();
            }
            return true;
        }
        return false;
    }

    public ArrayList<Integer> availableForFlipping() {
        final ArrayList<Integer> availableForFlipping = new ArrayList<>();
        for(int i = 0; i < cards.size(); i ++) {
            if (!cards.get(i).isFaceUp()) {
                availableForFlipping.add(i);
            }
        }
        return availableForFlipping;
    }

    @Override
    public String toString() {
        return "CardSet{" +
                "cards=" + cards +
                '}';
    }
}
