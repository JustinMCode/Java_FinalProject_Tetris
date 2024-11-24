/*
 * SinglePlayerUIConstants.java
 *
 * This class defines shared constants for SinglePlayerUI, ensuring consistent styling
 * and reducing hardcoded values across the codebase.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 */

package main.java.tetris.ui.singleplayerui;

import java.awt.*;

public class SinglePlayerUIConstants {

    // Game board dimensions and position
    public static final int GAME_BOARD_X = 345;
    public static final int GAME_BOARD_Y = 100;
    public static final int GAME_BOARD_WIDTH = 300;
    public static final int GAME_BOARD_HEIGHT = 600;

    // Title label properties
    public static final int TITLE_X = 350;
    public static final int TITLE_Y = 10;
    public static final int TITLE_WIDTH = 250;
    public static final int TITLE_HEIGHT = 50;
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 40);

    // Back button properties
    public static final String BACK_BUTTON_TEXT = "Back";
    public static final Color BACK_BUTTON_COLOR = Color.RED;
    public static final int BACK_BUTTON_X = 20;
    public static final int BACK_BUTTON_Y = 20;
    public static final int BACK_BUTTON_WIDTH = 175;
    public static final int BACK_BUTTON_HEIGHT = 40;

    // Panel properties
    public static final int PANEL_WIDTH = 300;
    public static final int PANEL_HEIGHT = 130;
    public static final Color PANEL_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    public static final Color PANEL_BORDER_COLOR = Color.BLACK;
    public static final int PANEL_BORDER_THICKNESS = 2;
    public static final Font PANEL_FONT = new Font("Arial", Font.BOLD, 16);

    // Score panel properties
    public static final String SCORE_LABEL_TEXT = "Score";
    public static final int SCORE_PANEL_X = 665;
    public static final int SCORE_PANEL_Y = 300;

    // Next panel properties
    public static final String NEXT_LABEL_TEXT = "Next";
    public static final int NEXT_PANEL_X = 30;
    public static final int NEXT_PANEL_Y = 300;

    // Rainbow title text
    public static final String RAINBOW_TITLE_TEXT = "<html>"
            + "<span style='color: red;'>S</span>"
            + "<span style='color: orange;'>i</span>"
            + "<span style='color: yellow;'>n</span>"
            + "<span style='color: green;'>g</span>"
            + "<span style='color: blue;'>l</span>"
            + "<span style='color: purple;'>e</span>"
            + "<span style='color: red;'>p</span>"
            + "<span style='color: orange;'>l</span>"
            + "<span style='color: yellow;'>a</span>"
            + "<span style='color: green;'>y</span>"
            + "<span style='color: blue;'>e</span>"
            + "<span style='color: purple;'>r</span>"
            + "</html>";
}
