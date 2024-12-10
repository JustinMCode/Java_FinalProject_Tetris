/*
 * MultiPlayer.java
 *
 * This class represents the Multiplayer mode for Tetris. It manages two game controllers,
 * key bindings for both players, and integrates with the MultiplayerUI for rendering.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/03/2024
 *
 * Features:
 * - Separate key bindings for two players (Player 1: WSAD, Player 2: Arrow keys).
 * - Integration with the MultiplayerUI for layout and game rendering.
 * - Centralized KeyListener for handling inputs for both players.
 *
 * Dependencies:
 * - MultiplayerUI for user interface setup and rendering.
 * - GameController for game mechanics.
 */

package main.java.tetris.Game;

import main.java.tetris.utility.ImageUtils;
import main.java.tetris.mechanics.GameController;
import main.java.tetris.ui.multiplayerui.MultiplayerUI;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import java.util.HashMap;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class MultiPlayer extends JPanel {

    // Constants for background
    private static final String BACKGROUND_IMAGE_PATH = "/main/resources/images/Tetris_BackgroundV7.jpg";
    private static final Color FALLBACK_BACKGROUND_COLOR = Color.GRAY;

    // Panel size and dimensions
    private static final int PANEL_WIDTH = 1200;
    private static final int PANEL_HEIGHT = 1100;

    // Key bindings for Player 1
    private static final int PLAYER1_MOVE_LEFT = KeyEvent.VK_A;
    private static final int PLAYER1_MOVE_RIGHT = KeyEvent.VK_D;
    private static final int PLAYER1_MOVE_DOWN = KeyEvent.VK_S;
    private static final int PLAYER1_ROTATE = KeyEvent.VK_W;

    // Key bindings for Player 2
    private static final int PLAYER2_MOVE_LEFT = KeyEvent.VK_LEFT;
    private static final int PLAYER2_MOVE_RIGHT = KeyEvent.VK_RIGHT;
    private static final int PLAYER2_MOVE_DOWN = KeyEvent.VK_DOWN;
    private static final int PLAYER2_ROTATE = KeyEvent.VK_UP;

    // Background image to display
    private final Image backgroundImage;

    public MultiPlayer() {
        // Load the background image using ImageUtils
        this.backgroundImage = ImageUtils.loadImage(BACKGROUND_IMAGE_PATH);

        setLayout(null); // Manual positioning for components

        // Key bindings for Player 1
        Map<Integer, Runnable> player1KeyBindings = new HashMap<>();
        // Key bindings for Player 2
        Map<Integer, Runnable> player2KeyBindings = new HashMap<>();

        // Initialize controllers with key bindings
        GameController player1Controller = new GameController(player1KeyBindings);
        GameController player2Controller = new GameController(player2KeyBindings);

        // Populate Player 1 key bindings (WSAD)
        player1KeyBindings.put(PLAYER1_MOVE_LEFT, player1Controller::moveLeft);
        player1KeyBindings.put(PLAYER1_MOVE_RIGHT, player1Controller::moveRight);
        player1KeyBindings.put(PLAYER1_MOVE_DOWN, player1Controller::moveDown);
        player1KeyBindings.put(PLAYER1_ROTATE, player1Controller::rotate);

        // Populate Player 2 key bindings (Arrow keys)
        player2KeyBindings.put(PLAYER2_MOVE_LEFT, player2Controller::moveLeft);
        player2KeyBindings.put(PLAYER2_MOVE_RIGHT, player2Controller::moveRight);
        player2KeyBindings.put(PLAYER2_MOVE_DOWN, player2Controller::moveDown);
        player2KeyBindings.put(PLAYER2_ROTATE, player2Controller::rotate);

        // Create MultiplayerUI and add it to the panel
        MultiplayerUI multiplayerUI = new MultiplayerUI(player1Controller, player2Controller);
        multiplayerUI.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        add(multiplayerUI);

        // Add a centralized KeyListener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Check Player 1 bindings
                Runnable player1Action = player1KeyBindings.get(e.getKeyCode());
                if (player1Action != null) {
                    player1Action.run();
                    return;
                }

                // Check Player 2 bindings
                Runnable player2Action = player2KeyBindings.get(e.getKeyCode());
                if (player2Action != null) {
                    player2Action.run();
                }
            }
        });

        // Ensure focus is set to the MultiPlayer panel
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // Ensure focus is requested when the panel is added to a container
        requestFocusInWindow();
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
