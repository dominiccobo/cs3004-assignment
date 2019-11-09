package com.dominiccobo.cs3004.assignment.core.scoring;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Threes score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ThreesScoringOption extends UpperSectionScoringOption {

    public static final String SCORING_OPTION_NAME = "Threes";
    public static final int DICE_VALUE = 3;

    public ThreesScoringOption() {
        super(DICE_VALUE);
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
