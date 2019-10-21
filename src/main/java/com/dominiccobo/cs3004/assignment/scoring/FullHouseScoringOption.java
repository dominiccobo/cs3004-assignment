package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

import java.util.*;

/**
 * TODO: add class description.
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
        if (!this.hasScoredAlready()) {
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
