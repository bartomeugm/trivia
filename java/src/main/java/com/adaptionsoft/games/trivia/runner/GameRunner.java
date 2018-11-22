
package com.adaptionsoft.games.trivia.runner;

import com.adaptionsoft.games.uglytrivia.Category;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.QuestionDeck;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static java.util.Arrays.asList;


public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        new GameRunner().run(new Random());
    }

    public void run(Random random) {
        List<Category> categories = asList(
                new Category("Pop", asList(0, 4, 8)),
                new Category("Science", asList(1, 5, 9)),
                new Category("Sports", asList(2, 6, 10)),
                new Category("History", asList(3, 7, 11)));

        IntStream.range(0, 50).forEach(i ->
                categories.forEach(category -> category.addQuestion(format("%s Question %d", category.name(), i))));

        Function<Integer, Boolean> ruleForGoingOutOfThePenaltyBox = (roll) -> roll % 2 == 0;
        Game aGame = new Game(new QuestionDeck(categories), ruleForGoingOutOfThePenaltyBox);

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        do {

            aGame.roll(random.nextInt(5) + 1);

            if (random.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }


        } while (notAWinner);
    }

}
