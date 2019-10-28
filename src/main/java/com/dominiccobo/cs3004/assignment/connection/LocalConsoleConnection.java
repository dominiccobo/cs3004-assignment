package com.dominiccobo.cs3004.assignment.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Represents a local connection for a player playing the game
 * via the std in and out streams, i.e. console..
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class LocalConsoleConnection implements Connection {

    @Override
    public OutputStream getOutputStream() throws IOException {
        return System.out;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return System.in;
    }
}
