package com.cardflip;

public class Card {

    private boolean faceUp;

    public Card(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public void flip() {
        this.faceUp(!this.faceUp);
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    private void faceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        return this.faceUp ? "U" : "D";
    }
}
