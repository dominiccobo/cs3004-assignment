package com.dominiccobo.cs3004.assignment.core;

import com.dominiccobo.cs3004.assignment.api.PlayerGameFinishedEvent;
import com.dominiccobo.cs3004.assignment.api.PlayerReadyEvent;
import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import com.dominiccobo.cs3004.assignment.core.scoring.ScoreBoard;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class Yahtzee implements SessionInstance, Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Yahtzee.class);

    public static final int NUMBER_OF_DICE = 5;
    public static final int NUMBER_OF_ROUNDS = 13;

    private InputOutputStreams ioStreams;
    private final SessionIdentifier identifier;
    private final TurnMediator turnMediator;
    private final EventBus eventBus;
    private String playerName;
    private ScoreBoard scoreBoard = new ScoreBoard();
    private int currentRoundNo = 0;

    public Yahtzee(InputOutputStreams ioStreams, TurnMediator turnMediator, EventBus eventBus) {
        this.ioStreams = ioStreams;
        this.turnMediator = turnMediator;
        this.eventBus = eventBus;
        this.identifier = SessionIdentifier.newInstance();
        this.turnMediator.registerSession(this);
    }

    @SuppressWarnings("WeakerAccess")
    public void playGame() {
        requestPlayerNameDetails();
        final String banner = readBanner();
        ioStreams.println(banner);
        ioStreams.println("Welcome " + playerName);

        while(this.hasNext()) {
            if(turnMediator.hasTurn(this)) {
                turnMediator.lockTurn(this);
                Round roundToPlay = this.next();
                scoreBoard = roundToPlay.play();
                turnMediator.releaseTurn(this);
            }
        }
        this.eventBus.post(new PlayerGameFinishedEvent(playerName, scoreBoard));
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

    private boolean hasNext() {
        return (currentRoundNo + 1) <= NUMBER_OF_ROUNDS;
    }

    // TODO: should return a transaction object that can be committed upon completion.
    private Round next() {
        currentRoundNo++;
        return new Round(ioStreams, currentRoundNo, scoreBoard);
    }

    @Override
    public SessionIdentifier getIdentifier() {
        return this.identifier;
    }

    @Override
    public void run() {
        this.playGame();
    }

    void requestPlayerNameDetails() {
        this.playerName = ioStreams.readConsoleInput("Choose a name: ");
        eventBus.post(new PlayerReadyEvent(playerName, getIdentifier()));
    }
}

