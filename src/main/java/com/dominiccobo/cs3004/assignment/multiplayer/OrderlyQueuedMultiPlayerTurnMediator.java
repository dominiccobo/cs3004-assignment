package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.core.Player;
import com.dominiccobo.cs3004.assignment.core.TurnMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Mediator responsible for ensuring that players take turns, playing one round at a time,
 * staying in sync with other players.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class OrderlyQueuedMultiPlayerTurnMediator implements TurnMediator {

    private static final Logger LOG = LoggerFactory.getLogger(OrderlyQueuedMultiPlayerTurnMediator.class);

    private final List<Player> players;
    private Integer currentTurnNo = null;
    private Player playerUsingTerm;

    public OrderlyQueuedMultiPlayerTurnMediator(List<Player> players) {
        this.players = players;
    }

    @Override
    public synchronized void start() {
        this.currentTurnNo = 0;
        playerUsingTerm = players.get(currentTurnNo);
        LOG.info("Mediating game start. First player is {}", playerUsingTerm.getAlias());
        notifyAll();
    }

    @Override
    public synchronized boolean hasTurn(Player player) {
        while (!doesHaveRightToTurn(player)) {
            try {
                wait();
                return false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    private synchronized Boolean doesHaveRightToTurn(Player player) {
        if (currentTurnNo == null) {
            return false;
        }
        Optional<Player> currentPlayerWithTurn = Optional.ofNullable(players.get(currentTurnNo));
        //noinspection OptionalIsPresent
        if (!currentPlayerWithTurn.isPresent()) {
            return false;
        }
        return currentPlayerWithTurn.get().equals(player);
    }

    @Override
    public synchronized void lockTurn(Player player) {
        LOG.info("Player {} has locked their turn.", player.getAlias());
        playerUsingTerm = player;
        notifyAll();
    }

    @Override
    public synchronized void releaseTurn(Player player) {
        LOG.info("Player {} has released their turn.", player.getAlias());
        playerUsingTerm = null;
        currentTurnNo++;
        if (currentTurnNo > players.size() - 1) {
            currentTurnNo = 0;
        }
        notifyAll();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
