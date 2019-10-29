package com.dominiccobo.cs3004.assignment.multiplayer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client application entrypoint.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ClientApplicationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(ClientApplicationEntryPoint.class);

    public static void main(String[] args) {
        try {
            new Client("localhost", 50000);
        } catch (Exception e) {
            LOG.error("There was an issue with the connection.", e);
        }
    }

}
