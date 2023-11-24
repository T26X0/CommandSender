package com.Instruments;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpLocal {
    static String userIp;

    public static String get() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();

            Pattern pattern = Pattern.compile("/(.*)");
            Matcher matcher = pattern.matcher(localHost.toString());

            while (matcher.find()) {
                userIp = matcher.group(1);
            }
        } catch (UnknownHostException e) {
            // TODO need to add logging
            userIp = "Ip not found";
        }
        return userIp;
    }
}
