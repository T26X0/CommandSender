package com.User_Interface;


import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Display extends Display_Config {

    private static int countLines = 4;

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
        int x;
        int y;
        switch (text) {
            case IS_TITLE:
                x = getX_center_for(str);
                y = headline_Y_Position;
                add(x, y, str);
                resetLineCounter();
                break;
            case NOT_TITLE:
                x = getX_center_for(str);
                y = (headline_Y_Position + 1) + countLines;

                if (y >= maxPermissible_y) {
                    resetLineCounter();
                    y = (headline_Y_Position + 1) + countLines;
                }

                Map<String, String> StringWithCoordinates = prepareToInsertInMap(x, countLines, str);
                addToDisplay(StringWithCoordinates);
                countLines++;
                break;
        }
    }

    public void add(int x, int y, String str) {
        Map<String, String> StringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToDisplay(StringWithCoordinates);
    }

    public void replace(String str) {
        countLines--;
        add(str, Text.NOT_TITLE);
    }

    public void reset() {
        updateDisplay();
    }

    private void resetLineCounter() {
        countLines = 4;
    }


    public void set_userName(String name) {
        userName = name;
    }

    public String get_userName() {
        return userName;
    }
}
