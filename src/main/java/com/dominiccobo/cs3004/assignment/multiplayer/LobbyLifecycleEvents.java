package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.Player;
import com.dominiccobo.cs3004.assignment.connection.Connection;

/**
 * Represents a typical lifecycle of a game instance.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public interface LobbyLifecycleEvents {

    void onLobbyCreated();
    void onPlayerConnectionIncoming(Connection connection);
    void onPlayerConnect(Player player);
    void onPlayerReady(Player playerReady);
    void onLobbyReady();
    void onGameStart();
    void onGameEnd();
    void onLobbyClosed();
    void onLobbyStateChange(LobbyState lobbyState);
}
