package com.dominiccobo.cs3004.assignment.api;

import com.dominiccobo.cs3004.assignment.ScoreBoard;

/**
 * Describes a player having finished their game.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class PlayerGameFinishedEvent {
    public final String playerName;
    public final ScoreBoard scoreBoard;

    public PlayerGameFinishedEvent(String playerName, ScoreBoard scoreBoard) {
        this.playerName = playerName;
        this.scoreBoard = scoreBoard;
    }
}
