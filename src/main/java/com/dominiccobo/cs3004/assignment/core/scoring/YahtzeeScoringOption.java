package com.dominiccobo.cs3004.assignment.core.scoring;

import static com.dominiccobo.cs3004.assignment.core.Dice.countDifferentDices;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Yahtzee score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class YahtzeeScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Yahtzee";

    public YahtzeeScoringOption() {
        this.score = 50;
    }

    public void checkAgainstDice(int[] theDice) {
        if (!this.hasOptionBeenScored()) {
            //CHECK FOR YAHTZEE
            int countedDifferences = countDifferentDices(theDice);
            if (countedDifferences == 0) {
                this.markAsAwardable();
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
