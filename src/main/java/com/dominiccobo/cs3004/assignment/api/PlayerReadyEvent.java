package com.dominiccobo.cs3004.assignment.api;

import com.dominiccobo.cs3004.assignment.core.SessionIdentifier;

/**
 * Describes a player having set their alias and thus being ready.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class PlayerReadyEvent {

    public final String playerName;
    public final SessionIdentifier sessionIdentifier;

    public PlayerReadyEvent(String playerName, SessionIdentifier sessionIdentifier) {
        this.playerName = playerName;
        this.sessionIdentifier = sessionIdentifier;
    }
}
