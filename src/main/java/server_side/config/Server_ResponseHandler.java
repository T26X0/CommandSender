package server_side.config;

import server_side.Server;
import utils.MessageForm;

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
                String fromUser = input.nextLine();
                MessageForm messageForm = new MessageForm(fromUser);

                if (!userIsRegistered(messageForm)) {
                    registrationUser(socket, messageForm);
                    fromUser += "     Total users: " + allUser.size();
                }

                lastIp = messageForm.get_clientIp();
                lastName = messageForm.get_clientName();
                send_message(fromUser);
            }
        } catch (IOException e) {
            // TODO logging
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            // TODO logging
            System.out.println(lastName + "/" + lastIp + ": passed out");
        }
    }
}
