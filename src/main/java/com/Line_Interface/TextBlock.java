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
    CONTENT(Display_Const.textBlock_CONTENT_location_X),

    SERVER_IP(Display_Const.block_serverIp_position_X_Y),
    SERVER_PORT(Display_Const.block_serverPort_position_X_Y),
    USER_NAME(Display_Const.position_X_Y_userName);

    final int[] coordinates;

    TextBlock(int[] lines) {
        this.coordinates = lines;
    }
}
