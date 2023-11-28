package com.Line_Interface;

class Display_Const {
    protected static final int X_POINT = 0;
    protected static final int Y_POINT = 1;
    protected static final String APP_TITLE = "LINE SENDER:";
    protected static String userName = "";
    protected static final String frameSymbol_x = "_";
    protected static final String frameSymbol_y = "|";

    protected static final int SIZE_DISPLAY_X = 70;
    protected static final int SIZE_DISPLAY_Y = 20;
    protected static int SIZE_NAME_BOX = 14;
    protected static int headline_Y_Position = 1;
    protected static int location_y_linePrint_notTitle = 6;

    /**
     * <h3>I don’t specify the Y coordinate for:</h3>
     * <pre>
     *     location_X_line_TITLE
     *     location_X_line_NOTIFICATION
     *     location_X_line_CONTENT
     * </pre>
     * because when adding text to the screen
     * centering occurs relative to the text content
     */
    protected static int[] location_X_line_TITLE = new int[] {4};
    protected static int[] location_X_line_NOTIFICATION = new int[] {5, 6};
    protected static int[] location_X_line_CONTENT = new int[] {10, 11, 12, 13};

    /**
     * <h3>Full X, Y coordinates to add server IP and server PORT</h3> (templates)
     */
    protected static int[] location_X_Y_serverIp = new int[] {1, 1};
    protected static int[] location_X_Y_serverPort = new int[] {Math.round((float) SIZE_DISPLAY_X) / 2, 1};

    protected static int getX_center_for(String str) {
        return (SIZE_DISPLAY_X / 2) - (Math.round((float) str.length() / 2));
    }

    protected void resetLineCounter() {
        location_y_linePrint_notTitle = 4;
    }

    protected int[] getLines(Block block) {
        switch (block) {
            case TITLE -> {
                return location_X_line_TITLE;
            }
            case NOTIFICATION -> {
                return location_X_line_NOTIFICATION;
            }
            case CONTEXT -> {
                return location_X_line_CONTENT;
            }
            case SERVER_IP -> {
                return location_X_Y_serverIp;
            }
            case SERVER_PORT -> {
                return location_X_Y_serverPort;
            }
            default -> {
                throw new NumberFormatException("Invalid information block format");
            }
        }
    }
}
