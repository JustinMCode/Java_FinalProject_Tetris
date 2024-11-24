/*
 * ButtonAction.java
 *
 * This enum represents the different actions available in the StartMenu of the Tetris game.
 * Each action is associated with a display text and a color, which are used to label and style
 * the corresponding buttons in the menu.
 *
 * Authors: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Use ButtonAction values to identify specific actions in the StartMenu.
 *   - Each enum value provides both a display label (text) and a color for button styling.
 *
 * Dependencies:
 *   - Java AWT Color class for defining button colors
 */

package main.java.tetris.ui.components;

import java.awt.Color;

public enum ButtonAction {
    // Enum values representing each action in the StartMenu, with text and color
    SINGLEPLAYER("Single-player", new Color(255, 255, 0)), // Yellow
    MULTIPLAYER("Multiplayer", new Color(0, 255, 0)),      // Green
    OPTIONS("Options", new Color(128, 0, 128)),            // Purple
    CREDITS("Credits", new Color(0, 0, 255)),              // Blue
    EXIT("Exit", new Color(0, 0, 139));                    // Dark Blue

    // Private fields to store the display text and color of each button action
    private final String text;
    private final Color color;

    // Constructs a ButtonAction enum value with specified text and color.
    ButtonAction(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    // Gets the display text of the button.
    public String getText() {
        return text;
    }

    // Gets the color associated with the button.
    public Color getColor() {
        return color;
    }
}
