package com.ClientSide;


import com.Instruments.MessageConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    private static String userIp;
    private static String userName;

    private static MessageConstructor messageConstructor;
    static Socket socket;
    static PrintWriter output;
    static Scanner input;
    static Scanner scanner;

    public static void main(String[] args) throws IOException {

        initClient();
        initServer();

        System.out.println("Enter message");
        String content = scanner.nextLine();

        String message = messageConstructor.prepareMessage("your mom :D", content);

        output.println(message);
        output.flush();
    }

    private static void initClient() {

        scanner = new Scanner(System.in);
        setIp();

        System.out.println("Please enter your name...");
        String name = scanner.nextLine();
        setName(name);

        messageConstructor = new MessageConstructor(userIp, userName);
    }

    private static void initServer() throws IOException {
        socket = new Socket("25.37.138.125", 8081);
        output = new PrintWriter(socket.getOutputStream());
        input = new Scanner(socket.getInputStream());
    }

    private static void setIp() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();

            Pattern pattern = Pattern.compile("/(.*)");
            Matcher matcher = pattern.matcher(localHost.toString());

            while (matcher.find()) {
                userIp = matcher.group(1);
            }
        } catch (UnknownHostException e) {
            // TODO: need to add logging
            userIp = "Ip not found";
        }
    }

    private static void setName(String name) {

        // TODO: name check
        userName = name;
        // else:
        // "Enter correct name"
    }

    public static String getUserIp() {
        return userIp;
    }

    public static String getUserName() {
        return userName;
    }
}

