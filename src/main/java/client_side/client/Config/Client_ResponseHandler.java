package client_side.client.Config;

import client_side.client.Client;
import utils.MessageForm;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client_ResponseHandler extends Client implements Runnable {

    Socket socket;

    public Client_ResponseHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (Scanner input = new Scanner(socket.getInputStream())) {
            while (true) {
                String fromServer = input.nextLine();
                MessageForm messageForm = new MessageForm(fromServer);
                addMessage(messageForm);
                display.update();
                display.show();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
