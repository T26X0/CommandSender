package com.User_Interface;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Config {

    private final int X_POINT = 0;
    private final int Y_POINT = 1;
    protected final String frameSymbol_x = "_";
    protected final String frameSymbol_y = "|";
    protected final int frameSize_x = 70;
    protected final int frameSize_y = 20;
    protected final int[][] frameLocation_top = new int[frameSize_x][2];
    protected final int[][] frameLocation_down = new int[frameSize_x][2];
    protected final int[][] frameLocation_left = new int[frameSize_y - 1][2];
    protected final int[][] frameLocation_right = new int[frameSize_y - 1][2];
    protected Map<String, String> working_display = new HashMap<>();

    public Config() {
        setHorizontal_line();
        setVertical_line();
        set_frame();
        fillEmptiness();
    }

    private void setHorizontal_line() {
        for (int queue = 0; queue < frameSize_x; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        frameLocation_top[queue][X_POINT] = queue;
                        frameLocation_down[queue][X_POINT] = queue;
                        break;
                    case Y_POINT:
                        frameLocation_top[queue][Y_POINT] = 0;
                        frameLocation_down[queue][Y_POINT] = frameSize_y - 1;
                        break;
                }
            }
        }
    }

    private void setVertical_line() {
        for (int queue = 0; queue < frameSize_y - 1; queue++) {
            for (int cursor = 0; cursor < 2; cursor++) {

                switch (cursor) {
                    case X_POINT:
                        frameLocation_left[queue][X_POINT] = 0;
                        frameLocation_right[queue][X_POINT] = frameSize_x - 1;
                        break;
                    case Y_POINT:
                        frameLocation_left[queue][Y_POINT] = queue + 1;
                        frameLocation_right[queue][Y_POINT] = queue + 1;
                        break;
                }
            }
        }
    }

    private void set_frame() {

        for (int[] coordinates_X_Y : frameLocation_left) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : frameLocation_right) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] coordinates_X_Y : frameLocation_top) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }

        for (int[] coordinates_X_Y : frameLocation_down) {
            String x = String.valueOf(coordinates_X_Y[X_POINT]);
            String y = String.valueOf(coordinates_X_Y[Y_POINT]);
            String coordinates = getCoordinates(x, y);
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }
    }

    private void fillEmptiness() {
        for (int x = 0; x < frameSize_x; x++) {
            for (int y = 0; y < frameSize_y; y++) {
                String coordinates = getCoordinates(x, y);
                working_display.putIfAbsent(coordinates, " ");
            }
        }
    }

    protected Map<String, String> prepareToInsert(int x, int y, String str) {
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
        List<String> keys = new ArrayList<String>(map.keySet());
        for (String key : keys) {
            String value = map.get(key);

            if (working_display.get(key).equals(" ")) {
                working_display.put(key, value);
            }
        }
    }

    protected static String getCoordinates(int x, int y) {
        return x + ", " + y;
    }

    protected static String getCoordinates(String x, String y) {
        return x + ", " + y;
    }

}
