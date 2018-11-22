package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PenaltyBoxTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void player_should_not_be_in_penalty_box(int currentPlayer) {
        PenaltyBox penaltyBox = new PenaltyBox(new boolean[6]);

        assertFalse(penaltyBox.isInPenaltyBox(currentPlayer));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void player_should_be_sent_to_penalty_box(int currentPlayer) {
        PenaltyBox penaltyBox = new PenaltyBox(new boolean[6]);

        penaltyBox.sendToPenaltyBox(currentPlayer);

        assertTrue(penaltyBox.isInPenaltyBox(currentPlayer));
    }

    @ParameterizedTest
    @CsvSource({"1, true", "2, false",
            "3,true", "4, false",
            "5, true", "6, false"})
    void gets_out_of_penalty_box_if_odd_number_is_rolled(int roll, boolean expected) {
        PenaltyBox penaltyBox = new PenaltyBox(new boolean[6]);
        boolean actual = penaltyBox.isGettingOutOfPenaltyBox(roll);
        assertEquals(expected, actual);
    }
}
