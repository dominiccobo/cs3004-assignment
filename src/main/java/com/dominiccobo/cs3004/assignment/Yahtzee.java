package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 *
 *   Welcome to Yahtzee -
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
@SuppressWarnings("WeakerAccess")
public class Yahtzee implements Iterator<Round> {

    private static final Logger LOG = LoggerFactory.getLogger(Yahtzee.class);

    public static final int NUMBER_OF_DICE = 5;
    public static final int NUMBER_OF_ROUNDS = 13;

    private InputOutputStreams ioStreams;
    private int currentScore;
    private ScoreBoard scoreBoard = new ScoreBoard();
    private int currentRoundNo = 0;

    public Yahtzee(InputOutputStreams ioStreams) {
        this.ioStreams = ioStreams;
        currentScore = 0;
    }

    @SuppressWarnings("WeakerAccess")
    public void playGame(String playerName) {
        final String banner = readBanner();
        ioStreams.println(banner);
    }

    public void printScore() {
        currentScore = scoreBoard.calculateCurrentScore();
        ioStreams.println("Your final score is " + currentScore);
        ioStreams.println(scoreBoard.buildScoreBoardString());
    }

    private String readBanner() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("banner.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        final String banner = bufferedReader.lines().collect(Collectors.joining("\n"));
        try {
            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            LOG.warn("Could not close streams:", e);
        }
        return banner;
    }

    @Override
    public boolean hasNext() {
        return (currentRoundNo + 1) <= NUMBER_OF_ROUNDS;
    }

    // TODO: should return a transaction object that can be committed upon completion.
    @Override
    public Round next() {
        currentRoundNo++;
        return new Round(ioStreams, currentRoundNo, scoreBoard);
    }
}

