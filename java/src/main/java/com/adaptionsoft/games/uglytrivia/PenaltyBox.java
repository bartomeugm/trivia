package com.adaptionsoft.games.uglytrivia;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PenaltyBox {
    private Map<Integer, Boolean> playersInPenaltyBox = new HashMap<>();

    private Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox;

    public PenaltyBox(Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox) {
        this.ruleForGoingOutOfThePenaltyBox = ruleForGoingOutOfThePenaltyBox;
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return playersInPenaltyBox.containsKey(currentPlayer)
                && playersInPenaltyBox.get(currentPlayer);
    }

    boolean isGettingOutOfPenaltyBox(int roll) {
        return ruleForGoingOutOfThePenaltyBox.apply(roll);
    }

    void sendToPenaltyBox(int currentPlayer) {
        playersInPenaltyBox.put(currentPlayer, true);
    }
}