package com.dominiccobo.cs3004.assignment.multiplayer;

import com.dominiccobo.cs3004.assignment.api.*;
import com.dominiccobo.cs3004.assignment.connection.Connection;
import com.dominiccobo.cs3004.assignment.connection.SocketConnection;
import com.dominiccobo.cs3004.assignment.core.Player;
import com.dominiccobo.cs3004.assignment.core.PlayerLifecycleEvents;
import com.dominiccobo.cs3004.assignment.core.SessionIdentifier;
import com.dominiccobo.cs3004.assignment.core.TurnMediator;
import com.dominiccobo.cs3004.assignment.core.scoring.ScoreBoard;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

/**
 * Processes all of the lifecycle related to a game. Players will join a lobby, providing there
 * is space and and agree on being ready.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Lobby implements LobbyLifecycleEvents, PlayerLifecycleEvents {

    private static final Logger LOG = LoggerFactory.getLogger(Lobby.class);

    public static final int MAX_PLAYER_COUNT = 3;

    private int playerReadyCount = 0;
    private HashMap<String, ScoreBoard> finishedPlayers = new HashMap<>();
    private Set<Player> connections = new HashSet<>();
    private Map<String, SessionIdentifier> playerToSessionIdentifier = new IdentityHashMap<>();
    private ServerSocket serverSocket;
    private LobbyState lobbyState;
    private TurnMediator turnMediator;
    private final EventBus eventBus;

    private Lobby(int port, EventBus eventBus) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.eventBus = eventBus;
        eventBus.register(this);
        this.turnMediator = new OrderlyQueuedMultiPlayerTurnMediator();
        onLobbyStateChange(LobbyState.CREATED);
        onLobbyCreated();
    }

    @Override
    public void onLobbyCreated() {
        LOG.info("Lobby created. Awaiting players.");
        this.onLobbyStateChange(LobbyState.OPEN);
        while (lobbyState == LobbyState.OPEN) {
            try {
                // FIXME: this does not cause a clean exit
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
            Player player = new Player(connection, this.turnMediator, this.eventBus);
            onPlayerConnect(player);
        } catch (IOException e) {
            LOG.error("Could not instantiate new player.", e);
        }
    }

    @Override
    public void onPlayerConnect(Player player) {
        connections.add(player);
        player.start();
    }


    // TODO: needs observer mechanism to interface between this lobby and the player.
    @Override
    public void onPlayerReady(Player playerReady) {
        playerReadyCount++;
        if (areAllPlayersReady()) {
            LOG.info("All players are ready. Readying lobby.");
            onLobbyReady();
        }
    }

    private boolean areAllPlayersReady() {
        return playerReadyCount == MAX_PLAYER_COUNT;
    }

    @Override
    public void onLobbyReady() {
        onLobbyStateChange(LobbyState.READY);
        turnMediator.start();
        onGameStart();
    }

    @Override
    public void onGameStart() {
        onLobbyStateChange(LobbyState.GAME_IN_PROGRESS);
    }

    @Override
    public void onGameEnd() {
        LOG.info("Game finished. Sending scores to all.");
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

    @Override
    public void on(PlayerReadyEvent event) {
        LOG.info("Player {} [{}] is ready", event.playerName, event.sessionIdentifier);
        playerToSessionIdentifier.put(event.playerName, event.sessionIdentifier);
        this.onPlayerReady(null); // FIXME:
    }

    @Override
    public void on(PlayerRoundStartedEvent event) {
        LOG.info("{} has started playing their round.", event.playerName);
    }

    @Override
    public void on(PlayerRoundFinishedEvent event) {
        LOG.info("{} finished with score {}", event.playerName, event.playerScore.buildScoreBoardString());
    }

    @Override
    public void on(PlayerGameFinishedEvent event) {
        LOG.info("{} has finished their game.", event.playerName);
        finishedPlayers.put(event.playerName, event.scoreBoard);
        if(allPlayersFinished()) {
            eventBus.post(new GameFinishedEvent(finishedPlayers));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onGameEnd();
        }
    }

    private boolean allPlayersFinished() {
        return finishedPlayers.size() == MAX_PLAYER_COUNT;
    }

    public static class Factory {

        public static Lobby create(int port) throws IOException {

            final EventBus eventBus = new EventBus();
            return new Lobby(port, eventBus);
        }
    }
}
