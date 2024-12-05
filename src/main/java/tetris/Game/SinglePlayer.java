/*
 * SinglePlayer.java
 *
 * This class represents the Single Player mode in Tetris. It sets up the game mechanics,
 * user interface, and background for a standalone single-player experience.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/03/2024
 *
 * Features:
 * - Customizable background image with fallback color.
 * - Game controller initialization with key bindings for gameplay.
 * - Integration with SinglePlayerUI for layout and additional UI components.
 *
 * Dependencies:
 * - GameController for game logic.
 * - SinglePlayerUI for user interface setup.
 * - ImageUtils for loading and managing images.
 */

package main.java.tetris.Game;

import main.java.tetris.utility.ImageUtils;
import main.java.tetris.mechanics.GameController;
import main.java.tetris.ui.singleplayerui.SinglePlayerUI;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.util.HashMap;
import java.awt.event.KeyEvent;


public class SinglePlayer extends JPanel {

    // Constants for background image and fallback color
    private static final String BACKGROUND_IMAGE_PATH = "/main/resources/images/Tetris_BackgroundV7.jpg";
    private static final Color FALLBACK_BACKGROUND_COLOR = Color.GRAY;

    // Background image to display
    private final Image backgroundImage;

    // Game controller for managing gameplay
    private GameController gameController;

    /*
     * Constructor for SinglePlayer mode.
     * Sets up the game controller, user interface, and background display.
     */
    public SinglePlayer() {
        // Load the background image using ImageUtils
        this.backgroundImage = ImageUtils.loadImage(BACKGROUND_IMAGE_PATH);

        // Use null layout for custom component positioning
        setLayout(null);

        // Initialize the game controller
        initializeGameController();

        // Initialize user interface components
        initializeUI();

        // Request focus for the game board to capture key events
        SwingUtilities.invokeLater(gameController.getGameBoardUI()::requestFocusInWindow);
    }

    // Initializes the game controller and configures key bindings for gameplay.
    private void initializeGameController() {
        // Create the key bindings map for user controls
        Map<Integer, Runnable> keyBindings = new HashMap<>();

        // Instantiate the game controller
        gameController = new GameController(keyBindings);

        // Define key bindings for single-player gameplay
        keyBindings.put(KeyEvent.VK_LEFT, gameController::moveLeft);
        keyBindings.put(KeyEvent.VK_RIGHT, gameController::moveRight);
        keyBindings.put(KeyEvent.VK_DOWN, gameController::moveDown);
        keyBindings.put(KeyEvent.VK_UP, gameController::rotate);
    }

    /*
     * Initializes the user interface components for Single Player mode.
     * Delegates layout setup to SinglePlayerUI.
     */
    private void initializeUI() {
        // Delegate UI setup to SinglePlayerUI for better modularity
        new SinglePlayerUI(this, gameController);
    }

    /*
     * Custom painting logic for the background.
     * Displays the background image if available, otherwise uses a fallback color.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Scale the background image to fit the panel dimensions
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Use fallback color if background image is unavailable
            g.setColor(FALLBACK_BACKGROUND_COLOR);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}