package com.cardflip;

import java.util.ArrayList;

public class Sequence {

    private ArrayList<Integer> moves;

    public Sequence() {
        this.moves = new ArrayList<>();
    }

    public void addMove(final Integer cardIndex) {
        moves.add(cardIndex);
    }

    public Integer getLastMove() {
        return moves.get(moves.size() -1);
    }

    public void dropLastMove() {
        moves.remove(moves.size() -1);
    }

    public int size() {return moves.size();}

    public Sequence createCopy() {
        final Sequence target = new Sequence();
        for (final Integer move : moves) {
            target.addMove(move);
        }
        return target;
    }

    @Override
    public String toString() {
        return "Sequence{" +
                "moves=" + moves +
                '}';
    }
}
