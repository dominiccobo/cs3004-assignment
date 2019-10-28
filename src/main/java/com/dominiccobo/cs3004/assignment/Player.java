package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Each player has an associated connection of players.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Player extends Thread {

    private String name;
    private Yahtzee gameInstance;
    private Connection connectedSocket;
    private InputOutputStreams inputOutputStreams;

    public Player(Connection playerConnection) throws IOException {
        this.connectedSocket = playerConnection;
        this.inputOutputStreams = new InputOutputStreams(
                connectedSocket.getInputStream(),
                new PrintStream(connectedSocket.getOutputStream())
        );
        this.gameInstance = new Yahtzee(inputOutputStreams);
    }

    @Override
    public void run() {
        // TODO: get player name....
        this.gameInstance.playGame();
    }
}
