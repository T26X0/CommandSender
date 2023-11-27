package com.User_Interface;

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

    protected static int[] location_serverIp = new int[] {1, 1};
    protected static int[] location_serverPort = new int[] {Math.round((float) SIZE_DISPLAY_X) / 2, 1};

    protected static int getX_center_for(String str) {
        return (SIZE_DISPLAY_X / 2) - (Math.round((float) str.length() / 2));
    }

    protected void resetLineCounter() {
        location_y_linePrint_notTitle = 4;
    }
}
