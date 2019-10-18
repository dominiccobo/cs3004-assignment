package com.dominiccobo.cs3004.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 *   Welcome to Yahtzee -
 *   Scoring - Y FH LS SS 4K 3K On Tw Th Fo Fi Si C
 *
 *     currentScoreRecord - For each of the above {status, score}
 *     canScoreThisRound - For each of the above  {can it be scored? i.e. can be scored and not previously scored, score}
 *     theDice - {what's been rolled this turn}
 *     currentScore - current score
 *
 *     showCurrentScore - calculate and show the current score from currentScoreRecord
 *     whatCanBeScored - update canScoreThisRound from theDice and currentScoreRecord
 *     chooseWhatToScore - user chooses from canScoreThisRound and update currentScoreRecord
 *
 *     Game - 	13 rounds
 *             Show what's been scored
 *             Roll/wantsToReRoll dice max 3 times
 *             Check what can be scored
 *             Choose what can be scored
 *             End of 13 rounds show final score and status
 *
 * @author  Simon Taylor Sept 2019
 * @author Dominic Cobo (contact@dominiccobo.com) - heavily refactored...
 *
 */
public class Yahtzee {

    public static final int NUMBER_OF_DICE = 5;
    public static final int NUMBER_OF_ROUNDS = 13;

    private InputOutputStreams ioStreams;
    private ArrayList<Round> rounds;

    public Yahtzee(InputOutputStreams ioStreams) {
        this.ioStreams = ioStreams;
        rounds = new ArrayList<>(NUMBER_OF_ROUNDS);
    }

    @SuppressWarnings("WeakerAccess")
    public void playGame() {
        final ScoreBoard scoreBoard = new ScoreBoard();
        int currentScore = 0;

        final String banner = readBanner();
        ioStreams.println(banner);

        for (int i = 1; i <= NUMBER_OF_ROUNDS; i++) {
            Round round = new Round(ioStreams, i, scoreBoard);
            rounds.add(round);
            round.play();
        }

        currentScore = scoreBoard.calculateCurrentScore();
        ioStreams.println("Your final score is " + currentScore);
        ioStreams.println("You scored:");
        final String s = scoreBoard.buildScoreBoardString();
        ioStreams.println(s);
    }

    private String readBanner() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("banner.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final String banner = bufferedReader.lines().collect(Collectors.joining("\n"));
        try {
            bufferedReader.close();
            inputStream.close();
        // FIXME: introduce logging...
        } catch (IOException ignored) { }
        return banner;
    }
}

