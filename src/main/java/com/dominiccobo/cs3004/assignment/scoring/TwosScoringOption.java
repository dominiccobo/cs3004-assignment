package com.dominiccobo.cs3004.assignment.scoring;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class TwosScoringOption extends UpperSectionScoringOption {

    public static final String SCORING_OPTION_NAME = "Twos";

    public static final int DICE_VALUE = 2;

    public TwosScoringOption() {
        super(DICE_VALUE);
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}
