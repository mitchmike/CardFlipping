package com.cardflip;

public class SequenceRepository {

    private Sequence longestSequence;
    private Sequence shortestSequence;

    public SequenceRepository() {
        this.longestSequence = new Sequence();
        this.shortestSequence = new Sequence();
    }

    public void updateLongestSequence(Sequence sequence) {
        if (sequence.size() > longestSequence.size()) {
            this.longestSequence = sequence;
        }
    }

    public void updateShortestSequence(Sequence sequence) {
        if (shortestSequence.size() == 0 || sequence.size() < shortestSequence.size()) {
            this.shortestSequence = sequence;
        }
    }

    public Sequence longestSequence() {
        return longestSequence;
    }

    public Sequence shortestSequence() {
        return shortestSequence;
    }

    public void clear() {
        shortestSequence = new Sequence();
        longestSequence = new Sequence();
    }
}
