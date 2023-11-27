package com.User_Interface;


import java.util.*;

public class Display_Config extends Display_Const {

    protected final int[][] location_top_line_Frame = new int[SIZE_DISPLAY_X][2];
    protected final int[][] location_medium_lineFrame = new int[SIZE_DISPLAY_X][2];
    protected final int[][] location_down_lineFrame = new int[SIZE_DISPLAY_X][2];
    protected final int[][] location_left_lineFrame = new int[SIZE_DISPLAY_Y - 1][2];
    protected final int[][] location_right_lineFrame = new int[SIZE_DISPLAY_Y - 1][2];
    protected final int[][] location_line_nameBox = new int[headline_Y_Position + 1][2];
    protected Map<String, String> working_display;

    public Display_Config() {
        updateDisplay();
    }

    protected void updateDisplay() {
        init_Display();
        init_Horizontal_line();
        init_Vertical_line();

        init_NameBox();
        init_userName();

        set_in_display();
        fillEmptiness();
        setTitle();
    }

    private void setTitle() {
        Map<String, String> title_map = prepareToInsertInMap(0, SIZE_DISPLAY_Y - 1, APP_TITLE);
        addToDisplay(title_map);
    }

    private void init_userName() {
        Map<String, String> userName_map = prepareToInsertInMap(
                SIZE_DISPLAY_X - SIZE_NAME_BOX + 1,
                headline_Y_Position,
                userName);
        addToDisplay(userName_map);
    }

    private void init_Horizontal_line() {
        for (int queue = 0; queue < SIZE_DISPLAY_X; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        location_top_line_Frame[queue][X_POINT] = queue;
                        location_down_lineFrame[queue][X_POINT] = queue;
                        location_medium_lineFrame[queue][X_POINT] = queue;
                        break;
                    case Y_POINT:
                        location_top_line_Frame[queue][Y_POINT] = 0;
                        location_down_lineFrame[queue][Y_POINT] = SIZE_DISPLAY_Y - 1;
                        location_medium_lineFrame[queue][Y_POINT] = headline_Y_Position + 1;
                        break;
                }
            }
        }
    }

    private void init_Vertical_line() {
        for (int queue = 0; queue < SIZE_DISPLAY_Y - 1; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        location_left_lineFrame[queue][X_POINT] = 0;
                        location_right_lineFrame[queue][X_POINT] = SIZE_DISPLAY_X - 1;
                        break;
                    case Y_POINT:
                        location_left_lineFrame[queue][Y_POINT] = queue + 1;
                        location_right_lineFrame[queue][Y_POINT] = queue + 1;
                        break;
                }
            }
        }
    }

    private void init_NameBox() {
        for (int queue = 0; queue < headline_Y_Position + 1; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {
                switch (cursor) {
                    case X_POINT:
                        location_line_nameBox[queue][X_POINT] = SIZE_DISPLAY_X - SIZE_NAME_BOX;
                        break;
                    case Y_POINT:
                        location_line_nameBox[queue][Y_POINT] = queue + 1;
                        break;
                }
            }
        }
    }

    private void init_Display() {
        working_display = new HashMap<>();
    }

    private void fillEmptiness() {
        for (int x = 0; x < SIZE_DISPLAY_X; x++) {
            for (int y = 0; y < SIZE_DISPLAY_Y; y++) {
                String coordinates = getCoordinates(x, y);
                working_display.putIfAbsent(coordinates, " ");
            }
        }
    }

    private void set_in_display() {

        for (int[] coordinates_X_Y : location_top_line_Frame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.put(coordinates, frameSymbol_x);
        }

        for (int[] coordinates_X_Y : location_medium_lineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.put(coordinates, frameSymbol_x);
        }

        for (int[] coordinates_X_Y : location_left_lineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.put(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : location_right_lineFrame) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.put(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : location_line_nameBox) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.put(coordinates, frameSymbol_y);
        }



//        for (int[] coordinates_X_Y : lineLocation_down) {
//            String x = String.valueOf(coordinates_X_Y[X_POINT]);
//            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
//            String coordinates = getCoordinates(x, y);
//            working_display.putIfAbsent(coordinates, frameSymbol_x);
//        }


    }

    protected Map<String, String> prepareToInsertInMap(int x, int y, String str) {
        String coordinatesForKey;
        String letterForValue;
        Map<String, String> resultMap = new HashMap<>();

        char[] numbersOfChars = str.toCharArray();

        for (int i = 0; i < numbersOfChars.length; i++) {
            coordinatesForKey = getCoordinates(x + i, y);
            letterForValue = String.valueOf(numbersOfChars[i]);
            resultMap.put(coordinatesForKey, letterForValue);
        }
        return resultMap;
    }

    protected void addToDisplay(Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        for (String key : keys) {
            String value = map.get(key);
            working_display.put(key, value);
        }
    }

    protected static String getCoordinates(int x, int y) {
        return x + ", " + y;
    }

    protected static String getCoordinates(String x, String y) {
        return x + ", " + y;
    }

    /**
     * This method serves for my tests
     * @param map Map
     */
    private void showMap(Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        for (String key : keys) {
            String value = map.get(key);
            System.out.println(key + ", " + value);
        }
    }
}