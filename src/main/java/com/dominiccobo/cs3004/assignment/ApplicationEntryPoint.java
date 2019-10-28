package com.dominiccobo.cs3004.assignment;

import com.dominiccobo.cs3004.assignment.connection.LocalConsoleConnection;

import java.io.IOException;

/**
 * TODO: add class description.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class ApplicationEntryPoint {
    public static void main(String[] args) throws IOException {

        Player player = new Player(new LocalConsoleConnection());
        player.start();
    }
}
