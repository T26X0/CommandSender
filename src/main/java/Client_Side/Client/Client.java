package Client_Side.Client;


import Client_Side.Client.Config.Client_InputHandler;
import Client_Side.Client.Config.Client_ResponseHandler;
import Client_Side.Client.Config.Local_ip;
import Client_Side.Client.Config.User_Fields;
import Client_Side.Display.Config.Blocks_Exception;
import Client_Side.Display.Config.Blocks_Text;
import Client_Side.Display.User_Display;
import Utils.MessageForm;
import Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends User_Fields {

    private Socket socket;

    private boolean connection_status;
    protected MessageForm messageForm;
    protected User_Display display;

    public Client() {
        display = new User_Display();
        if (!(has_userName() && has_serverIp() && has_serverPort())) {
            User_Fields.set_userIp(Local_ip.get());

            User_Fields.set_userName("t26x0");
            User_Fields.set_serverPort(8081);
            User_Fields.set_serverIp("127.0.0.1");
//            client_registration();
        }
        messageForm = new MessageForm(get_userIp(), get_userName());
    }

    /**
     * <h3>Initializes socket for connection to the Server</h3>
     * <h4>As well as channels for receiving and sending information to the server</h4>
     * Client sends on Server userIp so that the server to add
     * the user socket to the database active users
     */
    public void connecting_toServer() {
        display.update();
        display.show();
        try {
            socket = new Socket(get_serverIp(), get_serverPort());
            PrintWriter output = new PrintWriter(socket.getOutputStream());

            String message = messageForm.prepareMessage_toSend("", default_userMessage);
            output.println(message);
            output.flush();

            System.out.println("Success connection");
            connection_status = true;

            Thread thread_response = new Thread(new Client_ResponseHandler(socket));
            Thread thread_input = new Thread(new Client_InputHandler(socket));
            thread_response.start();
            thread_input.start();

        } catch (IOException e) {
            // TODO logging
            show_error(Blocks_Exception.SERVER_UNAVAILABLE);
            connection_status = false;
        }
    }

    public boolean get_connectionStatus() {
        return connection_status;
    }

    public void show_error(Blocks_Exception exBlock) {
        String[] exceptionLines = exBlock.getExceptionLines();

        for (String str : exceptionLines) {
            display.add(str, Blocks_Text.CONTENT);
        }
        display.show();
    }

    private void client_registration() {
        display.show_logo();

        String temporary_userName;
        String temporary_serverIp;
        int temporary_serverPort;
        User_Fields.set_userIp(Local_ip.get());

        display.update();
        display.add("PLEASE ENTER YOUR NAME:", Blocks_Text.TITLE);
        display.show();
        temporary_userName = name_request();
        update_userInfo(temporary_userName);

        display.update();
        display.add("ENTER SERVER IP:", Blocks_Text.TITLE);
        display.show();
        temporary_serverIp = serverIp_request();
        update_userInfo(temporary_userName, temporary_serverIp);

        display.update();
        display.add("ENTER SERVER PORT:", Blocks_Text.TITLE);
        display.show();
        temporary_serverPort = serverPort_request();

        update_userInfo(temporary_userName, temporary_serverIp, temporary_serverPort);
    }

    private String name_request() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        while (!Validator.userName_isValid(name)) {


            display.add("Your indicate not valid name:", Blocks_Text.CONTENT);
            display.add("* Name can't be empty", Blocks_Text.CONTENT);
            display.add("* Name must contain no more than 11 characters and 1 word", Blocks_Text.CONTENT);
            display.add("* you can use \"_\" or \"-\"", Blocks_Text.CONTENT);
            display.show();
            name = scanner.nextLine();
        }
        return name;
    }

    private String serverIp_request() {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();

        while (!Validator.ip_isValid(ip)) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT IP", Blocks_Text.CONTENT);
            display.add("TRY AGAIN", Blocks_Text.CONTENT);
            display.show();
            ip = scanner.nextLine();
        }
        return ip;
    }

    private int serverPort_request() {

        int port;

        try {
            Scanner scanner = new Scanner(System.in);
            port = Integer.parseInt(scanner.nextLine());

            while (!Validator.port_isValid(port)) {
                // TODO logging

                display.add("YOU ENTERED AN INCORRECT PORT", Blocks_Text.CONTENT);
                display.add("TRY AGAIN", Blocks_Text.CONTENT);
                display.show();
                port = Integer.parseInt(scanner.nextLine());
            }


        } catch (NumberFormatException e) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT PORT", Blocks_Text.CONTENT);
            display.add("TRY AGAIN", Blocks_Text.CONTENT);
            display.show();
            port = serverPort_request();
        }
        return port;
    }

    private void update_userInfo(String name, String ip, int port) {
        User_Fields.set_userName(name);
        User_Fields.set_serverIp(ip);
        User_Fields.set_serverPort(port);
        messageForm = new MessageForm(get_userIp(), name);
    }

    private void update_userInfo(String name, String ip) {
        update_userInfo(name, ip, get_serverPort());
    }

    private void update_userInfo(String name) {
        update_userInfo(name, get_serverIp(), get_serverPort());
    }
}


class Begin {
    public static void main(String[] args) {
        Client client = new Client();
        client.connecting_toServer();

        if (!client.get_connectionStatus()) {
            client.show_error(Blocks_Exception.SERVER_UNAVAILABLE);
        }
    }
}
