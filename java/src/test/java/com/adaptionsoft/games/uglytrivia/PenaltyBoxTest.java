package com.adaptionsoft.games.uglytrivia;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;

public class PenaltyBoxTest {
    @Test
    @Disabled
    void player_should_not_be_in_penalty_box() {
        QuestionDeck questionDeck = new QuestionDeck(Collections.emptyList());
        Game game = new Game(questionDeck);
        //PenaltyBox penaltyBox = new
      //  assertFalse(penaltyBox.isInPenaltyBox(0, game));
    }
}
