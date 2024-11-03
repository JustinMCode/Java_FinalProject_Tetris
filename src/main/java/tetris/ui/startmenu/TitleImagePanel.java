/*
 * TitleImagePanel.java
 *
 * This class displays the title image for the StartMenu of the Tetris game.
 * It uses a bordered panel with padding and centers the image within the panel,
 * ensuring consistent alignment and layout within the StartMenu.
 *
 * Authors: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Instantiate TitleImagePanel to display the title image in the StartMenu.
 *   - The image label is created using ImageUtils for consistent scaling and styling.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for image size settings
 *   - ImageUtils for creating a scaled image label
 */

package main.java.tetris.ui.startmenu;

import java.awt.*;
import javax.swing.*;
import main.java.tetris.ui.UIConstants;
import main.java.tetris.utility.ImageUtils;

// TitleImagePanel class displays the title image in the StartMenu.
public class TitleImagePanel extends JPanel {

    /*
     * Constructs a TitleImagePanel, setting up background color, padding, and
     * creating an image label to display the title image.
     */
    public TitleImagePanel() {
        // Set background color to match the menu theme
        setBackground(Color.BLACK);

        // Set up borders and padding around the image panel
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GREEN, 0),
                BorderFactory.createEmptyBorder(0, UIConstants.BORDERFACTORY_LEFT_PADDING, 0, UIConstants.BORDERFACTORY_RIGHT_PADDING)
        ));

        // Use BorderLayout to allow the image label to expand and center within the panel
        setLayout(new BorderLayout());

        // Create and scale the image label using ImageUtils
        JLabel imageLabel = ImageUtils.createImageLabel(
                "/main/resources/images/placeholder.png", UIConstants.IMAGE_WIDTH
        );

        // Add the image label to the center of the panel
        add(imageLabel, BorderLayout.CENTER);

        // Set preferred size to control the panel's width
        setPreferredSize(new Dimension(UIConstants.IMAGE_WIDTH, 0));
    }
}
