package Server_Side.Config;

import Server_Side.Server;
import Utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server_ResponseHandler extends Server implements Runnable {

    Socket socket;
    PrintWriter output;

    /**
     * <h3>A new monitoring of one user is created over the socket</h3>
     *
     * @param socket Socket
     */
    public Server_ResponseHandler(Socket socket) {
        // TODO logging
        System.out.println("Success connection");
        this.socket = socket;
        try {
            this.output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <h3>The method processes data that comes from the server,</h3>
     *
     * <pre>this can be:
     *      a command (if the message begins with "!")
     *      a message to the another user (without "!")</pre>
     */
    @Override
    public void run() {
        String lastIp = "None";
        String lastName = "None";


        try (Scanner input = new Scanner(socket.getInputStream())) {
            while (true) {
                System.out.println("1");
                String fromUser = input.nextLine();
                System.out.println("2");
                MessageForm messageForm = new MessageForm(fromUser);
                System.out.println("3");

                lastIp = messageForm.get_clientIp();
                lastName = messageForm.get_clientName();

                send_message(messageForm);
                System.out.println("4");
            }
        } catch (IOException e) {
            // TODO logging
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            // TODO logging
            System.out.println(lastName + "/" + lastIp + ": passed out");
        }
    }

    private void send_message(MessageForm messageForm) {

        String clientIp = messageForm.get_clientIp();
        String clientName = messageForm.get_clientName();
        String recipientIp = messageForm.get_recipientIp();
        String textMessage = messageForm.get_textMessage();

        MessageForm answered_messageForm = new MessageForm(clientIp, clientName);
        String message_toAnswer = answered_messageForm.prepareMessage_toSend(recipientIp, textMessage);

        output.println(message_toAnswer);
        output.flush();

        // temporary solution only for demonstration!
        System.out.println("____________");
        System.out.println("New message:");
        System.out.println("  from: " + clientIp);
        System.out.println("  name: " + clientName);
        System.out.println("  to: " + recipientIp);
        System.out.println("  text: " + textMessage);
    }
}
