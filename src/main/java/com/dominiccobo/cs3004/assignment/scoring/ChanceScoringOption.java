package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

import java.util.Arrays;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Chance score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ChanceScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Chance";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasOptionBeenScored()) {
            this.canBeAwarded = true;
            this.score = Arrays.stream(theDice).sum();
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
