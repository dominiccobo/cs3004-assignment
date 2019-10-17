package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.scoring.*;

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
        for (ScoringOption scoringOption : scoringOptions) {
            String output = scoringOption.getScoringOptionName() + " scoring " + scoringOption.score + "points";
            strBuilder.append(output);
        }
        return strBuilder.toString();
    }

    public int calculateCurrentScore() {
        int score = 0;
        for (ScoringOption scoringOption : scoringOptions) {
            if(scoringOption.hasScored) {
                score += scoringOption.score;
            }
        }
        return score;
    }
}
