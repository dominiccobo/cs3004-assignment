package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class LongStraightScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Long Straight";

    public LongStraightScoringOption() {
        this.score = 40;
    }

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            //CHECK LONG STRAIGHT
            //Check for Straight D1-5
            if ((theDice[0] == theDice[1] - 1) && (theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1) && (theDice[3] == theDice[4] - 1)) {
                this.canBeAwarded = true;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
