package com.dominiccobo.cs3004.assignment.scoring;

import com.dominiccobo.cs3004.assignment.Dice;
import com.dominiccobo.cs3004.assignment.ScoringOption;

/**
 * TODO: add class description.
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
        if (!this.hasScoredAlready()) {
            this.canBeAwarded = true;
            score = Dice.countNumberOfKind(upperSectionNumber, theDice) * upperSectionNumber;
        }
    }
}
