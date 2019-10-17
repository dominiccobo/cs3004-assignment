package com.dominiccobo.cs3004.assignment;

/**
 * Represents a scoring option, which is essentially a row
 * in the scoring table in a Yahtzee Game.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public abstract class ScoringOption {
    protected boolean hasScored;
    protected boolean canBeAwarded;
    protected int score;

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    protected boolean hasScoredAlready() {
        return hasScored;
    }

    public abstract void checkAgainstDice(int[] theDice);

    public abstract String getScoringOptionName();

    public boolean isHasScored() {
        return hasScored;
    }

    public boolean canScoreBeAwarded() {
        return canBeAwarded;
    }

    public int getScore() {
        return score;
    }
}
