package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.Player;
import com.dominiccobo.cs3004.assignment.TurnMediator;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class FFAMultiPlayerTurnMediator implements TurnMediator {
    @Override
    public boolean hasTurn(Player player) {
        return true;
    }

    @Override
    public void lockTurn(Player player) {
    }

    @Override
    public void releaseTurn(Player player) {

    }
}
