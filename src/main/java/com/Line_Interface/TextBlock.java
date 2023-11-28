package com.Line_Interface;

public enum Block {

    /**
     * Provides ONE LINE for TITLE block
     */
    TITLE(),

    /**
     * Provides TWO LINES for NOTIFICATION block
     */
    NOTIFICATION(),

    /**
     * Provides FOUR LINES for CONTEXT block
     */
    CONTEXT(),

    SERVER_IP,
    SERVER_PORT;

    final Display_Const constants;
    final int[] lines;

    Block() {
        constants = new Display_Const();
        this.lines = constants.getLines(this);
    }
}
