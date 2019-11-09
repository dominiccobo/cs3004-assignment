package com.dominiccobo.cs3004.assignment.core.scoring;

import com.dominiccobo.cs3004.assignment.core.Dice;
import com.dominiccobo.cs3004.assignment.core.ScoringOption;

import java.util.*;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Full-House score.
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
        if (!this.hasOptionBeenScored()) {
            if (hasTwoOfOneAndThreeOfAnother(theDice)) {
                this.canBeAwarded = true;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }

    private boolean hasTwoOfOneAndThreeOfAnother(int[] theDice) {
        final List<Object> mustContain = Arrays.asList(new Integer[]{2, 3});
        return Dice.hasXInstancesOf(theDice, mustContain);
    }

}
