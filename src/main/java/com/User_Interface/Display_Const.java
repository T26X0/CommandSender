package com.User_Interface;

class Display_Const {
    protected static final int X_POINT = 0;
    protected static final int Y_POINT = 1;
    protected static final String APP_TITLE = "YOU SENDER";
    protected static String userName = "";
    protected static final String frameSymbol_x = "_";
    protected static final String frameSymbol_y = "|";
    protected static final int SIZE_DISPLAY_X = 70;
    protected static final int SIZE_DISPLAY_Y = 15;
    protected static int SIZE_NAME_BOX = 14;
    protected static final int maxPermissible_y = SIZE_DISPLAY_Y - 1;
    protected static int headline_Y_Position = 1;

    protected static int getX_center_for(String str) {
        return (SIZE_DISPLAY_X / 2) - (Math.round((float) str.length() / 2));
    }
}
