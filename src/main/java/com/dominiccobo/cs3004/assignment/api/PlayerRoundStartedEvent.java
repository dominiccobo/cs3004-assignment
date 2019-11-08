package com.dominiccobo.cs3004.assignment.api;

/**
 * Denotes a player having started playing their round.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class PlayerRoundStartedEvent {

    public final String playerName;

    public PlayerRoundStartedEvent(String playerName) {
        this.playerName = playerName;
    }
}
