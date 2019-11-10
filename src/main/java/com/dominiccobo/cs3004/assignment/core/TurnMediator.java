package com.dominiccobo.cs3004.assignment.core;

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

    void registerSession(SessionInstance sessionInstance);
    void start();
    boolean hasTurn(SessionInstance sessionInstance);
    void lockTurn(SessionInstance sessionInstance);
    void releaseTurn(SessionInstance sessionInstance);
}
