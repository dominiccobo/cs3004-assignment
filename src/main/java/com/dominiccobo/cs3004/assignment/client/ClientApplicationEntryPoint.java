package com.dominiccobo.cs3004.assignment.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * Client application entrypoint.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ClientApplicationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(ClientApplicationEntryPoint.class);

    public static void main(String[] args) {
        try {
            new Client(getHostConfiguration(), getPortConfiguration(), new PrintWriter(System.out))
        } catch (Exception e) {
            LOG.error("There was an issue with the connection.", e);
        }
    }

    private static String getHostConfiguration() {
        return getConfiguration("YAHTZEE_HOSTNAME", "yahtzee.hostname", "localhost");
    }

    private static int getPortConfiguration() {
        String yahtzeePort = getConfiguration("YAHTZEE_PORT", "yahtzee.port", "50000");
        return Integer.parseInt(yahtzeePort);
    }

    private static String getConfiguration(String envVar, String propertyVar, String defaultValue) {
        String envConfigValue = System.getenv(envVar);
        String propertyVarConfigValue = System.getProperty(propertyVar);
        if(envConfigValue != null && envConfigValue.isEmpty()) {
            return envConfigValue;
        }
        if(propertyVarConfigValue != null && propertyVarConfigValue.isEmpty()) {
            return propertyVarConfigValue;
        }
        return defaultValue;
    }
}
