package Client.Line_Sender;


import Client.Line_Sender.Config.Local_ip;
import Client.Line_Sender.Config.User_Fields;
import Client.User_Display.Config.TextBlock;
import Client.User_Display.User_Display;
import Utils.Connectable;
import Utils.UserData;
import Utils.Validator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Line_Sender extends User_Fields implements Connectable {
    private UserData messageConstructor;
    private PrintWriter output;
    private final Scanner scanner = new Scanner(System.in);
    User_Display display;
    public Line_Sender() throws IOException {

        display = new User_Display();
        display.show_logo();

        User_Fields.set_userIp(Local_ip.get());

        display.reset();
        display.add("PLEASE ENTER YOUR NAME:", TextBlock.TITLE);
        display.show();

        registerClient();

        display.reset();
        display.add("ENTER SERVER IP:", TextBlock.TITLE);
        display.show();

        init_Server_Ip();

        display.reset();
        display.add("ENTER SERVER PORT:", TextBlock.TITLE);
        display.show();

        init_Server_Port();

        display.reset();
        display.add("...connection to the server...", TextBlock.CONTENT);
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
    public void init_Server_Ip() throws IOException {


        String ip = scanner.nextLine();
        if (Validator.isValid(ip)) {
            User_Fields.set_serverIp(ip);
        } else {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT IP", TextBlock.CONTENT);
            display.add("TRY AGAIN", TextBlock.CONTENT);
            display.show();
            init_Server_Ip();
        }
    }

    public void init_Server_Port() throws IOException {

        try {
            int port = Integer.parseInt(scanner.nextLine());

            if (Validator.isValid(port)) {
                User_Fields.set_serverPort(port);
            } else {
                // TODO logging

                display.add("YOU ENTERED AN INCORRECT PORT", TextBlock.CONTENT);
                display.add("TRY AGAIN", TextBlock.CONTENT);
                display.show();
                init_Server_Port();
            }
        } catch (NumberFormatException e) {
            // TODO logging

            display.add("YOU ENTERED AN INCORRECT PORT", TextBlock.CONTENT);
            display.add("TRY AGAIN", TextBlock.CONTENT);
            display.show();
            init_Server_Port();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void registerClient() throws IOException {

        String name = scanner.nextLine();

        if (!set_Name(name)) {

            display.add("Your indicate not valid name:", TextBlock.CONTENT);
            display.add("* Name can't be empty", TextBlock.CONTENT);
            display.add("* Name must contain no more than 11 characters and 1 word", TextBlock.CONTENT);
            display.add("* you can use \"_\" or \"-\"", TextBlock.CONTENT);
            display.show();
            registerClient();
        }  else {
            User_Fields.set_userName(name);
            messageConstructor = new UserData(get_userIp(), get_userName());
        }
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
        User_Fields.set_userName(name);
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
            connectingToServer(get_serverIp(), get_serverPort());
            System.out.println("Success connection");
            keyboardTapping();
        } catch (IOException e) {
            // TODO logging
            System.out.println("This server is currently unavailable");
            System.out.println("   Try connecting later");
            System.out.println("   or try connected to another server");

            try {
                init_Server_Ip();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}


class Begin {
    public static void main(String[] args) throws IOException {
        Line_Sender client = new Line_Sender();
        client.startConnect();
    }
}