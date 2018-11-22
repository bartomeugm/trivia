package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class PenaltyBoxTest {

    private final int MAX_NUMBER_OF_PLAYERS = 6;
    private Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox;

    @BeforeEach
    void setUp() {
        ruleForGoingOutOfThePenaltyBox = roll -> true;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void player_should_not_be_in_penalty_box(int currentPlayer) {
        PenaltyBox penaltyBox = new PenaltyBox(MAX_NUMBER_OF_PLAYERS, ruleForGoingOutOfThePenaltyBox);

        assertFalse(penaltyBox.isInPenaltyBox(currentPlayer));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void player_should_be_sent_to_penalty_box(int currentPlayer) {
        PenaltyBox penaltyBox = new PenaltyBox(MAX_NUMBER_OF_PLAYERS, ruleForGoingOutOfThePenaltyBox);

        penaltyBox.sendToPenaltyBox(currentPlayer);

        assertTrue(penaltyBox.isInPenaltyBox(currentPlayer));
    }

    @ParameterizedTest
    @CsvSource({"1, true", "2, false",
            "3,true", "4, false",
            "5, true", "6, false"})
    void gets_out_of_penalty_box_if_odd_number_is_rolled(int roll, boolean expected) {
        ruleForGoingOutOfThePenaltyBox = this::isOdd;
        PenaltyBox penaltyBox = new PenaltyBox(MAX_NUMBER_OF_PLAYERS, ruleForGoingOutOfThePenaltyBox);

        boolean actual = penaltyBox.isGettingOutOfPenaltyBox(roll);

        assertEquals(expected, actual);
    }

    private boolean isOdd(Integer diceRoll) {
        return diceRoll % 2 != 0;
    }
}
