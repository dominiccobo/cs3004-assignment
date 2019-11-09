package com.dominiccobo.cs3004.assignment.server;

import com.dominiccobo.cs3004.assignment.multiplayer.Lobby;
import com.dominiccobo.cs3004.assignment.utils.ConfigurationHelper;
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
        int port = getPortConfiguration();
        LOG.info("Starting up server on {}.", port);
        Lobby.Factory.create(port);
    }

    private static int getPortConfiguration() {
        String yahtzeePort = ConfigurationHelper.getConfiguration("YAHTZEE_PORT", "yahtzee.port", "50000");
        return Integer.parseInt(yahtzeePort);
    }
}
