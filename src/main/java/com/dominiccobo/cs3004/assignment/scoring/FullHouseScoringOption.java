package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.ScoringOption;

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
            //CHECK FULL HOUSE
            int count = 0;
            //Check it's not a Y
            for (int i = 0; i < 4; i++) {
                if (theDice[0] != theDice[i]) {
                    count++;
                }
            }
            if (count > 0) {
                //Two conditions 2 then 3 or 3 then 2

                count = 0;

                //Check first two the same then check last three
                for (int i = 0; i < 2; i++) {
                    if (theDice[0] != theDice[i]) {
                        count++;
                    }
                }

                //Check last three the same
                for (int i = 2; i < 5; i++) {
                    if (theDice[2] != theDice[i]) {
                        count++;
                    }
                }

                if (count == 0) {
                    this.canBeAwarded = true;
                } else {
                    count = 0;
                    //Check first three the same then check last two
                    for (int i = 0; i < 3; i++) {
                        if (theDice[0] != theDice[i]) {
                            count++;
                        }
                    }

                    //Check last three the same
                    for (int i = 3; i < 5; i++) {
                        if (theDice[3] != theDice[i]) {
                            count++;
                        }
                    }

                    if (count == 0) {
                        canBeAwarded = true;
                    }

                }
            }
        }
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
