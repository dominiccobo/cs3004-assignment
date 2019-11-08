package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.api.PlayerReadyEvent;
import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.InputOutputStreams;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Each player has an associated connection of players.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Player extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(Player.class);

    private String alias;
    private Yahtzee gameInstance;
    private final TurnMediator turnMediator;
    private InputOutputStreams inputOutputStreams;
    private final EventBus eventbus;

    public Player(Connection playerConnection, TurnMediator turnMediator, EventBus eventbus) throws IOException {
        this.turnMediator = turnMediator;
        this.inputOutputStreams = new InputOutputStreams(playerConnection);
        this.eventbus = eventbus;
        this.gameInstance = new Yahtzee(inputOutputStreams);
    }

    public final String getAlias() {
        return alias;
    }

    @Override
    public void run() {
        getPlayerNameDetails();

        LOG.info("{} is ready.", alias);
        this.gameInstance.playGame(alias);

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
        this.eventbus.post(new PlayerReadyEvent(alias));
    }
}
