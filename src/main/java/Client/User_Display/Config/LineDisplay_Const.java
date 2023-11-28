package Client.User_Display.Config;

import Client.Line_Sender.Config.User_Fields;

import java.util.HashMap;
import java.util.Map;

class LineDisplay_Const extends User_Fields {
    protected static final int X_POINT = 0;
    protected static final int Y_POINT = 1;
    protected static String APP_TITLE;
    protected static int[] APP_TITLE_location;
    protected static String logo_image;
    protected static String logo_text;
    protected static String symbol_frame_x;
    protected static String symbol_frame_y;
    protected static String symbol_for_lineFilling;

    protected static int SIZE_DISPLAY_X;
    protected static int SIZE_DISPLAY_Y;

    protected static int location_Y_headline;

    protected static int[] textBlock_TITLE_location_X = new int[1];
    protected static int[] textBlock_NOTIFICATION_location_X = new int[2];
    protected static int[] textBlock_CONTENT_location_X = new int[4];

    protected static int[] block_serverIp_position_X_Y;
    protected static String block_serverIp_title;

    protected static int[] block_serverPort_position_X_Y;
    protected static String block_serverPort_title;

    protected static int[] block_userName_position_X_Y;
    protected static int SIZE_NAME_BOX;

    protected static String command_title_headline;
    protected static String command_title_cmd0;
    protected static String command_title_cmd1;
    protected static String command_title_cmd2;
    protected static String command_title_cmd3;
    protected static String command_title_cmd4;
    protected static String command_title_cmd5;
    protected static String command_title_cmd6;
    protected static String command_title_cmd7;
    protected static String command_title_cmd8;
    protected static String command_title_cmd9;

    protected static int[] command_position_X_Y_headline;
    protected static int[] command_position_X_Y_cmd0;
    protected static int[] command_position_X_Y_cmd1;
    protected static int[] command_position_X_Y_cmd2;
    protected static int[] command_position_X_Y_cmd3;
    protected static int[] command_position_X_Y_cmd4;
    protected static int[] command_position_X_Y_cmd5;
    protected static int[] command_position_X_Y_cmd6;
    protected static int[] command_position_X_Y_cmd7;
    protected static int[] command_position_X_Y_cmd8;
    protected static int[] command_position_X_Y_cmd9;

    /**
     * The command menu becomes visible only after registration!!!
     */
    protected static boolean command_visibility = false;

    protected static Map<String, int[]> allCommands = new HashMap<>();

    static {
        set_logo();
        set_appValues();
        set_displayValues();
        set_commandLines();
    }

    public static void set_commandVisibility(boolean bool) {
        command_visibility = bool;
    }

    private static void set_appValues() {
        SIZE_DISPLAY_X = 80;
        SIZE_DISPLAY_Y = 25;
        symbol_frame_x = "_";
        symbol_frame_y = "|";
        symbol_for_lineFilling = "-";
        APP_TITLE = "LINE SENDER:";
        APP_TITLE_location = new int[] {1, SIZE_DISPLAY_Y - 1};
    }

    /**
     * <h3>I don’t specify the Y coordinate for:</h3>
     * <pre>
     *     location_X_line_TITLE
     *     location_X_line_NOTIFICATION
     *     location_X_line_CONTENT
     * </pre>
     * because when adding text to the screen
     * centering occurs relative to the text content
     * <pre></pre>
     * <h3>Full X, Y coordinates to add server IP and server PORT</h3>
     */
    private static void set_displayValues() {
        location_Y_headline = 1;
        SIZE_NAME_BOX = 15;

        textBlock_TITLE_location_X[0] = 4;

        textBlock_NOTIFICATION_location_X[0] = 5;
        textBlock_NOTIFICATION_location_X[1] = 6;

        textBlock_CONTENT_location_X[0] = 8;
        textBlock_CONTENT_location_X[1] = 9;
        textBlock_CONTENT_location_X[2] = 10;
        textBlock_CONTENT_location_X[3] = 11;

        block_serverIp_position_X_Y = new int[]{1, 1};
        block_serverIp_title = "server ip: ";

        block_serverPort_position_X_Y = new int[]{Math.round((float) SIZE_DISPLAY_X) / 2, 1};
        block_serverPort_title = "server port: ";

        block_userName_position_X_Y = new int[]{SIZE_DISPLAY_X - SIZE_NAME_BOX + 2, 1};

    }

