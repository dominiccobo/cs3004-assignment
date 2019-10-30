package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Short-Straight score.
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
            if (Dice.isIncrementalSequenceInOnes(theDice, 0, 3)) {
                this.canBeAwarded = true;
                this.score = 30;
            }
            else if(Dice.isIncrementalSequenceInOnes(theDice, 1, 4)) {
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
