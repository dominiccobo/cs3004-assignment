package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ChanceScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Chance";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            this.canBeAwarded = true;
            this.score = 0;

            for (int value : theDice) {
                this.score = this.score + value;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