    private static void set_commandLines() {
        command_title_headline = filledLine("COMMANDS");

        command_title_cmd0 = "[0] change name -" + " SOON";
        command_title_cmd1 = "[1] change server ip -" + " SOON";
        command_title_cmd2 = "[2] change server port -" + " SOON";
        command_title_cmd3 = "[3] add friend -" + " SOON";
        command_title_cmd4 = "[4] view friends -" + " SOON";

        command_title_cmd5 = "[5] view all users -" + " SOON";
        command_title_cmd6 = "[6] generate URL:friend -" + " SOON";
        command_title_cmd7 = "[7] try connecting -" + " SOON";
        command_title_cmd8 = "[8] disconnected -" + " SOON";
        command_title_cmd9 = "[9] exit -" + " SOON";

        command_position_X_Y_headline = new int[]{
                get_X_for_centering(command_title_headline),
                SIZE_DISPLAY_Y - 8};

        command_position_X_Y_cmd0 = new int[]{2, command_position_X_Y_headline[Y_POINT] + 1};
        command_position_X_Y_cmd1 = new int[]{2, command_position_X_Y_cmd0[Y_POINT] + 1};
        command_position_X_Y_cmd2 = new int[]{2, command_position_X_Y_cmd1[Y_POINT] + 1};
        command_position_X_Y_cmd3 = new int[]{2, command_position_X_Y_cmd2[Y_POINT] + 1};
        command_position_X_Y_cmd4 = new int[]{2, command_position_X_Y_cmd3[Y_POINT] + 1};

        command_position_X_Y_cmd5 = new int[]{get_X_centerDisplay(), command_position_X_Y_headline[Y_POINT] + 1};
        command_position_X_Y_cmd6 = new int[]{get_X_centerDisplay(), command_position_X_Y_cmd5[Y_POINT] + 1};
        command_position_X_Y_cmd7 = new int[]{get_X_centerDisplay(), command_position_X_Y_cmd6[Y_POINT] + 1};
        command_position_X_Y_cmd8 = new int[]{get_X_centerDisplay(), command_position_X_Y_cmd7[Y_POINT] + 1};
        command_position_X_Y_cmd9 = new int[]{get_X_centerDisplay(), command_position_X_Y_cmd8[Y_POINT] + 1};

        allCommands.put(command_title_headline, command_position_X_Y_headline);
        allCommands.put(command_title_cmd0, command_position_X_Y_cmd0);
        allCommands.put(command_title_cmd1, command_position_X_Y_cmd1);
        allCommands.put(command_title_cmd2, command_position_X_Y_cmd2);
        allCommands.put(command_title_cmd3, command_position_X_Y_cmd3);
        allCommands.put(command_title_cmd4, command_position_X_Y_cmd4);
        allCommands.put(command_title_cmd5, command_position_X_Y_cmd5);
        allCommands.put(command_title_cmd6, command_position_X_Y_cmd6);
        allCommands.put(command_title_cmd7, command_position_X_Y_cmd7);
        allCommands.put(command_title_cmd8, command_position_X_Y_cmd8);
        allCommands.put(command_title_cmd9, command_position_X_Y_cmd9);

    }

