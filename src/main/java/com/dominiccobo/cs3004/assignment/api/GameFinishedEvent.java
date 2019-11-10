package com.dominiccobo.cs3004.assignment.api;

import com.dominiccobo.cs3004.assignment.core.scoring.ScoreBoard;

import java.util.Map;

public class GameFinishedEvent {

    public final Map<String, ScoreBoard> playerScores;

    public GameFinishedEvent(Map<String, ScoreBoard> playerScores) {
        this.playerScores = playerScores;
    }
}
