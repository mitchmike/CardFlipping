package com.cardflip;

import java.util.ArrayList;
import java.util.Scanner;

public class CardFlipMain {

    private final static SequenceRepository sequenceRepository = new SequenceRepository();

    /**
     * This program flips cards until all are facing up
     * Only face-down cards can be flipped
     * Flipping a card will also flip its neighbour to the immediate right (if this neighbour exists)
     * The program recursively checks every sequence of card flips to find the longest and shortest possible number of moves
     *
     * The move sequences can be seen as a decision tree, branching out as we make move moves.
     *
     * e.g. U, U, D, U, D
     *      1, 2, 3, 4, 5
     * at move 0 we have 2 options (so two branches of the tree)
     * --> 3
     * --> 5
     *
     * if we pick 3 then the cards will be U, U, U, D, D
     * now there are two options again
     * --> 3 --> 4
     *       --> 5
     *
     * if we pick 4 then all cards are face up and we are finished with this sequence.
     *
     * We check the length of this finished sequence against our current shortest and longest sequences and update as needed.
     * We then revert the last flip and move back to the previous decision point, this time exercising the 2nd option
     */

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a pattern like D, D, D, D, D: ");
        final String input = scanner.nextLine();
        runCardFlipGame(input);
    }

    static SequenceRepository runCardFlipGame(final String cardList) {
        sequenceRepository.clear();
        System.out.println("Running game with " + cardList);
        final  CardSet cardSet = new CardSet(new ArrayList<>());
        cardSet.addCards(cardList);

        // 1. find all available cards to flip at move=1
        final ArrayList<Integer> availableCards = cardSet.availableForFlipping();

        // 2. recursively go through available cards at each move
        recursiveFlip(cardSet, availableCards, new Sequence());

        // 3. print minimum and maximum number of moves
        final Sequence shortest = sequenceRepository.shortestSequence();
        final Sequence longest = sequenceRepository.longestSequence();
        System.out.println("Minimum number of moves = " + shortest.size());
        System.out.println(shortest);
        System.out.println("Maximum number of moves = " + longest.size());
        System.out.println(longest);
        return sequenceRepository;
    }

    private static void recursiveFlip(final CardSet cardSet, final ArrayList<Integer> availableCards, final Sequence sequence) {
        for (int cardIndex : availableCards) {
            cardSet.flipCard(cardIndex);
            sequence.addMove(cardIndex);
            final ArrayList<Integer> availableNextMove = cardSet.availableForFlipping();
            if (availableNextMove.size() == 0) {
                // no more moves (all cards are flipped)
                sequenceRepository.updateLongestSequence(sequence.createCopy());
                sequenceRepository.updateShortestSequence(sequence.createCopy());
                // revert most recent flip
                cardSet.revertFlip(cardIndex);
                sequence.dropLastMove();
                continue;
            } else {
                // more moves available - go to next move
                recursiveFlip(cardSet, availableNextMove, sequence);
            }
        }
        // finished all available card flips at this level, revert to previous sequence
        if (sequence.size() > 0) {
            cardSet.revertFlip(sequence.getLastMove());
            sequence.dropLastMove();
        }
    }

}
