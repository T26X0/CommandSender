package com.Line_Interface;

import java.io.IOException;
import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Display extends Display_Config {
    protected static int lineCounter = 0;

    public User_Display() {
        super();
    }


    public void add(String str, TextBlock textBlock) throws IOException {
        int x = get_X_for_centering(str);
        int y = 0;

        Map<String, String> stringWithCoordinates;

        if (textBlock == TextBlock.TITLE) {
            if (lineCounter >= TextBlock.TITLE.coordinates.length) {
                throw new IOException(prepareTextForException(textBlock, str));
            }
            y = TextBlock.TITLE.coordinates[lineCounter];
        }

        if (textBlock == TextBlock.NOTIFICATION) {
            if (lineCounter >= TextBlock.NOTIFICATION.coordinates.length) {
                throw new IOException(prepareTextForException(textBlock, str));
            }
            y = TextBlock.NOTIFICATION.coordinates[lineCounter];
        }

        if (textBlock == TextBlock.CONTENT) {
            if (lineCounter >= TextBlock.CONTENT.coordinates.length) {
                throw new IOException(prepareTextForException(textBlock, str));
            }
            y = TextBlock.CONTENT.coordinates[lineCounter];
        }

        stringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToDisplay(stringWithCoordinates);
        lineCounter++;
    }

    private static String prepareTextForException(TextBlock textBlock, String str) {
        return  "\nMaximum positions in the: " + textBlock.toString() + " block.\n " +
                "There was an attempt for a text: \"" + str + "\"";
    }

    public void show() {
        for (int y = 0; y < SIZE_DISPLAY_Y; y++) {
            for (int x = 0; x < SIZE_DISPLAY_X; x++) {
                String coordinates = getCoordinates(x, y);
                System.out.print(working_display.get(coordinates));
            }
            System.out.println();
        }
        resetLineCounter();
    }

    /**
     * this method simply prints on top of the line above the current one
     *
     * @param str String
     */
    public void replace(String str) throws IOException {
        lineCounter--;
        add(str, TextBlock.NOTIFICATION);
    }

    /**
     * <h3>reset display -> deleting all text block</h3>
     * <pre>
     *     1. TextBlock.TITLE
     *     2. TextBlock.NOTIFICATION
     *     3. TextBlock.CONTENT
     * </pre>
     */
    public void reset() {
        updateDisplay();
    }

    private void add(int x, int y, String str) {
        Map<String, String> StringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToDisplay(StringWithCoordinates);
    }

    protected void resetLineCounter() {
        lineCounter = 0;
    }
}
