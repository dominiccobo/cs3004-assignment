package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class TripleScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Triple";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            if (hasThreeInstancesOfValue(theDice)) {
                this.canBeAwarded = true;
                this.score = Arrays.stream(theDice).sum();
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }

    private boolean hasThreeInstancesOfValue(int[] theDice) {
        final List<Object> mustContain = Arrays.asList(new Integer[]{3});
        return Dice.hasXInstancesOf(theDice, mustContain);
    }
}
