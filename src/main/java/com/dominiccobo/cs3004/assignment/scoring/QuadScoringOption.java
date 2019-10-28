package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

import java.util.Arrays;
import java.util.List;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Quad score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class QuadScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Quad";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            if(hasFourOfKind(theDice)) {
                this.canBeAwarded = true;
                this.score = Arrays.stream(theDice).sum();
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }

    private boolean hasFourOfKind(int[] theDice) {
        final List<Object> mustContain = Arrays.asList(new Integer[]{4});
        return Dice.hasXInstancesOf(theDice, mustContain);
    }
}
