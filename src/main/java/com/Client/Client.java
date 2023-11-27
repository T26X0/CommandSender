package com.Client;


import com.Utils.Connectable;
import com.Utils.UserData;
import com.Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Connectable {
    private String userIp;
    private String userName;
    private UserData messageConstructor;
    private PrintWriter output;
    private final Scanner scanner = new Scanner(System.in);
    private String serverIp;
    private int serverPort;

    public Client() {

        userIp = IpLocal.get();
        registerClient();
        settingIpPortServer();

    }

    @Override
    public void startConnect() {

        try {
            connectingToServer(serverIp, serverPort);
            System.out.println("Success connection");
            keyboardTapping();
        } catch (IOException e) {
            // TODO logging
            System.out.println("This server is currently unavailable");
            System.out.println("   Try connecting later");
            System.out.println("   or try connected to another server");

            settingIpPortServer();
        }
    }

    /**
     * Getting and setting ip and port to connect to the server
     */
    public void settingIpPortServer() {
        System.out.println("Enter server ip");
        String ip = scanner.nextLine();
        if (Validator.isValid(ip)) {
            serverIp = ip;
        } else {
            // TODO logging
            System.out.println("You entered an incorrect IP");
            System.out.println("   Try again");
            settingIpPortServer();
        }

        System.out.println("Enter server port");
        int port = Integer.parseInt(scanner.nextLine());
        if (Validator.isValid(port)) {
            serverPort = port;
        } else {
            // TODO logging
            System.out.println("You entered an incorrect IP");
            System.out.println("   Try again");
            settingIpPortServer();
        }
    }

    public String getUserIp() {
        return userIp;
    }

    public String getUserName() {
        return userName;
    }

    private void registerClient() {

        System.out.println("Please enter your name...");
        String name = scanner.nextLine();

        if (!setName(name)) {
            registerClient();
        }
        messageConstructor = new UserData(getUserIp(), getUserName());
    }

    /**
     * <h3>Initializes socket for connection to the Server</h3>
     * <h4>As well as channels for receiving and sending information to the server</h4>
     * Client sends on Server userIp so that the server to add
     * the user socket to the database active users
     */
    private void connectingToServer(String serverIp, int serverPort) throws IOException {
        Socket socket = new Socket(serverIp, serverPort);
        output = new PrintWriter(socket.getOutputStream());

        String message = messageConstructor.prepareMessage("", "");
        output.println(message);
        output.flush();

        System.out.println("Success connection");
    }

    private boolean setName(String name) {
        System.out.println();
        if (name.isEmpty() || name.length() > 14 || getWordCount(name) > 1) {
            System.out.println("Your indicate not valid name:");
            System.out.println("    [*] Name can't be empty");
            System.out.println("    [*] Name must contain no more than 14 characters");
            System.out.println("    [*] Name must contain no more than 1 word");
            System.out.println("    [*] you can use \"_\" or \"-\"");
            System.out.println();
            return false;
        }
        userName = name;
        return true;

    }

    private int getWordCount(String name) {
        return name.trim().split("[\\s]+").length;
    }

    private void keyboardTapping() {
        while (true) {
            System.out.println("Enter message or command");
            String line = scanner.nextLine();
            if (!line.isEmpty()) {

                String message = messageConstructor.prepareMessage("", line);
                output.println(message);
                output.flush();
            }
        }
    }
}


class Begin {
    public static void main(String[] args) {
        Client client = new Client();
        client.startConnect();
    }
}
