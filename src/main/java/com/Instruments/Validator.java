package com.Instruments;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static final String regExForIp = "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}" +
            "(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
    static final String regExForPort = "";

    public static boolean isValid(String ip) {

        if (ip.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(regExForIp);
        Matcher matcher = pattern.matcher(ip);


        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValid(int port) {
        return port >= 1024 && port <= 49151;
    }
}