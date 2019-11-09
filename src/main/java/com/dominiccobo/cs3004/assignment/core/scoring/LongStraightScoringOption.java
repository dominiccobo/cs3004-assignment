package com.dominiccobo.cs3004.assignment.core.scoring;

import com.dominiccobo.cs3004.assignment.core.Dice;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Long-straight score.
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
        if (!this.hasOptionBeenScored()) {
            if(Dice.isIncrementalSequenceInOnes(theDice, 0, 4)) {
                this.markAsAwardable();
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
