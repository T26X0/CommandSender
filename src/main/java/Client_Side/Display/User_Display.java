package Client_Side.Display;

import Client_Side.Display.Config.Display_Config;
import Client_Side.Display.Config.Blocks_Text;

import java.util.Arrays;
import java.util.Map;

/**
 * <h2>Class for visualization client actions</h2>
 * Size:
 * <pre>x = 30</pre>
 * <pre>y = 30</pre>
 */
public class User_Display extends Display_Config {

    public User_Display() {
        super();
    }

    public void add(String str, Blocks_Text textBlock) {
        int x = get_X_for_centering(str);
        int y = 0;

        Map<String, String> stringWithCoordinates;

        if (textBlock == Blocks_Text.TITLE) {

            y = Blocks_Text.TITLE.coordinates[lineCounter];
        }

        if (textBlock == Blocks_Text.NOTIFICATION) {

            y = Blocks_Text.NOTIFICATION.coordinates[lineCounter];
        }

        if (textBlock == Blocks_Text.CONTENT) {
            System.out.println(str);
            y = Blocks_Text.CONTENT.coordinates[lineCounter];
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
    public void update() {
        updateDisplay();
    }

    public void show() {
        purify_display();
        show_display();
        resetLineCounter();
    }

    private static String prepareTextForException(Blocks_Text textBlock, String str) {
        return "\nMaximum positions in the: " + textBlock.toString() + " block.\n " +
                "There was an attempt for a text: \"" + str + "\"";
    }
}
