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

        if (text != Text.IS_TITLE) {

            if (text == Text.CONTENT) {
                y = (headline_Y_Position + 1) + linePrint_notTitle;
            }
            if (text == Text.IS_ERROR) {
                y = ((Math.round((float) SIZE_DISPLAY_Y / 2) - 4) + linePrint_notTitle);
            }
            stringWithCoordinates = prepareToInsertInMap(x, y, str);
            addToDisplay(stringWithCoordinates);
            linePrint_notTitle++;

        } else {
            y = headline_Y_Position;
            add(x, y, str);
            resetLineCounter();
        }

    }

    public void replace(String str) {
        linePrint_notTitle--;
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
