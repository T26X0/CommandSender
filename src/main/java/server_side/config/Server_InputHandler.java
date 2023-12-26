package server_side.config;

import server_side.Server;
import utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Server_InputHandler extends Server implements Runnable {

    Socket socket;

    public Server_InputHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String nextLine = scanner.nextLine();

                MessageForm messageForm = new MessageForm("127.0.0.1", "server");
                String message = messageForm.prepareMessage_toSend("some recipient", nextLine);
                send_message(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
