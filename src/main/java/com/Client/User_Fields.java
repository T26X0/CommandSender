package com.Client;

public class User_Fields {
    protected static final int MAX_NAME_LENGTH = 12;
    private static String userName = "...";
    private static String userIp = "...";
    private static String serverIp = "...";
    private static int serverPort = 0;

    public static void set_userName(String name) {
        userName = name;
    }
    public static void set_userIp(String ip) {
        userIp = ip;
    }
    public static void set_serverIp(String ip) {
        serverIp = ip;
    }
    public static void set_serverPort(int port) {
        serverPort = port;
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
