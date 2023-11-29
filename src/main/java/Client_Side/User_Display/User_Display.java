package Client_Side.User_Display;

import Client_Side.User_Display.Config.LineDisplay;
import Client_Side.User_Display.Config.TextBlock;

import java.io.IOException;
import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Display extends LineDisplay {

    public User_Display() {
        super();
    }

    public void add(String str, TextBlock textBlock) {
        int x = get_X_for_centering(str);
        int y = 0;

        Map<String, String> stringWithCoordinates;

        if (textBlock == TextBlock.TITLE) {

            y = TextBlock.TITLE.coordinates[lineCounter];
        }

        if (textBlock == TextBlock.NOTIFICATION) {

            y = TextBlock.NOTIFICATION.coordinates[lineCounter];
        }

        if (textBlock == TextBlock.CONTENT) {
            System.out.println(str);
            y = TextBlock.CONTENT.coordinates[lineCounter];
        }

        stringWithCoordinates = prepareToInsertInMap(x, y, str);
        addToDisplay(stringWithCoordinates);
        lineCounter++;
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

    public void show() {
        purify_display();
        show_display();
        resetLineCounter();
    }

    private static String prepareTextForException(TextBlock textBlock, String str) {
        return "\nMaximum positions in the: " + textBlock.toString() + " block.\n " +
                "There was an attempt for a text: \"" + str + "\"";
    }
}
