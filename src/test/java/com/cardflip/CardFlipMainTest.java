package com.cardflip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;


class CardFlipMainTest {

    private static Stream<Arguments> test_card_flipping() {
        return Stream.of(
                Arguments.of("D, D, D, D, D", 3, 15),
                Arguments.of("U, U, U, U", 0, 0),
                Arguments.of("D, D, D, U, D, U", 3, 17),
                Arguments.of("U, U, D, D, D, U", 3, 9),
                Arguments.of("D, D, U, U, D, D, D", 3, 19),
                Arguments.of("D, U, D, D, D, U, D, U", 5, 25)
        );
    }

    @ParameterizedTest
    @MethodSource
    void test_card_flipping(final String cards, final long minMoves, final long maxMoves) {
        final SequenceRepository moveSequences = CardFlipMain.runCardFlipGame(cards);
        Assertions.assertEquals(minMoves, moveSequences.shortestSequence().size());
        Assertions.assertEquals(maxMoves, moveSequences.longestSequence().size());
    }
}