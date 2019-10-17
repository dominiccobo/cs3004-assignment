package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

import static com.dominiccobo.cs3004.assignment.Dice.countDifferentDices;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Yahtzee";

    public YahtzeeScoringOption() {
        this.score = 50;
    }

    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            //CHECK FOR YAHTZEE
            int countedDifferences = countDifferentDices(theDice);
            if (countedDifferences == 0) {
                canBeAwarded = true;
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
