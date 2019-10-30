package com.dominiccobo.cs3004.assignment.multiplayer.server;

import com.dominiccobo.cs3004.assignment.multiplayer.Lobby;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Application entry point for the server.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ServerApplicationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(ServerApplicationEntryPoint.class);

    public static void main(String[] args) throws IOException {
        int port = 50000;
        LOG.info("Starting up server on {}.", port);
        final Lobby lobby = Lobby.Factory.create(port);
    }
}
