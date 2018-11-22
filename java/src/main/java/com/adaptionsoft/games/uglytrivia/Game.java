package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.function.Function;

public class Game {
    private static final int MAX_NUMBER_OF_PLAYERS = 6;
    private static final int MAX_PURSE_NUMBER = 6;
    private final PenaltyBox penaltyBox;
    ArrayList players = new ArrayList();
    int[] places = new int[MAX_NUMBER_OF_PLAYERS];
    int[] purses = new int[MAX_NUMBER_OF_PLAYERS];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    private final QuestionDeck questionDeck;

    public Game(QuestionDeck aQuestionDeck, Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox) {
        questionDeck = aQuestionDeck;
        penaltyBox = new PenaltyBox(ruleForGoingOutOfThePenaltyBox);
    }

    public boolean add(String playerName) {


        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (penaltyBox.isInPenaltyBox(currentPlayer) && !penaltyBox.isGettingOutOfPenaltyBox(roll)) {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
            return;
        }
        if (penaltyBox.isInPenaltyBox(currentPlayer)) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        }
        movePlayer(roll);
        askQuestion();
    }

    private void movePlayer(int positions) {
        places[currentPlayer] = places[currentPlayer] + positions;
        if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
    }

    private void askQuestion() {
        System.out.println(questionDeck.askQuestionFor(currentCategory()));
    }


    private String currentCategory() {
        return questionDeck.currentCategoryForPosition(places[currentPlayer]);
    }

    public boolean wasCorrectlyAnswered() {
        if (penaltyBox.isInPenaltyBox(currentPlayer)) {
            if (isGettingOutOfPenaltyBox) {
                increasePurses("Answer was correct!!!!");

                boolean winner = didPlayerWin();
                setTurnToNextPlayer();
                return winner;
            } else {
                setTurnToNextPlayer();
                return true;
            }
        } else {
            increasePurses("Answer was corrent!!!!");
            boolean winner = didPlayerWin();
            setTurnToNextPlayer();
            return winner;
        }
    }

    private void setTurnToNextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }

    private void increasePurses(String message) {
        System.out.println(message);
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        penaltyBox.sendToPenaltyBox(currentPlayer);

        setTurnToNextPlayer();
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == MAX_PURSE_NUMBER);
    }
}
