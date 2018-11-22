package com.adaptionsoft.games.uglytrivia;

import java.util.function.Function;

public class PenaltyBox {
    private final boolean[] inPenaltyBox;
    private Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox;

    public PenaltyBox(boolean[] inPenaltyBox, Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
        this.ruleForGoingOutOfThePenaltyBox = ruleForGoingOutOfThePenaltyBox;
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return inPenaltyBox[currentPlayer];
    }

    boolean isGettingOutOfPenaltyBox(int roll) {
        return ruleForGoingOutOfThePenaltyBox.apply(roll);
    }

    void sendToPenaltyBox(int currentPlayer) {
        inPenaltyBox[currentPlayer] = true;
    }
}