package Client_Side.Client;



import Client_Side.Client.Config.Local_ip;
import Client_Side.Client.Config.User_Fields;
import Client_Side.User_Display.Config.TextBlock;
import Client_Side.User_Display.User_Display;
import Utils.Connectable;
import Utils.UserData;
import Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends User_Fields implements Connectable {

    private static final int temporary_port_plug = 0;
    private UserData messageConstructor;
    private PrintWriter output;
    private final Scanner scanner = new Scanner(System.in);
    User_Display display;

    public Client() {
        display = new User_Display();
    }

    private void update_userInfo(String name, String ip, int port) {
        User_Fields.set_userName(name);
        messageConstructor = new UserData(get_userIp(), name);
        User_Fields.set_serverIp(ip);
        User_Fields.set_serverPort(port);
    }

    private void update_userInfo(String name, String ip) {
        update_userInfo(name, ip, get_serverPort());
    }

    private void update_userInfo(String name) {
        update_userInfo(name, get_serverIp(), get_serverPort());
    }

    private String name_request() throws IOException {
        String name = scanner.nextLine();

        while (!name_isValid(name)) {

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
    public String serverIp_request() throws IOException {

        String ip = scanner.nextLine();

        while (!Validator.isValid(ip)) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT IP", TextBlock.CONTENT);
            display.add("TRY AGAIN", TextBlock.CONTENT);
            display.show();
            ip = scanner.nextLine();
        }
        return ip;
    }

    public int serverPort_request() throws IOException {

        int port = temporary_port_plug;

        try {
            port = Integer.parseInt(scanner.nextLine());

            while (!Validator.isValid(port)) {
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

    private boolean name_isValid(String name) {
        return !name.isEmpty() && name.length() <= MAX_NAME_LENGTH && get_WordCount(name) <= 1;

    }

    private int get_WordCount(String name) {
        return name.trim().split("[\\s]+").length;
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

    @Override
    public boolean startConnect() {

        String temporary_userName;
        String temporary_serverIp;
        int temporary_serverPort;
        User_Fields.set_userIp(Local_ip.get());



        display.reset();
        display.add("PLEASE ENTER YOUR NAME:", TextBlock.TITLE);
        display.show();
//        temporary_userName = name_request();
        temporary_userName = "t26x0";
        update_userInfo(temporary_userName);

//        display.reset();
//        display.add("ENTER SERVER IP:", TextBlock.TITLE);
//        display.show();
//        temporary_serverIp = serverIp_request();
        temporary_serverIp = "127.0.0.1";
        update_userInfo(temporary_userName, temporary_serverIp);

//        display.reset();
//        display.add("ENTER SERVER PORT:", TextBlock.TITLE);
//        display.show();
//        temporary_serverPort = serverPort_request();
        temporary_serverPort = 8081;

        update_userInfo(temporary_userName, temporary_serverIp, temporary_serverPort);

        display.reset();
        display.add("...connection to the server...", TextBlock.CONTENT);
        display.show();


        try {
            connectingToServer(get_serverIp(), get_serverPort());
            System.out.println("Success connection");
            return true;
        } catch (IOException e) {
//            // TODO logging
            return false;
        }
    }
}


class Begin {
    public static void main(String[] args) throws IOException {
        Client client = new Client();

        if (!client.startConnect()) {
            client.display.add("This server is currently unavailable", TextBlock.CONTENT);
            client.display.add("   Try connecting later", TextBlock.CONTENT);
            client.display.add("   or try connected to another server", TextBlock.CONTENT);
            client.display.show();
        }
        client.keyboard_handler();

    }

}