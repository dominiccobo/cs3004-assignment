package com.dominiccobo.cs3004.assignment.scoring;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FoursScoringOption extends UpperSectionScoringOption {

    public static final String SCORING_OPTION_NAME = "Fours";

    public static final int DICE_VALUE = 4;

    public FoursScoringOption() {
        super(DICE_VALUE);
    }

    @Override
    public String getScoringOptionName() {
        return SCORING_OPTION_NAME;
    }
}