/*
 * CreditsPanel.java
 *
 * This class creates the credits section for the Tetris game.
 * It displays the names of contributors and their roles in a styled format.
 * The panel is designed to fit seamlessly into the game's UI theme.
 *
 * Authors: Lauren Gregory
 * Last Updated Date: 12/7/2024
 *
 * Usage:
 *   - Instantiate CreditsPanel to display the credits at the bottom of the game.
 *   - Customizable by editing the text content and formatting in the getCreditsText method.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 */

package main.java.tetris.ui.credits;

import java.awt.*;
import javax.swing.*;

// CreditsPanel class creates a styled panel to display game credits.
public class CreditsPanel extends JPanel {

    // Define constants for layout and styling
    private static final int PADDING_TOP = 20;
    private static final int PADDING_LEFT = 20;
    private static final int PADDING_BOTTOM = 20;
    private static final int PADDING_RIGHT = 20;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE = 14;
    private static final int WINDOW_WIDTH = 400;
    private static final int CREDITS_HEIGHT = 150;

    /*
     * Constructs a CreditsPanel, setting the background color, padding,
     * and adding the credits content.
     */
    public CreditsPanel() {
        // Set background color to match the game's theme
        setBackground(Color.BLACK);

        // Set padding around the credits for proper alignment
        setBorder(BorderFactory.createEmptyBorder(
                PADDING_TOP,
                PADDING_LEFT,
                PADDING_BOTTOM,
                PADDING_RIGHT
        ));

        // Create and add the credits component
        JComponent creditsComponent = createCreditsComponent();
        add(creditsComponent);
    }

    /*
     * Creates and returns the credits component.
     * The component is a JLabel styled with HTML for formatting the text.
     */
    private JComponent createCreditsComponent() {
        // Create a JLabel for displaying the credits
        JLabel creditsLabel = new JLabel();
        creditsLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        creditsLabel.setForeground(Color.WHITE);
        creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creditsLabel.setText(getCreditsText());

        // Set preferred size to ensure proper layout in the panel
        creditsLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, CREDITS_HEIGHT));
        return creditsLabel;
    }

    /*
     * Generates the styled credits text using HTML for formatting.
     * Includes team members and their roles.
     */
    private String getCreditsText() {
        return "<html>"
                + "<div style='text-align: center;'>"
                + "<h2 style='color: cyan;'>Tetris Game Credits</h2>"
                + "<p style='color: white;'>Developed by:</p>"
                + "<p style='color: yellow;'>Daniyar Alimkhanov, Justin Morgan, Lauren Gregory - Lead Developer</p>"
                + "</div>"
                + "</html>";
    }
}
