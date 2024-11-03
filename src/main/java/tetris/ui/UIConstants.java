package main.java.tetris.ui;

/*
 * UIConstants class holds constant values used throughout the UI for consistent styling.
 * It centralizes settings like window dimensions, font settings, padding, and spacing, allowing
 * easy adjustments to the look and feel of the application.
 */
public class UIConstants {

    // -----------------------------------
    // Universal Constants
    // -----------------------------------

    /* Font size used for error messages displayed in the UI */
    public static final int ERROR_FONT_SIZE = 20;

    // -----------------------------------
    // StartMenu Settings
    // -----------------------------------

    // ------------------------------
    // Window Settings
    // ------------------------------

    /* The width of the main window for the StartMenu */
    public static final int WINDOW_WIDTH = 1000;

    /* The height of the main window for the StartMenu */
    public static final int WINDOW_HEIGHT = 800;

    /* The title of the game, displayed in the window title bar */
    public static final String GAME_TITLE = "Tetris";

    // ------------------------------
    // Font Settings
    // ------------------------------

    /* Default font name for titles, buttons, and text elements */
    public static final String FONT_NAME = "Arial";

    /* Font size for the main title in the StartMenu */
    public static final int TITLE_FONT_SIZE = 80;

    /* Font size for buttons in the StartMenu */
    public static final int BUTTON_FONT_SIZE = 50;

    // ------------------------------
    // Padding and Spacing
    // ------------------------------

    // Title Padding
    /* Top padding for the title to provide spacing from the top of the window */
    public static final int TITLE_PADDING_TOP = 100;

    // Menu Panel Padding
    /* Top padding for the menu panel within the StartMenu */
    public static final int MENU_PANEL_PADDING_TOP = 20;

    // Button Spacing
    /* Vertical spacing between buttons in the menu panel */
    public static final int BUTTON_SPACING = 10;

    // ------------------------------
    // Image Dimensions
    // ------------------------------

    /* Width of images used in the StartMenu (height adjusts to maintain aspect ratio) */
    public static final int IMAGE_WIDTH = 400;

    // ------------------------------
    // Right Panel Spacing
    // ------------------------------

    /* Top spacing for components within the right panel */
    public static final int RIGHT_PANEL_TOP_SPACING = 20;

    /* Bottom spacing for components within the right panel */
    public static final int RIGHT_PANEL_BOTTOM_SPACING = 20;

    // ------------------------------
    // BorderFactory Settings
    // ------------------------------

    /* Left padding for components with a BorderFactory-defined border */
    public static final int BORDERFACTORY_LEFT_PADDING = 20;

    /* Right padding for components with a BorderFactory-defined border */
    public static final int BORDERFACTORY_RIGHT_PADDING = 30;

}
