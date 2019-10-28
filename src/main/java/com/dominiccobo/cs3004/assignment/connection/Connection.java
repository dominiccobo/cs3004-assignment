package com.dominiccobo.cs3004.assignment.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Models the required information for the game to contact a playing entity whether
 * local, remote or emulated.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public interface Connection {

    OutputStream getOutputStream() throws IOException;
    InputStream getInputStream() throws IOException;
}
