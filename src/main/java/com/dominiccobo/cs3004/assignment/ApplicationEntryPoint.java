package com.dominiccobo.cs3004.assignment;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ApplicationEntryPoint {
    public static void main(String[] args) {
        final InputOutputStreams inputOutputStreams = new InputOutputStreams(System.in, System.out);
        final Yahtzee yahtzee = new Yahtzee(inputOutputStreams);
        yahtzee.playGame();
    }
}
