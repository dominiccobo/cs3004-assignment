package com.dominiccobo.cs3004.assignment.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Collection of utilities relating to handling console input ...
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 * @author Simon Taylor (some networking lecturer at brunel)
 */
@SuppressWarnings("WeakerAccess")
public class InputOutputStreams {

    private final InputStream inputStream;
    private final PrintStream outputStream;

    public InputOutputStreams() {
        this.inputStream = System.in;
        this.outputStream = System.out;
    }

    public InputOutputStreams(InputStream inputStream, PrintStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void println(String line) {
        outputStream.println(line);
    }

    private String readConsoleInput(String prompt) {
        String inputLine = "";
        outputStream.print(prompt);
        try {
            InputStreamReader sys = new InputStreamReader(this.inputStream);
            BufferedReader inBuffer = new BufferedReader(sys);
            inputLine = inBuffer.readLine();
        } catch (Exception e) {
            String err = e.toString();
            System.err.println(err);
        }
        return inputLine;
    }

    public int readIntegerConsoleInput(String prompt, int min, int max) {
        final int i = new InputOutputStreams().readIntegerConsoleInput(prompt);

        if(i < min) {
            outputStream.println("Minimum value is " + min);
            return new InputOutputStreams().readIntegerConsoleInput(prompt, min, max);
        }
        if(i > max) {
            outputStream.println("Maximum value is " + max);
            return new InputOutputStreams().readIntegerConsoleInput(prompt, min, max);
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
