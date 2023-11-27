package com.ClientSide.Interface;


import java.util.Arrays;

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

    private void generalRendering() {

        for (int y = 0; y < frameSize_y; y++) {
            for (int x = 0; x < frameSize_x; x++) {
                drawFrame(x, y);
            }
            System.out.println();
        }
    }

    private void drawFrame(int x, int y) {
        // painting the top line
        if (y == 0) {
            System.out.print(frameSymbol_x);
        }
        // painting the down line
        if (y == frameSize_y - 1) {
            System.out.print(frameSymbol_x);

        }
        // painting the left line
        if (x == 0 && y != 0 && y != frameSize_y - 1) {
            System.out.print(frameSymbol_y);
        }
        // painting the right line
        if (x == frameSize_x - 1 && y != 0 && y != frameSize_y - 1) {
            System.out.print(frameSymbol_y);
        }
        // painting empty space in the center
        if (!(y == 0 || y == frameSize_y - 1)) {
            spaceCount++;
            System.out.print(" ");
        }
        //

        if (x == frameSize_x - 1 && y == frameSize_y - 1) {
            System.out.println();
        }
    }

    private void drawSpace() {
        for (int i = 0; i < spaceCount; i++) {
            System.out.print(" ");
        }
    }
}

abstract class Config {

    protected final String frameSymbol_x = "_";
    protected final String frameSymbol_y = "|";
    protected final int frameSize_x = 60;
    protected final int frameSize_y = 15;
    protected int spaceCount = 0;
    protected final int[][] frameLocation_top = new int[frameSize_x][2];
    protected final int[][] frameLocation_down = new int[frameSize_x][2];

    public Config() {
        setHorizontal_line();
        System.out.println(Arrays.deepToString(frameLocation_top));
        System.out.println(Arrays.deepToString(frameLocation_down));
    }

    private void setHorizontal_line() {
        for (int x = 0; x < frameSize_x; x++) {
            for (int y = 0; y < 2; y++) {
                if (y == 0) {
                    frameLocation_top[x][y] = x;
                    frameLocation_down[x][y] = x;
                } else {
                    frameLocation_down[x][y] = frameSize_y - 1;
                }
            }
        }
    }
}
