package com.User_Interface;


import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Display extends Display_Config {

    public User_Display() {
        super();
    }

    public void show_display() {
        for (int y = 0; y < displaySize_y; y++) {
            for (int x = 0; x < displaySize_x; x++) {
                String coordinates = getCoordinates(x, y);
                System.out.print(working_display.get(coordinates));
            }
            System.out.println();
        }
    }


    public void add_Element(String str) {
        int x = getX_for_insert(str);
        int y = notification_Y_Position;
        add_Element(x, y, str);
    }


    public void add_Element(int x, int y, String str) {
        Map<String, String> StringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToMap(StringWithCoordinates);
    }

    public void reset() {
        resetDisplay();
    }
}
