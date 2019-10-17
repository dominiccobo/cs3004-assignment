package com.dominiccobo.cs3004.assignment;

import org.junit.Before;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeTest {

    private Yahtzee fixture;
    private InputOutputStreams inputOutputStreams;

    @Before
    public void setUp() throws Exception {
        inputOutputStreams = new InputOutputStreams();
        fixture = new Yahtzee(inputOutputStreams);
    }

}
