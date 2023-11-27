package com.User_Interface;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Display_Config extends Display_Const {

    protected final int[][] lineLocation_top = new int[displaySize_x][2];
    protected final int[][] lineLocation_medium = new int[displaySize_x][2];
    protected final int[][] lineLocation_down = new int[displaySize_x][2];
    protected final int[][] lineLocation_left = new int[displaySize_y - 1][2];
    protected final int[][] lineLocation_right = new int[displaySize_y - 1][2];
    protected Map<String, String> working_display;

    public Display_Config() {
        resetDisplay();
    }

    protected void resetDisplay() {
        initHorizontal_line();
        initVertical_line();
        initDisplay();
        set_in_display();
        fillEmptiness();
        setTitle();
    }

    private void setTitle() {
        Map<String, String> title_map = prepareToInsertInMap(getX_for_insert(APP_TITLE), 0, APP_TITLE);
        addToMap(title_map);
    }

    private void initHorizontal_line() {
        for (int queue = 0; queue < displaySize_x; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        lineLocation_top[queue][X_POINT] = queue;
                        lineLocation_down[queue][X_POINT] = queue;
                        lineLocation_medium[queue][X_POINT] = queue;
                        break;
                    case Y_POINT:
                        lineLocation_top[queue][Y_POINT] = 0;
                        lineLocation_down[queue][Y_POINT] = displaySize_y - 1;
                        lineLocation_medium[queue][Y_POINT] = notification_Y_Position + 1;
                        break;
                }
            }
        }
    }

    private void initVertical_line() {
        for (int queue = 0; queue < displaySize_y - 1; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        lineLocation_left[queue][X_POINT] = 0;
                        lineLocation_right[queue][X_POINT] = displaySize_x - 1;
                        break;
                    case Y_POINT:
                        lineLocation_left[queue][Y_POINT] = queue + 1;
                        lineLocation_right[queue][Y_POINT] = queue + 1;
                        break;
                }
            }
        }
    }

    private void initDisplay() {
        working_display = new HashMap<>();
    }

    private void set_in_display() {

        for (int[] coordinates_X_Y : lineLocation_left) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : lineLocation_right) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : lineLocation_medium) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }

        for (int[] coordinates_X_Y : lineLocation_top) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }

        for (int[] coordinates_X_Y : lineLocation_down) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }


    }

    private void fillEmptiness() {
        for (int x = 0; x < displaySize_x; x++) {
            for (int y = 0; y < displaySize_y; y++) {
                String coordinates = getCoordinates(x, y);
                working_display.putIfAbsent(coordinates, " ");
            }
        }
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

    protected void addToMap(Map<String, String> map) {
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

    protected static int getX_for_insert(String str) {
        return (displaySize_x / 2) - (Math.round((float) str.length() / 2));
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