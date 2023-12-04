package Server_Side;

import Server_Side.Config.Server_InputHandler;
import Server_Side.Config.Server_ResponseHandler;
import Utils.MessageForm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    public static Map<Socket, MessageForm> allUser = new HashMap<>();

    public void startConnect() {
        try {
            serverSocket = new ServerSocket(8081);

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
