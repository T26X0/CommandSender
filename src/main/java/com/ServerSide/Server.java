package com.ServerSide;


import com.Instruments.MessageConstructor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);

            while (true) {
                System.out.println("I'm wait new client");
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            // TODO: need to add logging
        }
    }
}

class ClientHandler implements Runnable {

    Map<String, String> allUsers = new HashMap<>();

    Socket socket;

    ClientHandler(Socket socket) {
        System.out.println("Success connection");
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner input = new Scanner(socket.getInputStream());
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            Scanner send = new Scanner(System.in);


            while (input.hasNext()) {
                String inputData = input.nextLine();
                MessageConstructor message = new MessageConstructor(inputData);

                String ip = message.getClientIp();
                String name = message.getClientName();
                String recipient = message.getRecipient();
                String content = message.getContent();

                System.out.println("ip: " + ip);
                System.out.println("name: " + name);
                System.out.println("recipient: " + recipient);
                System.out.println("content: " + content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}