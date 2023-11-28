package com.Line_Interface;

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

    public void show() {
        for (int y = 0; y < SIZE_DISPLAY_Y; y++) {
            for (int x = 0; x < SIZE_DISPLAY_X; x++) {
                String coordinates = getCoordinates(x, y);
                System.out.print(working_display.get(coordinates));
            }
            System.out.println();
        }
        resetLineCounter();
    }

    public void add(String str, Text text) {
        int x = getX_center_for(str);
        int y = 0;

        Map<String, String> stringWithCoordinates;

        if (text != Text.TITLE) {

            if (text == Text.CONTENT) {
                y = (headline_Y_Position + 1) + location_y_linePrint_notTitle;
            }
            if (text == Text.ERROR) {
                y = ((Math.round((float) SIZE_DISPLAY_Y / 2) - 5) + location_y_linePrint_notTitle);
            }
            if (text == Text.SERVER_IP) {
                x = location_serverIp[X_POINT];
                y = location_serverIp[Y_POINT];
            }
            if (text == Text.SERVE_PORT) {
                x = location_serverPort[X_POINT];
                y = location_serverPort[Y_POINT];
            }
            stringWithCoordinates = prepareToInsertInMap(x, y, str);
            addToDisplay(stringWithCoordinates);
            location_y_linePrint_notTitle++;

        } else {
            y = headline_Y_Position;
            add(x, y, str);
            resetLineCounter();
        }

    }

    /**
     * this method simply prints on top of the line above the current one
     * @param str String
     */
    public void replace(String str) {
        location_y_linePrint_notTitle--;
        add(str, Text.CONTENT);
    }

    public void reset() {
        updateDisplay();
    }

    public void set_userName(String name) {
        userName = name;
    }

    public String get_userName() {
        return userName;
    }

    private void add(int x, int y, String str) {
        Map<String, String> StringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToDisplay(StringWithCoordinates);
    }
}
