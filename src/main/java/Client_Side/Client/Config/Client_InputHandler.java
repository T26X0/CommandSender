package Client_Side.Client.Config;

import Client_Side.Client.Client;
import Utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client_InputHandler extends Client implements Runnable {

    Socket socket;
    PrintWriter output;
    Scanner scanner = new Scanner(System.in);

    public Client_InputHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String recipient;
        String textMessage;

        try {
            output = new PrintWriter(socket.getOutputStream());
            while (true) {

                textMessage = scanner.nextLine();

                String message = messageForm.prepareMessage_toSend("some recipient", textMessage);

                output.println(message);
                output.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
