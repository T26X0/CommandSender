package Server_Side.Config;

import Client_Side.Client.Config.User_Fields;
import Server_Side.Server;
import Utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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

                System.out.println("?????");
                System.out.println(userIsRegistered(messageForm));
                System.out.println("?????");

                if (!userIsRegistered(messageForm)) {
                    registrationUser(socket, messageForm);
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

    /**
     * <h3>The method checks the user's registration.
     * The user sends an empty message if he is not registered</h3>
     *
     * @return boolean
     */
    private static boolean userIsRegistered(MessageForm messageForm) {
        return !messageForm.get_textMessage()
                .equals(User_Fields.get_default_userMessage());
    }


    private void send_message(String message_fromUser) {

        List<Socket> allUser_sockets = new ArrayList<>(allUser.keySet());

        System.out.println(allUser);
        System.out.println(allUser_sockets);

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
