package com.Client;

import com.Line_Interface.Display_Config;

public class User_Fields {
    protected static final int MAX_NAME_LENGTH = 12;
    private static String userName = "...";
    private static String userIp = "...";
    private static String serverIp = "...";
    private static int serverPort = 0;

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
}
