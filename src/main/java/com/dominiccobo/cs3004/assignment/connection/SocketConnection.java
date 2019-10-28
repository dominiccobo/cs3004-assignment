package com.dominiccobo.cs3004.assignment.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Provides an implementation of a {@link Connection} specific to the {@link Socket} API.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
@SuppressWarnings("WeakerAccess")
public class SocketConnection implements Connection {

    private final Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }
}
