package com.dominiccobo.cs3004.assignment.core;

import com.dominiccobo.cs3004.assignment.api.*;
import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Each player has an associated connection of players.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Player extends Thread implements PlayerLifecycleEvents {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    private Yahtzee gameInstance;
    private InputOutputStreams inputOutputStreams;
    private final TurnMediator turnMediator;
    private final EventBus eventbus;

    public Player(Connection playerConnection, final TurnMediator turnMediator, final EventBus eventbus) throws IOException {
        this.inputOutputStreams = new InputOutputStreams(playerConnection);
        this.turnMediator = turnMediator;
        this.eventbus = eventbus;
        this.eventbus.register(this);
    }

    @Override
    public void run() {
        this.gameInstance = new Yahtzee(inputOutputStreams, turnMediator, eventbus);
        Thread gameThread = new Thread(gameInstance);
        gameThread.setName("GameThread");
        gameThread.run();
    }

    @Override
    public void on(PlayerReadyEvent event) {
        inputOutputStreams.println(String.format("[GLOBAL] %s has joined the game.", event.playerName));
    }

    @Override
    public void on(PlayerRoundStartedEvent event) {
        inputOutputStreams.println(String.format("[GLOBAL] %s has started their round.", event.playerName));
    }

    @Override
    public void on(PlayerRoundFinishedEvent event) {
        inputOutputStreams.println(String.format("[GLOBAL] %s has finished their round. %n%s", event.playerName, event.playerScore.buildScoreBoardString()));
    }

    @Override
    public void on(PlayerGameFinishedEvent event) {
        inputOutputStreams.println(String.format("[GLOBAL] %s has finished playing their game.", event.playerName));
    }

    @Subscribe
    public void on(GameFinishedEvent event) {
        event.playerScores.forEach((playerName, scoreBoard) -> {
            String format = String.format("Player %s [Score: %d]: %n%s%n", playerName, scoreBoard.calculateCurrentScore(), scoreBoard.buildScoreBoardString());
            inputOutputStreams.println(format);
        });
        inputOutputStreams.println(ConnectionProtocol.TERMINATE_CONNECTION_REQUEST);
    }
}
