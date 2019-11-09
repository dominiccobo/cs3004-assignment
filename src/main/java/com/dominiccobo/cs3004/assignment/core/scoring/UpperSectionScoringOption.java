package com.dominiccobo.cs3004.assignment.core.scoring;

import com.dominiccobo.cs3004.assignment.core.Dice;

/**
 * Provides reusable scoring evaluation logic for upper section scores.
 * Upper sections are considered those that require n instances of a
 * particular number to obtain the score. The score is always n x n.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public abstract class UpperSectionScoringOption extends ScoringOption {

    protected int upperSectionNumber;

    public UpperSectionScoringOption(int upperSectionNumber) {
        this.upperSectionNumber = upperSectionNumber;
    }

    @Override
    public void checkAgainstDice(int[] theDice) {
        if (!this.hasOptionBeenScored()) {
            int numberOfKind = Dice.countNumberOfKind(upperSectionNumber, theDice);
            if(numberOfKind > 0) {
                this.markAsAwardable();
                score = numberOfKind * upperSectionNumber;
            }
        }
    }
}
