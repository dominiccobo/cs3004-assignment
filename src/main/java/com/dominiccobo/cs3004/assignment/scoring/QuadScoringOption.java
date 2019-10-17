package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class QuadScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Quad";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {

            int score = 0;
            //CHECK FOR 4 OF A KIND
            int count = 0;
            boolean found4ofAKind = false;
            //Check first set are the same
            for (int i = 0; i < 4; i++) {
                if (theDice[0] != theDice[i]) {
                    count++;
                }
            }
            if (count == 0) {
                found4ofAKind = true;
                //	        	System.out.println("4K true here first");
            }
            count = 0;
            //Check last set are the same
            for (int i = 1; i < 5; i++) {
                if (theDice[1] != theDice[i]) {
                    count++;
                }
            }
            if (count == 0) {
                found4ofAKind = true;
            }
            if (found4ofAKind) {
                for (int i = 0; i < theDice.length; i++) {
                    score = score + theDice[i];
                }
                this.canBeAwarded = true;
                this.score = score;
            }

        }//4K
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
