package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.core.SessionInstance;
import com.dominiccobo.cs3004.assignment.core.TurnMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    private final List<SessionInstance> sessionInstances;
    private Integer currentTurnNo = null;
    private SessionInstance currentSessionInstanceWithTurn;

    public OrderlyQueuedMultiPlayerTurnMediator() {
        sessionInstances = new ArrayList<>();
    }

    @Override
    public void registerSession(SessionInstance sessionInstance) {
        LOG.info("Adding session instance {}", sessionInstance.getIdentifier());
        sessionInstances.add(sessionInstance);
    }

    @Override
    public synchronized void start() {
        this.currentTurnNo = 0;
        currentSessionInstanceWithTurn = sessionInstances.get(currentTurnNo);
        LOG.info("Mediating game start. First session is {}", currentSessionInstanceWithTurn.getIdentifier());
        notifyAll();
    }

    @Override
    public synchronized boolean hasTurn(SessionInstance sessionInstance) {
        while (!doesHaveRightToTurn(sessionInstance)) {
            try {
                wait();
                return false;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return true;
    }

    private synchronized Boolean doesHaveRightToTurn(SessionInstance sessionInstance) {
        if (currentTurnNo == null) {
            return false;
        }
        Optional<SessionInstance> currentPlayerWithTurn = Optional.ofNullable(sessionInstances.get(currentTurnNo));
        //noinspection OptionalIsPresent
        if (!currentPlayerWithTurn.isPresent()) {
            return false;
        }
        return currentPlayerWithTurn.get().equals(sessionInstance);
    }

    @Override
    public synchronized void lockTurn(SessionInstance sessionInstance) {
        LOG.info("Player {} has locked their turn.", sessionInstance.getIdentifier());
        currentSessionInstanceWithTurn = sessionInstance;
        notifyAll();
    }

    @Override
    public synchronized void releaseTurn(SessionInstance sessionInstance) {
        LOG.info("Player {} has released their turn.", sessionInstance.getIdentifier());
        currentSessionInstanceWithTurn = null;
        currentTurnNo++;
        if (currentTurnNo > sessionInstances.size() - 1) {
            currentTurnNo = 0;
        }
        notifyAll();
    }
}