    private static void set_logo() {
        logo_image =
                "⠀     ⠀        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣶⣿⣿⣿⣶⣶⣤⣄⡀⠀⠀⠀⠀⠀\n" +
                "⠀  ⠀⠀         ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣯⢿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣦⠀⠀⠀⠀\n" +
                "⠀      ⠀     ⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣯⣽⣶⣶⣾⣕⣝⣧⣣⢫⡋⡿⠿⣇⠀⠀⠀\n" +
                "⠀         ⠀  ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⢖⢬⣍⠻⣿⣿⡜⢧⠑⣴⣿⣿⣦⠀⠀\n" +
                "  ⠀      ⠀   ⠐⠻⣿⣿⣟⣛⡻⠶⣦⣻⣿⣿⣿⣿⡗⢿⣿⣿⢙⠛⣃⠬⢄⠉⠉⠀⠀⠀⠀\n" +
                "⠀         ⠀⠀  ⠀⠈⠻⣿⣿⠁⠀⣶⣿⣿⣿⣿⣿⣏⣚⣏⣴⢟⣟⠁⣰⠀⡇⣄⣽⣿⡄⠀\n" +
                "⠀⠀         ⠀⠀⠀  ⠀⠈⢿⣶⡾⠿⢻⣿⣿⡛⠋⠁⣠⢏⣵⣿⣿⣿⣿⣷⣿⡘⡜⡿⠃⠀\n" +
                "         ⠀⠀⠀⠀⠀⠀⠀  ⠀⠙⠿⣿⡿⣿⢻⡧⢀⡞⣡⣿⣿⣿⣿⣿⣿⣿⡿⣷⢁⠀⠀⠀\n" +
                "       ⠀  ⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⣾⡿⣼⣳⢿⣾⣿⣿⣿⣶⣶⣶⣶⡶⠶⣶⣍ \n" +
                "⠀       ⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⠀  ⣿⢷⣿⣿⣿⡿⢋⠀⢶⠂⠶⠀⠄⠄⡤⣤  \n" +
                " ⠀        ⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⢸⣿⠈⣿⣿⠟⠀⣰⡇⢀⡀⠀⠠⠀⠀⠀⠉⣤  \n" +
                "    ⠀  ⠀       ⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⡇⠼⠷⢿⣇⢸⣷⡀⣾⡆⣾⣷⠠⡿⠆ \n" +
                "       ⠀⠀ ⠀   ⠀⠀⠀⠀⠀⠀⠀⠸⣿⣽⣿⣷⣿⡿⣿⣷⣶⣶⣶⣶⣶⣶⣶⣶⣶⣷⣦ \n" +
                "       ⠀⠀⠀⠀  ⠀  ⠀⠀⠀⠀⠀⠀⠙⠛⠛⣿⣿⣿⣶⣤⣤⣈⡉⠉⠉⠉⠉⠉⠉⠉⠁⠀\n" +
                "       ⠀⠀⠀⠀⠀⠀  ⠀⠀  ⠀⠀⠀⠀⠀⠀⠈⠻⣯⣭⣯⣭⣤⣶⣶⣶⣶⣦⣤⡈⠁⠀⠀\n" +
                "       ⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀  ⠀⠀⠀⠀⠀⠈⠛⠛⠿⠛⠛⠁⠈⠉⠙⠛⠁⠀⠀";
        logo_text =
                "         _      _                  ____                    _             \n" +
                "        | |    (_) _ __    ___    / ___|   ___  _ __    __| |  ___  _ __ \n" +
                "        | |    | || '_ \\  / _ \\   \\___ \\  / _ \\| '_ \\  / _` | / _ \\| '__|\n" +
                "        | |___ | || | | ||  __/    ___) ||  __/| | | || (_| ||  __/| |   \n" +
                "        |_____||_||_| |_| \\___|   |____/  \\___||_| |_| \\__,_| \\___||_|   ";
    }

    protected static int get_X_for_centering(String str) {
        return get_X_centerDisplay() - (Math.round((float) str.length() / 2));
    }

    protected static int get_X_centerDisplay() {
        return (SIZE_DISPLAY_X / 2);
    }

    /**
     * <h3>Fills the resulting row at the edges</h3>
     * The input string is in the middle
     * @param str String
     * @return String
     */
    private static String filledLine(String str) {
        int str_startsWIth = get_X_for_centering(str);
        StringBuilder filled_string = new StringBuilder();

        for (int i = 0; i < str_startsWIth - 1; i++) {
            filled_string.append(symbol_for_lineFilling);
        }
        filled_string.append(str);
        for (int i = str_startsWIth + str.length(); i < SIZE_DISPLAY_X - 1; i++) {
            filled_string.append(symbol_for_lineFilling);
        }
        return filled_string.toString();
    }

}