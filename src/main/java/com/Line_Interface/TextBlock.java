package com.Line_Interface;


public enum TextBlock {

    /**
     * Provides ONE LINE for TITLE block
     */
    TITLE(Display_Const.location_X_textBlock_TITLE),

    /**
     * Provides TWO LINES for NOTIFICATION block
     */
    NOTIFICATION(Display_Const.location_X_textBlock_NOTIFICATION),

    /**
     * Provides FOUR LINES for CONTEXT block
     */
    CONTENT(Display_Const.location_X_textBlock_CONTENT),

    SERVER_IP(Display_Const.location_X_Y_block_serverIp),
    SERVER_PORT(Display_Const.location_X_Y_block_serverPort),
    USER_NAME(Display_Const.location_X_Y_userName);

    final int[] coordinates;

    TextBlock(int[] lines) {
        this.coordinates = lines;
    }
}
