package com.Client;


import com.Line_Interface.Block;
import com.Line_Interface.User_Display;
import com.Utils.Connectable;
import com.Utils.UserData;
import com.Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends User_Const implements Connectable {
    private String userIp;
    private String userName;
    private UserData messageConstructor;
    private PrintWriter output;
    private final Scanner scanner = new Scanner(System.in);
    private String serverIp = "_";
    private int serverPort = 0;
    User_Display display;

    public Client() {

        display = new User_Display();

        userIp = IpLocal.get();

        display.reset();
        display.add("server ip: " + serverIp, Block.SERVER_IP);
        display.add("server port: " + "_", Block.SERVER_PORT);
        display.add("PLEASE ENTER YOUR NAME", Block.CONTEXT);
        display.show();

        registerClient();

        display.reset();
        display.add("server ip: " + serverIp, Block.SERVER_IP);
        display.add("server port: " + "_", Block.SERVER_PORT);
        display.add("ENTER SERVER IP", Block.CONTEXT);
        display.show();

        init_Server_Ip();

        display.reset();
        display.add("server ip: " + serverIp, Block.SERVER_IP);
        display.add("server port: " + "_", Block.SERVER_PORT);
        display.add("ENTER SERVER IP", Block.CONTEXT);
        display.show();

        init_Server_Port();

        display.reset();
        display.add("server ip: " + serverIp, Block.SERVER_IP);
        display.add("server port: " + serverPort, Block.SERVER_PORT);
        display.add("...connection to the server...", Block.CONTEXT);
        display.show();

        try {
            Thread.sleep(2700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("exit");
    }

    /**
     * Getting and setting ip and port to connect to the server
     */
    public void init_Server_Ip() {


        String ip = scanner.nextLine();
        if (Validator.isValid(ip)) {
            serverIp = ip;
        } else {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT IP", Block.CONTEXT);
            display.add("TRY AGAIN", Block.CONTEXT);
            display.show();
            init_Server_Ip();
        }
    }

    public void init_Server_Port() {

        try {
            int port = Integer.parseInt(scanner.nextLine());

            if (Validator.isValid(port)) {
                serverPort = port;
            } else {
                // TODO logging

                display.add("YOU ENTERED AN INCORRECT PORT", Block.CONTEXT);
                display.add("TRY AGAIN", Block.CONTEXT);
                display.show();
                init_Server_Port();
            }
        } catch (NumberFormatException e) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT PORT", Block.CONTEXT);
            display.add("TRY AGAIN", Block.CONTEXT);
            display.show();
            init_Server_Port();
        }
    }

    public String get_UserIp() {
        return userIp;
    }

    public String get_UserName() {
        return userName;
    }

    private void registerClient() {

        String name = scanner.nextLine();

        if (!set_Name(name)) {

            display.add("Your indicate not valid name:", Block.CONTEXT);
            display.add("* Name can't be empty", Block.CONTEXT);
            display.add("* Name must contain no more than 14 characters", Block.CONTEXT);
            display.add("* Name must contain no more than 1 word", Block.CONTEXT);
            display.add("* you can use \"_\" or \"-\"", Block.CONTEXT);
            display.show();
            registerClient();
        }
        display.set_userName(name);
        messageConstructor = new UserData(get_UserIp(), get_UserName());


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

    private boolean set_Name(String name) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH || get_WordCount(name) > 1) {
            return false;
        }
        userName = name;
        return true;

    }

    private int get_WordCount(String name) {
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

            init_Server_Ip();
        }
    }
}


class Begin {
    public static void main(String[] args) {
        Client client = new Client();
        client.startConnect();
    }
}
