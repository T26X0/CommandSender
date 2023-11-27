package com.ClientSide.Interface;


import java.util.HashMap;
import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Interface extends Config {
    public static void main(String[] args) {
        User_Interface app = new User_Interface();
//        app.generalRendering();
//        System.out.println(Arrays.deepToString(app.frameLocation_x));
    }
}


abstract class Config {

    private final int X_POINT = 0;
    private final int Y_POINT = 1;

    protected final String frameSymbol_x = "_";
    protected final String frameSymbol_y = "|";
    protected final int frameSize_x = 60;
    protected final int frameSize_y = 15;
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
        show_display();
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

        for (int[] ints : frameLocation_left) {
            String x = String.valueOf(ints[X_POINT]);
            String y = String.valueOf(ints[Y_POINT]);
            String coordinates = x + ", " + y;
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] ints : frameLocation_right) {
            String x = String.valueOf(ints[X_POINT]);
            String y = String.valueOf(ints[Y_POINT]);
            String coordinates = x + ", " + y;
            working_display.putIfAbsent(coordinates, frameSymbol_y);
        }

        for (int[] ints : frameLocation_top) {
            String x = String.valueOf(ints[X_POINT]);
            String y = String.valueOf(ints[Y_POINT]);
            String coordinates = x + ", " + y;
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }

        for (int[] ints : frameLocation_down) {
            String x = String.valueOf(ints[X_POINT]);
            String y = String.valueOf(ints[Y_POINT]);
            String coordinates = x + ", " + y;
            working_display.putIfAbsent(coordinates, frameSymbol_x);
        }
    }

    private void fillEmptiness() {
        for (int x = 0; x < frameSize_x; x++) {
            for (int y = 0; y < frameSize_y; y++) {
                String coordinates = x + ", " + y;
                working_display.putIfAbsent(coordinates, " ");
            }
        }
    }

    private void show_display() {
        for (int y = 0; y < frameSize_y; y++) {
            for (int x = 0; x < frameSize_x; x++) {
                String coordinates = x + ", " + y;
                System.out.print(working_display.get(coordinates));
            }
            System.out.println();
        }
    }
}
