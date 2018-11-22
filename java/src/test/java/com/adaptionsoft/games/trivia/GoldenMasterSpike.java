package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GoldenMasterSpike {

    @Test
    public void run() throws IOException {
        Path output = Paths.get("output.log");
        Path goldenMaster = Paths.get("golden-master-for-even-numbers.log");

        PrintStream originalOut = System.out;

        try (PrintStream printStream = new PrintStream(output.toFile())) {

            System.setOut(printStream);

            IntStream.range(0, 1000).forEach(i -> new GameRunner().run(new Random(i)));

            assertThat(Files.readAllLines(output), is(Files.readAllLines(goldenMaster)));
        } finally {
            System.setOut(originalOut);
        }
    }
}
