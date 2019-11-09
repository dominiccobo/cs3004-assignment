package com.dominiccobo.cs3004.assignment.core.scoring;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scoreboard.
 *
 * @author Simon Taylor 2019
 * @author Dominic Cobo (contact@dominiccobo.com) - modiified
 */
public class ScoreBoard {

    private ArrayList<ScoringOption> scoringOptions = new ArrayList<>();

    public ScoreBoard() {
        // lower house
        scoringOptions.add(new YahtzeeScoringOption());
        scoringOptions.add(new QuadScoringOption());
        scoringOptions.add(new ShortStraightScoringOption());
        scoringOptions.add(new TripleScoringOption());
        scoringOptions.add(new LongStraightScoringOption());
        scoringOptions.add(new FullHouseScoringOption());
        scoringOptions.add(new ChanceScoringOption());
        // upper house
        scoringOptions.add(new OnesScoringOption());
        scoringOptions.add(new TwosScoringOption());
        scoringOptions.add(new ThreesScoringOption());
        scoringOptions.add(new FoursScoringOption());
        scoringOptions.add(new FivesScoringOption());
        scoringOptions.add(new SixesScoringOption());
    }

    public void updateScoringOptionsForDiceRoll(int[] theDice) {
        resetAwarded();
        for (ScoringOption scoringOption : scoringOptions) {
            scoringOption.checkAgainstDice(theDice);
        }
    }

    public String buildScoreBoardString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("\tCurrent Score\n");
        strBuilder.append("Option\t\t\t\tScore\n");



        for (ScoringOption scoringOption : scoringOptions) {
            String padding = scoringOption.getScoringOptionName().length() > 12 ? ("\t\t\t") : ("\t\t\t\t\t");
            String output = scoringOption.getScoringOptionName() + padding + scoringOption.getScore() + "\n";
            strBuilder.append(output);
        }
        return strBuilder.toString();
    }

    private void resetAwarded() {
        for (ScoringOption scoringOption : scoringOptions) {
            scoringOption.resetAwardable();
        }
    }

    private void markOptionAsScored(ScoringOption scored) {
        for (ScoringOption scoringOption : scoringOptions) {
            if(scoringOption.getScoringOptionName().equals(scored.getScoringOptionName())) {
                scoringOption.markAsScored();
            }
        }
    }

    public int calculateCurrentScore() {
        int score = 0;
        for (ScoringOption scoringOption : scoringOptions) {
            if(scoringOption.hasOptionBeenScored()) {
                score += scoringOption.getScore();
            }
        }
        return score;
    }

    public ScoringOptionSelection getScoringOptionSelection() {
        return new ScoringOptionSelection(this.scoringOptions);
    }

    public void submitScoringSelection(ScoringOptionSelection selection) {
        markOptionAsScored(selection.getChosen());
        resetAwarded();
    }

    public static class ScoringOptionSelection {

        private final List<ScoringOption> scoringOptions;
        private ScoringOption chosenOption;

        ScoringOptionSelection(List<ScoringOption> scoringOptions) {
            this.scoringOptions = scoringOptions;
        }

        public String generateScoreChoicePrompt() {
            StringBuilder promptBuilder = new StringBuilder();
            promptBuilder.append("With your roll you can select: \n");
            for (int i = 0; i < scoringOptions.size(); i++) {
                ScoringOption scoringOption = scoringOptions.get(i);
                if (!scoringOption.hasOptionBeenScored() && scoringOption.canScoreBeAwarded()) {
                    String promptToAppend = String.format("%d for %s scoring %d points%n", i, scoringOption.getScoringOptionName(), scoringOption.getScore());
                    promptBuilder.append(promptToAppend);
                }
            }
            return promptBuilder.toString();
        }

        private boolean isValidScoringOption(int chosenOption) {
            if(!isWithinOptionBounds(chosenOption)) return false; // OOB check.
            ScoringOption chosenScore = scoringOptions.get(chosenOption);
            return chosenScore.canScoreBeAwarded() && !chosenScore.hasOptionBeenScored();
        }

        private boolean isWithinOptionBounds(int chosenOption) {
            return chosenOption >= 0 && chosenOption < scoringOptions.size();
        }

        private ScoringOption getChosen() {
            return chosenOption;
        }

        public void select(int scoringOption) throws InvalidScoringOptionException {
            if(!isValidScoringOption(scoringOption)) {
                throw new InvalidScoringOptionException("Score has either been chosen or cannot be awarded!");
            }
            chosenOption = scoringOptions.get(scoringOption);
            chosenOption.markAsScored();
        }
    }

    public static class InvalidScoringOptionException extends Exception {

        public InvalidScoringOptionException(String s) {
            super(s);
        }
    }
}
