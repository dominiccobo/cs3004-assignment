package com.dominiccobo.cs3004.assignment.core.scoring;

/**
 * Represents a scoring option, which is essentially a row
 * in the scoring table in a Yahtzee Game.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public abstract class ScoringOption {
    private boolean hasScored;
    private boolean canBeAwarded;
    protected int score;

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasOptionBeenScored() {
        return hasScored;
    }

    public void markAsScored() {
        hasScored = true;
    }

    public void markAsAwardable() {
        canBeAwarded = true;
    }

    public void resetAwardable() {
        canBeAwarded = false;
    }

    public abstract void checkAgainstDice(int[] theDice);

    public abstract String getScoringOptionName();

    public boolean canScoreBeAwarded() {
        return canBeAwarded;
    }

    public int getScore() {
        return score;
    }
}
