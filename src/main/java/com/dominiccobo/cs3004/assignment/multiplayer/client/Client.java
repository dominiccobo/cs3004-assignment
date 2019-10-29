package com.dominiccobo.cs3004.assignment.multiplayer.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Basic "dumb" client connection aimed at responding only to the a socket input stream.
 * The only protocol established here  is to listen for an [INPUT] string and allow
 * input based on its presence.
 *
 * @author Dominic Cobo (contact@dominiccobo.com)
 */
public class Client {

    private static final Logger LOG = LoggerFactory.getLogger(Client.class);

    private Socket clientSocket;
    private PrintWriter serverInputStream;
    private BufferedReader serverOutputStream;

    public Client(String hostToConnectTo, int portToConnectTo) throws Exception {
        this.connectToServer(hostToConnectTo, portToConnectTo);
        this.startConversation();
    }

    private void connectToServer(String hostToConnectTo, int portToConnectTo) throws IOException {
        LOG.info("Connecting to server {}:{}", hostToConnectTo, portToConnectTo);
        this.clientSocket = new Socket(hostToConnectTo, portToConnectTo);

        if (this.clientSocket.isConnected()) {
            LOG.info("Successfully connected to {}:{}", hostToConnectTo, portToConnectTo);
            serverInputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            serverOutputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } else {
            LOG.warn("No connection appears to be established to the server.");
        }
    }

    private void cleanUp() throws IOException {
        serverInputStream.close();
        serverOutputStream.close();
    }

    private void startConversation() throws IOException {
        while (this.clientSocket.isConnected()) {
            String fromServer = getServerReply();
            System.out.println(fromServer);

            if (fromServer.contains("[INPUT]")) {
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
                String userInput = consoleInput.readLine();

                if (userInput != null) {
                    LOG.debug("Client input was: {}", userInput);
                    sendMessageToServer(userInput);
                }
            }
        }
        this.cleanUp();
    }

    private String getServerReply() throws IOException {
        return serverOutputStream.readLine();
    }

    private void sendMessageToServer(String fromUser) {
        serverInputStream.println(fromUser);
    }

}
