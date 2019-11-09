package com.dominiccobo.cs3004.assignment.core;

import java.util.*;

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
    static String printDice(int[] theseDice) {
        StringBuilder strBuilder = new StringBuilder("You rolled: ");
        for(int dice: theseDice) {
            strBuilder.append(dice).append("\t");
        }
        return strBuilder.toString();
    }

    public static int countDifferentDices(int[] theDice) {
        int count = 0;
        for (int value : theDice) {
            if (theDice[0] != value) {
                count++;
            }
        }
        return count;
    }

    public static int countNumberOfKind(int value, int[] rolledDice) {
        return Arrays.stream(rolledDice).filter(iterVal -> iterVal == value).toArray().length;
    }

    public static boolean isIncrementalSequenceInOnes(int[] theDice, int startAtIdx, int endAtIdx) {
        int[] sortedDice = Arrays.copyOf(theDice, theDice.length);
        Arrays.sort(sortedDice);
        int previous = sortedDice[startAtIdx];
        for (int i = startAtIdx; i <= endAtIdx; i++) {
            int difference = sortedDice[i] - previous;
            if (difference > 1) {
                return false;
            }
            previous = sortedDice[i];
        }
        return true;
    }

    private static Map<Integer, Integer> countInstancesOfNumberInRoll(int[] rolledDice) {
        Map<Integer, Integer> instancesOfValue = new HashMap<>();
        for (int number : rolledDice) {
            Integer val = instancesOfValue.getOrDefault(number, 0);
            Integer newValue = ++val;
            instancesOfValue.put(number, newValue);
        }
        return instancesOfValue;
    }

    public static boolean hasXInstancesOf(int[] theDice, List mustContain) {
        final Map<Integer, Integer> instancesOfValue = countInstancesOfNumberInRoll(theDice);
        final Set<Integer> numberOfInstances = new HashSet<>();

        for (Integer integer : instancesOfValue.keySet()) {
            numberOfInstances.add(instancesOfValue.get(integer));
        }

        return (numberOfInstances.containsAll(mustContain));
    }
}
