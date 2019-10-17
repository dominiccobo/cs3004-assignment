package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class TripleScoringOption extends ScoringOption {

    public static final String SCORING_OPTION_NAME = "Triple";

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasScoredAlready()) {
            //CHECK FOR 3 OF A KIND
            int count = 0;
            boolean found3K = false;
            //Check first set are the same
            for (int i = 0; i < 3; i++) {
                if (theDice[0] != theDice[i]) {
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
                //	        	System.out.println("3K true here first");
            }

            count = 0;
            //Check last set are the same
            for (int i = 1; i < 4; i++) {
                if (theDice[1] != theDice[i]) {
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
                //	        	System.out.println("3K true here second");

            }

            count = 0;
            //Check last set are the same
            for (int i = 2; i < 5; i++) {
                if (theDice[1] != theDice[i]) {
                    count++;
                }
            }
            if (count == 0) {
                found3K = true;
                //	        	System.out.println("4K true here third");

            }

            if (found3K) {
                //	        	System.out.println("It's 3K");
                int score = 0;
                for (int i = 0; i < theDice.length; i++) {
                    score = score + theDice[i];
                }
                this.canBeAwarded = true;
                this.score = score;
            }

        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
