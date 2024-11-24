/*
 * SinglePlayer.java
 *
 * This class represents the single-player game mode of the Tetris game.
 * It sets up the gameplay UI, including a customizable background image
 * and game logic integration. The SinglePlayer panel is designed to be
 * manually positioned and rendered within a parent JFrame.
 *
 * Author: Justin Morgan, Lauren Greg
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate SinglePlayer to initialize and display the single-player game UI.
 *   - The game controller handles the game logic and interacts with the UI components.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - SinglePlayerUI for structured UI setup
 *   - GameController for game mechanics and state management
 *   - ImageUtils for loading and scaling the background image
 */

package main.java.tetris.Game;

import main.java.tetris.utility.ImageUtils;
import main.java.tetris.mechanics.GameController;
import main.java.tetris.ui.singleplayerui.SinglePlayerUI;

import javax.swing.*;
import java.awt.*;

/*
 * SinglePlayer class sets up the single-player game mode,
 * including a customizable background image and a UI managed by SinglePlayerUI.
 */
public class SinglePlayer extends JPanel {

    // Constants
    private static final String BACKGROUND_IMAGE_PATH = "/main/resources/images/Tetris_BackgroundV7.jpg";
    private static final Color FALLBACK_BACKGROUND_COLOR = Color.GRAY;
    private static final LayoutManager PANEL_LAYOUT = null;
    private final Image backgroundImage;

    // Constructor: Initializes the SinglePlayer panel.
    public SinglePlayer() {
        // Load the background image using ImageUtils
        this.backgroundImage = ImageUtils.loadImage(BACKGROUND_IMAGE_PATH);

        // Set layout to null for manual positioning
        setLayout(PANEL_LAYOUT);

        GameController gameController = new GameController();

        // Initialize the UI components and layout
        initializeUI(gameController);

        // Request focus for the game board UI to capture keyboard input during gameplay
        SwingUtilities.invokeLater(gameController.getGameBoardUI()::requestFocusInWindow);
    }

    /*
     * Initializes the SinglePlayer UI by passing the game controller
     * to the SinglePlayerUI class, which handles component layout and rendering.
     */
    private void initializeUI(GameController gameController) {
        // Delegate UI setup to SinglePlayerUI
        new SinglePlayerUI(this, gameController);
    }

    /*
     * Overridden paintComponent method: Draws the background image for the panel.
     * Ensures the image covers the entire panel, scaling to fit the dimensions.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Call the superclass implementation for default behavior
        super.paintComponent(g);

        // Draw the background image, scaling it to fit the entire panel
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Fill the panel with a fallback color if the background image is null
            g.setColor(FALLBACK_BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
