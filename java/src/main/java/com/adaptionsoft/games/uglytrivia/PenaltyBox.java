package com.adaptionsoft.games.uglytrivia;

public class PenaltyBox {
    private final boolean[] inPenaltyBox;

    public PenaltyBox(boolean[] inPenaltyBox) {
        this.inPenaltyBox = inPenaltyBox;
    }

    boolean isInPenaltyBox(int currentPlayer) {
        return inPenaltyBox[currentPlayer];
    }

    boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 == 0;
    }

    void sendToPenaltyBox(int currentPlayer) {
        inPenaltyBox[currentPlayer] = true;
    }
}