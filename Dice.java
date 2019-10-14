package com.dominiccobo.cs3004.assignment;

import java.util.Random;

/**
 * Generic dice rolling code ..
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 * @author Simon Taylor (some networking lecturer at brunel)
 */
public class Dice {

    /**
     * Rolls a single dice
     * @return the result the roll
     */
    static int roll() {
        Random r = new Random();
        return r.nextInt(6)+1;
    }

    /**
     * Rolls several dice
     * @param times the number of dice to roll...
     * @return an array containing the result of the roll.
     */
    static int[] roll(int times) {
        int[] rollingResults = new int[times];

        for (int i = 0; i < rollingResults.length; i++) {
            rollingResults[i] = roll();
        }
        return rollingResults;
    }

    /**
     * Prints an array as a result of dice rolls to the Std out stream.
     * @param theseDice the resulting array roll dice.
     */
    static void printDice(int[] theseDice) {
        StringBuilder strBuilder = new StringBuilder("You rolled: ");
        for(int dice: theseDice) {
            strBuilder.append(dice).append("\t");
        }
        System.out.println(strBuilder.toString());
    }
}
