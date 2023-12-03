package Utils;

import Client_Side.Client.Config.User_Fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator extends User_Fields {

    static final String regExForIp = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}" +
            "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
    static final String regExForPort = "";

    public static boolean ip_isValid(String ip) {

        if (ip.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regExForIp);
        Matcher matcher = pattern.matcher(ip);


        return matcher.matches();
    }

    public static boolean port_isValid(int port) {
        return port >= 1024 && port <= 49151;
    }

    public static boolean userName_isValid(String userName) {

        int nameLength = userName.trim().split("[\\s]+").length;
        return !userName.isEmpty() && userName.length() <= MAX_NAME_LENGTH && nameLength <= 1;
    }
}