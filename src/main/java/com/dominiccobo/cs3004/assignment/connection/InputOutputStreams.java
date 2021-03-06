package com.dominiccobo.cs3004.assignment.connection;

import com.dominiccobo.cs3004.assignment.api.ConnectionProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Collection of utilities relating to handling console input ...
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
@SuppressWarnings("WeakerAccess")
public class InputOutputStreams {

    private static final Logger LOG = LoggerFactory.getLogger(InputOutputStreams.class);

    private final InputStream inputStream;
    private final PrintStream outputStream;

    public InputOutputStreams() {
        this.inputStream = System.in;
        this.outputStream = System.out;
    }

    public InputOutputStreams(Connection connection) throws IOException {
        this.inputStream = connection.getInputStream();
        this.outputStream = new PrintStream(connection.getOutputStream());
    }

    public void println(String line) {
        LOG.trace("Sending message:  {}", line);
        outputStream.println(line);
        outputStream.flush();
    }

    public String readConsoleInput(String prompt) {
        String inputLine = "";
        final String message = ConnectionProtocol.CONNECTION_INPUT_REQUEST + prompt;
        LOG.trace("Requesting input [{}]", message);
        outputStream.println(message);
        try {
            InputStreamReader sys = new InputStreamReader(this.inputStream);
            BufferedReader inBuffer = new BufferedReader(sys);
            inputLine = inBuffer.readLine();
            LOG.debug("Received input: {}", inputLine);
        } catch (Exception e) {
            LOG.error("Could not read console input ", e);
        }
        return inputLine;
    }

    public int readIntegerConsoleInput(String prompt, int min, int max) {
        final int i = readIntegerConsoleInput(prompt);

        if(i < min) {
            outputStream.println("Minimum value is " + min);
            return readIntegerConsoleInput(prompt, min, max);
        }
        if(i > max) {
            outputStream.println("Maximum value is " + max);
            return readIntegerConsoleInput(prompt, min, max);
        }
        else return i;
    }

    public int readIntegerConsoleInput(String Prompt) {
        int result;
        try {
            result = Integer.parseInt(readConsoleInput(Prompt).trim());
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
