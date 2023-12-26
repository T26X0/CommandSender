package server_side;

import client_side.client.Config.User_Fields;
import server_side.config.Server_InputHandler;
import server_side.config.Server_ResponseHandler;
import utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    PrintWriter output;
    public static Map<Socket, MessageForm> allUser = new HashMap<>();

    public void startConnect() {
        try {
            serverSocket = new ServerSocket(8080);


            while (true) {
                System.out.println("I'm wait new client");

                // receives client socket
                socket = serverSocket.accept();

                Thread thread_response = new Thread(new Server_ResponseHandler(socket));
                Thread thread_input = new Thread(new Server_InputHandler(socket));
                thread_response.start();
                thread_input.start();

            }
        } catch (IOException e) {
            // TODO logging
        }
    }

    /**
     * Adds a user to the shared database
     *
     * @param socket   Socket
     * @param messageForm UserData
     */
    public static void registrationUser(Socket socket, MessageForm messageForm) {
        allUser.put(
                socket,
                messageForm);
        // TODO logging
    }

    /**
     * <h3>The method checks the user's registration.
     * The user sends an empty message if he is not registered</h3>
     *
     * @return boolean
     */
    protected static boolean userIsRegistered(MessageForm messageForm) {
        return !messageForm.get_textMessage()
                .equals(User_Fields.get_default_userMessage());
    }

    protected void send_message(String message_fromUser) {

        List<Socket> allUser_sockets = new ArrayList<>(allUser.keySet());

        for (Socket soc : allUser_sockets) {
            try {
                output = new PrintWriter(soc.getOutputStream());
                output.println(message_fromUser);
                output.flush();
            } catch (IOException e) {
                MessageForm userData = allUser.get(soc);
                // TODO logging ("user"  + userData + "didn't receive the message)
            }
        }

        MessageForm userData = new MessageForm(message_fromUser);
//         temporary solution only for demonstration!
        System.out.println("____________");
        System.out.println("New message:");
        System.out.println("  from: " + userData.get_clientIp());
        System.out.println("  name: " + userData.get_clientName());
        System.out.println("  text: " + userData.get_textMessage());
    }
}


class Begin {

    /**
     * <h3>Start of the program</h3>
     * <h3>Entry point for the Client</h3>
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.startConnect();
    }
}
