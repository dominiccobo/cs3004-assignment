package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ShortStraightScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Short Straight";

    public ShortStraightScoringOption() {
    }

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            //Check for Short Straight D1-4, D2-5
            if ((theDice[0] == theDice[1] - 1) && (theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1)) {
                this.canBeAwarded = true;
                this.score = 30;
            }
            if ((theDice[1] == theDice[2] - 1) && (theDice[2] == theDice[3] - 1) && (theDice[3] == theDice[4] - 1)) {
                this.canBeAwarded = true;
                this.score = 30;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
