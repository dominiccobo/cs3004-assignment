package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.Player;
import com.dominiccobo.cs3004.assignment.TurnMediator;
import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.SocketConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Processes all of the lifecycle related to a game. Players will join a lobby, providing there
 * is space and and agree on being ready.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Lobby implements LobbyLifecycleEvents {

    private static final Logger LOG = LoggerFactory.getLogger(Lobby.class);

    public static final int MAX_PLAYER_COUNT = 4;

    private List<Player> players = new ArrayList<>();
    private ServerSocket serverSocket;
    private LobbyState lobbyState;
    private TurnMediator turnMediator;

    private Lobby(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.turnMediator = new FFAMultiPlayerTurnMediator();
        onLobbyStateChange(LobbyState.CREATED);
        onLobbyCreated();
    }

    @Override
    public void onLobbyCreated() {
        LOG.info("Lobby created. Awaiting players.");
        this.onLobbyStateChange(LobbyState.OPEN);
        while (lobbyState == LobbyState.OPEN) {
            try {
                onPlayerConnectionIncoming(new SocketConnection(serverSocket.accept()));
            } catch (IOException e) {
                LOG.error("Could not handle incoming player connection.", e);
            }
        }
        onLobbyClosed();
    }

    @Override
    public void onPlayerConnectionIncoming(Connection connection) {
        LOG.info("New player connection incoming.");
        try {
            Player player = new Player(connection, this.turnMediator);
            onPlayerConnect(player);
        } catch (IOException e) {
            LOG.error("Could not instantiate new player.", e);
        }
    }

    @Override
    public void onPlayerConnect(Player player) {
        this.players.add(player);
        player.start();
    }


    // TODO: needs observer mechanism to interface between this lobby and the player.
    @Override
    public void onPlayerReady(Player playerReady) {
        // TODO: add player to ready players....
        if (areAllPlayersReady())
            onLobbyReady();
    }

    private boolean areAllPlayersReady() {
        return true;
    }

    @Override
    public void onLobbyReady() {
        onLobbyStateChange(LobbyState.READY);
        onGameStart();
        // TODO: inform mediator to allow players to start.
    }

    @Override
    public void onGameStart() {
        onLobbyStateChange(LobbyState.GAME_IN_PROGRESS);
    }

    @Override
    public void onGameEnd() {
        onLobbyClosed();
    }

    @Override
    public void onLobbyClosed() {
        try {
            onLobbyStateChange(LobbyState.CLOSED);
            serverSocket.close();
        } catch (IOException e) {
            LOG.error("Could not close lobby.", e);
        }
    }

    @Override
    public void onLobbyStateChange(LobbyState lobbyState) {
        this.lobbyState = lobbyState;
    }

    public static class Factory {

        public static Lobby create(int port) throws IOException {
            return new Lobby(port);
        }
    }
}
