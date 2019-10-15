package com.dominiccobo.cs3004.assignment;

/**
 * Collection of utilities relating to handling console input ...
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 * @author Simon Taylor (some networking lecturer at brunel)
 */
public class InputUtils {

    public static int readIntegerConsoleInput(String Prompt) {
        int result = 0;
        try {
            result = Integer.parseInt(readConsoleInput(Prompt).trim());
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public static String readConsoleInput(String prompt) {
        String inputLine = "";
        System.out.print(prompt);
        try {
            java.io.InputStreamReader sys = new java.io.InputStreamReader(System.in);
            java.io.BufferedReader inBuffer = new java.io.BufferedReader(sys);
            inputLine = inBuffer.readLine();
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        }
        return inputLine;
    }
}
