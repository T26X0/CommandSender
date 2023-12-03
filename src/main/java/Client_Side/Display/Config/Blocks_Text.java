package Client_Side.Display.Config;


public enum Blocks_Text {

    /**
     * Provides ONE LINE for TITLE block
     */
    TITLE(Display_Const.textBlock_TITLE_location_Y),

    /**
     * Provides TWO LINES for NOTIFICATION block
     */
    NOTIFICATION(Display_Const.textBlock_NOTIFICATION_location_Y),

    /**
     * Provides FOUR LINES for CONTEXT block
     */
    CONTENT(Display_Const.textBlock_CONTENT_location_Y);

    public final int[] coordinates;

    Blocks_Text(int[] lines) {
        this.coordinates = lines;
    }
}
