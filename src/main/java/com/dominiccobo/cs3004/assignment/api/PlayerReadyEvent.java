package com.dominiccobo.cs3004.assignment.api;

/**
 * Describes a player having set their alias and thus being ready.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class PlayerReadyEvent {

    public final String playerName;

    public PlayerReadyEvent(String playerName) {
        this.playerName = playerName;
    }
}
