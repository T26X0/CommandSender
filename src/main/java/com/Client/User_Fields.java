package com.Client;

public class User_Fields {
    protected static final int MAX_NAME_LENGTH = 12;
    private static String userName = "...";
    private static String userIp = "...";
    private static String serverIp = "...";
    private static int serverPort = 0;

    public static void set_UserName(String name) {
        userName = name;
    }
    public static void set_UserIp(String ip) {
        userIp = ip;
    }
    public static void set_ServerIp(String ip) {
        serverIp = ip;
    }
    public static void set_ServerPort(int port) {
        serverPort = port;
    }

    public static String get_UserIp() {
        return userIp;
    }
    public static String get_UserName() {
        return userName;
    }
    public static String get_ServerIp() {
        return serverIp;
    }
    public static int get_ServerPort() {
        return serverPort;
    }


}
