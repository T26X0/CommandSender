package com.Line_Interface;

import com.Client.User_Fields;

class Display_Const extends User_Fields {
    protected static final int X_POINT = 0;
    protected static final int Y_POINT = 1;
    protected static String APP_TITLE;
    protected static String frameSymbol_x;
    protected static String frameSymbol_y;

    protected static int SIZE_DISPLAY_X;
    protected static int SIZE_DISPLAY_Y;

    protected static int location_Y_headline;


    protected static int[] location_X_textBlock_TITLE;
    protected static int[] location_X_textBlock_NOTIFICATION;
    protected static int[] location_X_textBlock_CONTENT;
    protected static int[] location_X_Y_block_serverIp;
    protected static String title_X_Y_block_serverIp;
    protected static int[] location_X_Y_block_serverPort;
    protected static String title_X_Y_block_serverPort;
    protected static int[] location_X_Y_userName;
    protected static int SIZE_NAME_BOX;

    static {
        set_appValues();
        set_displayValues();
    }

    private static void set_appValues() {
        APP_TITLE = "LINE SENDER:";
        frameSymbol_x = "_";
        frameSymbol_y = "|";
        SIZE_DISPLAY_X = 70;
        SIZE_DISPLAY_Y = 20;
    }

    /**
     * <h3>I don’t specify the Y coordinate for:</h3>
     * <pre>
     *     location_X_line_TITLE
     *     location_X_line_NOTIFICATION
     *     location_X_line_CONTENT
     * </pre>
     * because when adding text to the screen
     * centering occurs relative to the text content
     * <pre></pre>
     * <h3>Full X, Y coordinates to add server IP and server PORT</h3>
     */
    private static void set_displayValues() {
        location_Y_headline = 1;
        SIZE_NAME_BOX = 14;

        location_X_textBlock_TITLE = new int[] {4};
        location_X_textBlock_NOTIFICATION = new int[] {5, 6};
        location_X_textBlock_CONTENT = new int[] {10, 11, 12, 13};

        location_X_Y_block_serverIp = new int[] {1, 1};
        title_X_Y_block_serverIp = "server ip: ";

        location_X_Y_block_serverPort = new int[] {Math.round((float) SIZE_DISPLAY_X) / 2, 1};
        title_X_Y_block_serverPort = "server port: ";

        location_X_Y_userName = new int[] {SIZE_DISPLAY_X - SIZE_NAME_BOX + 2, 1};
    }

    protected static int getX_center_for(String str) {
        return (SIZE_DISPLAY_X / 2) - (Math.round((float) str.length() / 2));
    }
}