package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

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
            this.canBeAwarded = true;
            score = Dice.countNumberOfKind(upperSectionNumber, theDice) * upperSectionNumber;
        }
    }
}
