package com.dominiccobo.cs3004.assignment.scoring;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Sixes score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class SixesScoringOption extends UpperSectionScoringOption {

    public static final String SCORING_OPTION_NAME = "Sixes";
    public static final int DICE_VALUE = 6;

    public SixesScoringOption() {
        super(DICE_VALUE);
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
