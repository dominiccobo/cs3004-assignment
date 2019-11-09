package com.dominiccobo.cs3004.assignment.core;

import com.dominiccobo.cs3004.assignment.core.scoring.*;

import java.util.ArrayList;

/**
 * Represents the scoreboard.
 *
 * @author Simon Taylor 2019
 * @author Dominic Cobo (contact@dominiccobo.com) - modiified
 */
@SuppressWarnings("WeakerAccess")
public class ScoreBoard {

    ArrayList<ScoringOption> scoringOptions = new ArrayList<>();

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

    public void whatCanBeScored(int[] theDice) {
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

    public void resetAwarded() {
        for (ScoringOption scoringOption : scoringOptions) {
            scoringOption.resetAwardable();
        }
    }

    public void markOptionAsScored(ScoringOption scored) {
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
}
