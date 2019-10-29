package com.dominiccobo.cs3004.assignment;

/**
 * Classic GOF pattern for decoupling two parties and mediating
 * communications between them.
 *
 * The turn mediator is responsible for arbitrating the turn
 * protocol between all players involved in a game.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public interface TurnMediator {

    boolean hasTurn(Player player);
    void lockTurn(Player player);
    void releaseTurn(Player player);
}
