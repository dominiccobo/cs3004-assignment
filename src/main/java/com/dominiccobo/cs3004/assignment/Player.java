package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Each player has an associated connection of players.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Player extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    private String alias;
    private Yahtzee gameInstance;
    private Connection connectedSocket;
    private final TurnMediator turnMediator;
    private InputOutputStreams inputOutputStreams;

    public Player(Connection playerConnection, TurnMediator turnMediator) throws IOException {
        this.connectedSocket = playerConnection;
        this.turnMediator = turnMediator;
        this.inputOutputStreams = new InputOutputStreams(
                connectedSocket.getInputStream(),
                new PrintStream(connectedSocket.getOutputStream())
        );
        this.gameInstance = new Yahtzee(inputOutputStreams);
    }

    public final String getAlias() {
        return alias;
    }

    @Override
    public void run() {
        getPlayerNameDetails();

        LOG.info("{} is ready.", alias);
        this.gameInstance.playGame();

        while(this.gameInstance.hasNext()) {
            if(turnMediator.hasTurn(this)) {
                turnMediator.lockTurn(this);
                Round roundToPlay = this.gameInstance.next();
                roundToPlay.play();
                turnMediator.releaseTurn(this);
            }
        }
        this.gameInstance.printScore();
    }

    private void getPlayerNameDetails() {
        this.alias = this.inputOutputStreams.readConsoleInput("Choose a name: ");
        this.setName("PlayerThread-" + alias);
    }
}
