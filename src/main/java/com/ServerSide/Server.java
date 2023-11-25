package com.ServerSide;


import com.Instruments.Connectable;
import com.Instruments.MessageConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server implements Connectable {

    ServerSocket serverSocket;
    Map<String, Socket> allUser = new HashMap<>();

    public Server() {
        raiseDataBase();
    }

    @Override
    public void startConnect() {
        try {
            serverSocket = new ServerSocket(8081);

            while (true) {
                System.out.println("I'm wait new client");

                // receives client socket
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            // TODO logging
            System.out.println("err");
        }
    }

    private void raiseDataBase() {
        // TODO I'll add the database when I research it :D
    }

    public void registrationUser(MessageConstructor messageConstructor, Socket socket) {
        allUser.put(
                messageConstructor.getClientIp(),
                socket);
        // TODO logging
    }

    public void sendMessage(MessageConstructor messageConstructor) {
        String clientIp = messageConstructor.getClientIp();
        String clientName = messageConstructor.getClientName();
        String recipientIp = messageConstructor.getRecipientIp();
        String textMessage = messageConstructor.getTextMessage();

        // temporary solution only for demonstration!
        System.out.println("____________");
        System.out.println("New message:");
        System.out.println("  from: " + clientIp);
        System.out.println("  name: " + clientName);
        System.out.println("  to: " + recipientIp);
        System.out.println("  text: " + textMessage);
    }

    public void commandExecution(String command) {

    }
}


class ClientHandler extends Server implements Runnable {

    Socket socket;

    ClientHandler(Socket socket) {
        System.out.println("Success connection");
        this.socket = socket;
    }

    /**
     * <h3>The method processes data that comes from the server,</h3>
     *
     * <pre>this can be:
     *      a command (if the message begins with "!")
     *      a message to the another user (without "!")</pre>
     */
    @Override
    public void run() {
        String lastIp = "None";
        String lastName = "None";

        try {
            Scanner input = new Scanner(socket.getInputStream());

            while (true) {
                String inputData = input.nextLine();

                // if the user sent a command
                if (inputData.startsWith("!")) {
                    commandExecution(inputData);
                } else {
                    MessageConstructor message = new MessageConstructor(inputData);

                    /*
                     * lastIp and lastName store data about the user who was last
                     * accessed the server once
                     *
                     * Then lastIp and lastName are used in logging when a user
                     * unexpectedly disconnects from the server
                     *
                     * ("unexpectedly" == without using the command "!q" or "!exit")
                     */
                    lastIp = message.getClientIp();
                    lastName = message.getClientName();

                    /* the user sends an empty message upon
                     * first connection for identification
                     */
                    if (message.getTextMessage().isEmpty()) {
                        registrationUser(message, socket);
                        System.out.println("User: " + message.getClientName() + " is registered");
                    }
                    /*
                     * processing a regular message
                     */
                    else {
                        sendMessage(message);
                    }
                }
            }
        } catch (IOException e) {
            // TODO logging
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            // TODO logging
            System.out.println(lastName + "/" + lastIp + ": passed out");
        }
    }
}


class Begin {
    public static void main(String[] args) {
        Server server = new Server();
        server.startConnect();
    }
}