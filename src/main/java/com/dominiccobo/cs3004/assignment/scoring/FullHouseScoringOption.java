package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

import java.util.*;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FullHouseScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Full House";

    public FullHouseScoringOption() {
        this.score = 25;
    }

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            if (hasTwoOfOneAndThreeOfAnother(theDice)) {
                this.canBeAwarded = true;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }

    private Map<Integer, Integer> countInstancesOfNumberInRoll(int[] rolledDice) {
        Map<Integer, Integer> instancesOfValue = new HashMap<>();
        for (int number : rolledDice) {
            Integer val = instancesOfValue.getOrDefault(number, 0);
            Integer newValue = ++val;
            instancesOfValue.put(number, newValue);
        }
        return instancesOfValue;
    }

    private boolean hasTwoOfOneAndThreeOfAnother(int[] theDice) {
        final Map<Integer, Integer> instancesOfValue = countInstancesOfNumberInRoll(theDice);
        final Set<Integer> numberOfInstances = new HashSet<>();

        for (Integer integer : instancesOfValue.keySet()) {
            numberOfInstances.add(instancesOfValue.get(integer));
        }
        final List<Integer> mustContainTheseValues = Arrays.asList(3, 2);

        return (numberOfInstances.containsAll(mustContainTheseValues));
    }
}
