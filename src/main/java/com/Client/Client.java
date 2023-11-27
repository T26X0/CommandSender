package com.Client;


import com.User_Interface.Text;
import com.User_Interface.User_Display;
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
    private String serverIp;
    private int serverPort;
    User_Display display;

    public Client() {

        display = new User_Display();

        userIp = IpLocal.get();
        registerClient();

        display.reset();
        display.add("Enter server ip", Text.IS_TITLE);
        display.show();
        init_Server_Ip();

        display.reset();
        display.add("Enter server port", Text.IS_TITLE);
        display.show();
        init_Server_Port();
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

    /**
     * Getting and setting ip and port to connect to the server
     */
    public void init_Server_Ip() {

        String ip = scanner.nextLine();
        if (Validator.isValid(ip)) {
            serverIp = ip;
        } else {
            // TODO logging

            display.add("You entered an incorrect IP", Text.IS_ERROR);
            display.add("Try again", Text.IS_ERROR);
            display.show();
            init_Server_Ip();
        }
    }

    public void init_Server_Port() {

        display.replace("Enter server ip ☑");
        display.add("Enter server port", Text.CONTENT);
        display.show();

        try {
            int port = Integer.parseInt(scanner.nextLine());

            if (Validator.isValid(port)) {
                serverPort = port;
            } else {
                // TODO logging
                display.add("You entered an incorrect PORT", Text.CONTENT);
                display.add("   Try again", Text.CONTENT);
                display.show();
                init_Server_Ip();
            }
        } catch (NumberFormatException e) {
            // TODO logging
            display.add("You entered an incorrect PORT", Text.CONTENT);
            display.add("   Try again", Text.CONTENT);
            display.show();
            init_Server_Ip();

        }
    }

    public String get_UserIp() {
        return userIp;
    }

    public String get_UserName() {
        return userName;
    }

    private void registerClient() {

        display.reset();
        display.add("Please enter your name:", Text.IS_TITLE);
        display.show();
        String name = scanner.nextLine();

        if (!set_Name(name)) {
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

            display.add("Your indicate not valid name:", Text.CONTENT);
            display.add("* Name can't be empty", Text.CONTENT);
            display.add("* Name must contain no more than 14 characters", Text.CONTENT);
            display.add("* Name must contain no more than 1 word", Text.CONTENT);
            display.add("* you can use \"_\" or \"-\"", Text.CONTENT);
            display.show();
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
}


class Begin {
    public static void main(String[] args) {
        Client client = new Client();
        client.startConnect();
    }
}
