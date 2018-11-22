package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
