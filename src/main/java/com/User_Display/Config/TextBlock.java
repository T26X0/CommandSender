package com.User_Display.Config;


public enum TextBlock {

    /**
     * Provides ONE LINE for TITLE block
     */
    TITLE(LineDisplay_Const.textBlock_TITLE_location_X),

    /**
     * Provides TWO LINES for NOTIFICATION block
     */
    NOTIFICATION(LineDisplay_Const.textBlock_NOTIFICATION_location_X),

    /**
     * Provides FOUR LINES for CONTEXT block
     */
    CONTENT(LineDisplay_Const.textBlock_CONTENT_location_X);

    public final int[] coordinates;

    TextBlock(int[] lines) {
        this.coordinates = lines;
    }
}
