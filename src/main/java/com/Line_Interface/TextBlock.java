package com.Line_Interface;


public enum TextBlock {

    /**
     * Provides ONE LINE for TITLE block
     */
    TITLE(Display_Const.textBlock_TITLE_location_X),

    /**
     * Provides TWO LINES for NOTIFICATION block
     */
    NOTIFICATION(Display_Const.textBlock_NOTIFICATION_location_X),

    /**
     * Provides FOUR LINES for CONTEXT block
     */
    CONTENT(Display_Const.textBlock_CONTENT_location_X);

    final int[] coordinates;

    TextBlock(int[] lines) {
        this.coordinates = lines;
    }
}
