package com.ClientSide;


import com.Instruments.MessageCunstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {

    private static String ip;
    private static String name;

    private static MessageCunstructor messageConstructor;
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
        setName();

        messageConstructor = new MessageCunstructor(ip, name);
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
                ip = matcher.group(1);
            }
        } catch (UnknownHostException e) {
            // TODO: need to add logging
            ip = "Ip not found";
        }

    }

    private static void setName() {

        // User needs to enter his name in terminal

        // Temporary solution:
        System.out.println("Please enter your name...");
        name = scanner.nextLine();
        System.out.println("Welcome :D");
    }

    public static String getIp() {
        return ip;
    }

    public static String getName() {
        return name;
    }
}

