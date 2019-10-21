package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
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
            if(Dice.isIncrementalSequenceInOnes(theDice, 0, 5)) {
                this.canBeAwarded = true;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
