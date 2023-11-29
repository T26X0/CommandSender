package Server_Side;

import Utils.Connectable;
import Utils.UserData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server implements Connectable {

    ServerSocket serverSocket;
    public static Map<String, Socket> allUser = new HashMap<>();

    public Server() {
        raiseDataBase();
    }

    @Override
    public boolean startConnect() {
        try {
            serverSocket = new ServerSocket(8081);

            while (true) {
                System.out.println("I'm wait new client");

                // receives client socket
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
                return true;
            }
        } catch (IOException e) {
            // TODO logging
            return false;
        }
    }

    private void raiseDataBase() {
        // TODO I'll add the database when I research it :D
    }

    public void sendMessage(UserData messageConstructor) {
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

    public void commandExecution(UserData userData) {

    }
}


class ClientHandler extends Server implements Runnable {

    Socket socket;

    /**
     * <h2>A new monitoring of one user is created over the socket</h2>
     * @param socket Socket
     */
    ClientHandler(Socket socket) {
        // TODO logging
        System.out.println("Success connection");
        this.socket = socket;
    }

    /**
     * Adds a user to the shared database
     * @param userData UserData
     * @param socket Socket
     */
    public static void registrationUser(UserData userData, Socket socket) {
        allUser.put(
                userData.getClientIp(),
                socket);
        // TODO logging
    }

    /**
     * <strong>The method checks the user's registration</strong>
     * <h3>The user sends an empty message if he is not registered</h3>
     * @param userData UserData
     * @return boolean
     */
    private static boolean userIsRegistered(UserData userData) {
        return !(userData.getTextMessage().isEmpty());
    }

    /**
     *<h3>The method checks the message for the commands</h3>
     *<pre>
     *     Example:
     *     "!add" - command to the adding friend
     *     "Hello" - message
     * </pre>
     *
     *
     * @param userData UserData
     * @return boolean
     */
    private static boolean isCommand(UserData userData) {
        return userData.getTextMessage().startsWith("!");
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

        try (Scanner input = new Scanner(socket.getInputStream())) {
            while (true) {
                String fromUser = input.nextLine();
                UserData userData = new UserData(fromUser);

                if (!userIsRegistered(userData)) {
                    registrationUser(userData, socket);
                } else {
                    lastIp = userData.getClientIp();
                    lastName = userData.getClientName();

                    if (isCommand(userData)) {
                        commandExecution(userData);
                    } else {
                        sendMessage(userData);
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

    /**
     * <h2>Start of the program</h2>
     * <h3>Entry point for the Client</h3>
     */
    public static void main(String[] args) {
        Server server = new Server();

        boolean connection_status = false;
        do {
            connection_status = server.startConnect();
        }
        while (!connection_status);

    }
}