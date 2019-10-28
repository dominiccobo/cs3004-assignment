package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeTest {

    private Yahtzee fixture;
    private InputOutputStreams inputOutputStreams;
    private InputStream inputStream;
    private PrintStream printStream;

    @Before
    public void setUp() throws Exception {
        inputOutputStreams = new InputOutputStreams(inputStream, printStream);
        fixture = new Yahtzee(inputOutputStreams);
    }

    @Test
    public void playGameThroughToCompletion() {
        fixture.playGame();
    }
}
