package com.dominiccobo.cs3004.assignment.client;

import com.dominiccobo.cs3004.assignment.utils.ConfigurationHelper;
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
            new Client(getHostConfiguration(), getPortConfiguration());
        } catch (Exception e) {
            LOG.error("There was an issue with the connection.", e);
        }
    }

    private static String getHostConfiguration() {
        return ConfigurationHelper.getConfiguration("YAHTZEE_HOSTNAME", "yahtzee.hostname", "localhost");
    }

    private static int getPortConfiguration() {
        String yahtzeePort = ConfigurationHelper.getConfiguration("YAHTZEE_PORT", "yahtzee.port", "50000");
        return Integer.parseInt(yahtzeePort);
    }

}
