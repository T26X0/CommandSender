package com.User_Interface;


import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Interface extends Config {

    public User_Interface(String serverIp, int serverPort, String userName) {

    }

    public void show_display() {
        for (int y = 0; y < frameSize_y; y++) {
            for (int x = 0; x < frameSize_x; x++) {
                String coordinates = getCoordinates(x, y);
                System.out.print(working_display.get(coordinates));
            }
            System.out.println();
        }
    }

    public void add_Element(int x, int y, String str) {
        Map<String, String> StringWithCoordinates = prepareToInsert(x, y, str);
        addToMap(StringWithCoordinates);
    }
}
