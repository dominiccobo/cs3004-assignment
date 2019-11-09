package com.dominiccobo.cs3004.assignment.api;

import com.dominiccobo.cs3004.assignment.core.ScoreBoard;

/**
 * Denotes a player having finished their current round.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class PlayerRoundFinishedEvent {

    public final String playerName;
    public final ScoreBoard playerScore;

    public PlayerRoundFinishedEvent(String playerName, ScoreBoard playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }
}
