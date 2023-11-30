package Client_Side.Client;


import Client_Side.Client.Config.Local_ip;
import Client_Side.Client.Config.User_Fields;
import Client_Side.User_Display.Config.InfoBlock;
import Client_Side.User_Display.Config.TextBlock;
import Client_Side.User_Display.User_Display;
import Utils.UserData;
import Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.ConnectionPendingException;
import java.util.Scanner;

public class Client extends User_Fields {

    Socket socket;
    private boolean connection_status;
    private static final int temporary_port_plug = 0;
    private UserData messageConstructor;
    private PrintWriter output;
    private final Scanner scanner = new Scanner(System.in);
    User_Display display;

    public Client() {
        display = new User_Display();

        if (!(has_userName() && has_serverIp() && has_serverPort())) {
            client_registration();
        }
        connecting_toServer();
    }

    /**
     * <h3>Initializes socket for connection to the Server</h3>
     * <h4>As well as channels for receiving and sending information to the server</h4>
     * Client sends on Server userIp so that the server to add
     * the user socket to the database active users
     */
    public void connecting_toServer() {
        try {
            socket = new Socket(get_serverIp(), get_serverPort());
            output = new PrintWriter(socket.getOutputStream());

            String message = messageConstructor.prepareMessage("", "");
            output.println(message);
            output.flush();

            System.out.println("Success connection");
            connection_status = true;

            Thread thread = new Thread(new ServerHandler(socket));
            thread.start();

        } catch (IOException e) {
            // TODO logging
            connection_status = false;
        }
    }

    public void keyboard_handler() {
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

    private void update_userInfo(String name, String ip, int port) {
        User_Fields.set_userName(name);
        User_Fields.set_serverIp(ip);
        User_Fields.set_serverPort(port);
        messageConstructor = new UserData(get_userIp(), name);
    }

    private void update_userInfo(String name, String ip) {
        update_userInfo(name, ip, get_serverPort());
    }

    private void update_userInfo(String name) {
        update_userInfo(name, get_serverIp(), get_serverPort());
    }

    public boolean get_connectionStatus() {
        return connection_status;
    }

    public Socket get_serverSocket() {
        return socket;
    }

    public void show_info(InfoBlock infoBlock) {
        String[] exceptionLines = infoBlock.getExceptionLines();

        this.display.add(exceptionLines[0], TextBlock.CONTENT);
        this.display.add(exceptionLines[1], TextBlock.CONTENT);
        this.display.add(exceptionLines[2], TextBlock.CONTENT);
        this.display.show();
    }

    private void client_registration() {
        String temporary_userName;
        String temporary_serverIp;
        int temporary_serverPort;
        User_Fields.set_userIp(Local_ip.get());



        display.reset();
        display.add("PLEASE ENTER YOUR NAME:", TextBlock.TITLE);
        display.show();
        temporary_userName = name_request();
        update_userInfo(temporary_userName);

        display.reset();
        display.add("ENTER SERVER IP:", TextBlock.TITLE);
        display.show();
        temporary_serverIp = serverIp_request();
        update_userInfo(temporary_userName, temporary_serverIp);

        display.reset();
        display.add("ENTER SERVER PORT:", TextBlock.TITLE);
        display.show();
        temporary_serverPort = serverPort_request();

        update_userInfo(temporary_userName, temporary_serverIp, temporary_serverPort);
    }

    private String name_request() {
        String name = scanner.nextLine();

        while (!Validator.userName_isValid(name)) {

            display.add("Your indicate not valid name:", TextBlock.CONTENT);
            display.add("* Name can't be empty", TextBlock.CONTENT);
            display.add("* Name must contain no more than 11 characters and 1 word", TextBlock.CONTENT);
            display.add("* you can use \"_\" or \"-\"", TextBlock.CONTENT);
            display.show();
            name = scanner.nextLine();
        }
        return name;
    }

    /**
     * Getting and setting ip and port to connect to the server
     */
    private String serverIp_request() {

        String ip = scanner.nextLine();

        while (!Validator.ip_isValid(ip)) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT IP", TextBlock.CONTENT);
            display.add("TRY AGAIN", TextBlock.CONTENT);
            display.show();
            ip = scanner.nextLine();
        }
        return ip;
    }

    private int serverPort_request() {

        int port = temporary_port_plug;

        try {
            port = Integer.parseInt(scanner.nextLine());

            while (!Validator.port_isValid(port)) {
                // TODO logging

                display.add("YOU ENTERED AN INCORRECT PORT", TextBlock.CONTENT);
                display.add("TRY AGAIN", TextBlock.CONTENT);
                display.show();
                port = Integer.parseInt(scanner.nextLine());
            }

        } catch (NumberFormatException e) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT PORT", TextBlock.CONTENT);
            display.add("TRY AGAIN", TextBlock.CONTENT);
            display.show();
            port = serverPort_request();
        }
        return port;
    }
}


class ServerHandler extends Client implements Runnable {

    Socket socket;

    ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

    }
}


class Begin {
    public static void main(String[] args) {
        Client client = new Client();
        client.connecting_toServer();

        if (client.get_connectionStatus()) {
            ServerHandler serverHandler = new ServerHandler(client.get_serverSocket());
        } else {
            client.show_info(InfoBlock.SERVER_UNAVAILABLE);
        }
        client.keyboard_handler();
    }
}
