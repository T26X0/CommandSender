package Server_Side.Config;

import Server_Side.Server;
import Utils.MessageForm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageHandler extends Server implements Runnable {

    private final String IS_COMMAND = "command";
    private final String IS_MESSAGE = "message";
    Socket socket;
    MessageForm messageForm;
    PrintWriter output;

    public MessageHandler(Socket socket, MessageForm messageForm) {
        super();

        this.socket = socket;
        this.messageForm = messageForm;
        try {
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <h3>The method checks the user's registration.
     * The user sends an empty message if he is not registered</h3>
     *
     * @return boolean
     */
    private boolean userIsRegistered() {
        return !(messageForm.get_textMessage().isEmpty());
    }

    /**
     * <h3>The method checks the message for the commands</h3>
     * <pre>
     *     Example:
     *     "!add" - command to the adding friend
     *     "Hello" - message
     * </pre>
     */
    private String define_message() {

        if (messageForm.get_textMessage().startsWith("!")) {
            return IS_COMMAND;
        } else {
            return IS_MESSAGE;
        }
    }

    private void execute_command() {

    }

    private void send_message() {

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

    @Override
    public void run() {
        if (!userIsRegistered()) {
            registrationUser(socket, messageForm);
        } else {
            switch (define_message()) {
                case IS_COMMAND:
                    execute_command();
                    break;
                case IS_MESSAGE:
                    send_message();
                    break;
            }
        }
    }
}
