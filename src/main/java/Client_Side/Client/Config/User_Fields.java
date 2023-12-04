package Client_Side.Client.Config;

import Client_Side.Display.Config.Display_Config;
import Client_Side.Display.Config.Display_Const;
import Utils.MessageForm;

import java.util.ArrayList;
import java.util.List;

public class User_Fields {

    private static String default_userName = "...";
    private static String default_userIp = "...";
    private static String default_serverIp = "...";
    private static int default_serverPort = 0;
    protected static String default_userMessage = "...joined the server...";

    protected static final int MAX_NAME_LENGTH = 13;
    private static String userName = default_userName;
    private static String userIp = default_userIp;
    private static String serverIp = default_serverIp;
    private static int serverPort = default_serverPort;

    protected static List<String> allMessages = new ArrayList<>();

    protected User_Fields() {
        if (allMessages.size() != Display_Const.messageLines_size) {
            fillUp_allMessages();
        }
    }

    private void fillUp_allMessages() {
        for (int i = 0; i < Display_Const.messageLines_size; i++) {
            allMessages.add(" ");
        }
    }

    protected static void addMessage(MessageForm messageForm) {

        for (int i = 1; i < allMessages.size(); i++) {
            allMessages.set(i - 1, allMessages.get(i));
        }
        String message = messageForm.get_clientName() + ": " + messageForm.get_textMessage();
        allMessages.set(allMessages.size() - 1, message);
    }

    public boolean has_userName() {
        return !userName.equals(default_userName);
    }

    public boolean has_serverIp() {
        return !serverIp.equals(default_serverIp);
    }

    public boolean has_serverPort() {
        return !(serverPort == default_serverPort);
    }

    public static void set_userIp(String ip) {
        userIp = ip;
    }

    public static void set_userName(String name) {
        userName = name;
        update_commandSwitcher();
    }

    public static void set_serverIp(String ip) {
        serverIp = ip;
        update_commandSwitcher();
    }

    public static void set_serverPort(int port) {
        serverPort = port;
        update_commandSwitcher();
    }

    private static void update_commandSwitcher() {
        boolean result = gotAllData();
        Display_Config.set_commandVisibility(result);
    }

    private static boolean gotAllData() {
        return (!userName.equals("...") &&
                !serverIp.equals(("...")) &&
                serverPort != 0);
    }

    public static String get_userIp() {
        return userIp;
    }

    public static String get_userName() {
        return userName;
    }

    public static String get_serverIp() {
        return serverIp;
    }

    public static int get_serverPort() {
        return serverPort;
    }

    public static String get_default_userMessage() {
        return default_userMessage;
    }
}
