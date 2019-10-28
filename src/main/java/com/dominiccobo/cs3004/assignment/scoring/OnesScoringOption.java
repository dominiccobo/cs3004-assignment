package com.dominiccobo.cs3004.assignment.scoring;

/**
 * Evaluates whether or not a particular dice roll can
 * result in a Ones score.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class OnesScoringOption extends UpperSectionScoringOption {

    public static final String SCORING_OPTION_NAME = "Ones";
    public static final int DICE_VALUE = 1;

    public OnesScoringOption() {
        super(DICE_VALUE);
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
