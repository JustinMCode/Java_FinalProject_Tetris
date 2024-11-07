/*
 * StartMenu.java
 *
 * This class represents the main menu of the Tetris game, assembling the primary UI components
 * and initializing the JFrame. The StartMenu provides options for single-player and multiplayer modes,
 * accessing game options, viewing credits, and exiting the game.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Instantiate StartMenu to initialize and display the main menu UI for the Tetris game.
 *   - Button actions for different game modes and options are handled through action methods.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for consistent UI settings
 *   - TitlePanel, MenuPanel, TitleImagePanel components for structured layout
 */

package main.java.tetris.ui.startmenu;

import java.awt.*;
import javax.swing.*;

import main.java.tetris.Game.SinglePlayer;
import main.java.tetris.ui.UIConstants;

/*
 * StartMenu class represents the main menu of the Tetris game.
 * It assembles the UI components and initializes the frame.
 */
public class StartMenu extends JFrame {

    // Constructor to set up the StartMenu frame and its components.
    public StartMenu() {
        // Set up the frame properties
        setTitle(UIConstants.GAME_TITLE);
        setSize(UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel with a BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Create components for different sections of the main menu
        TitlePanel titlePanel = new TitlePanel();
        MenuPanel menuPanel = new MenuPanel(new StartMenuActionHandler(this));
        TitleImagePanel titleImagePanel = new TitleImagePanel();

        // Assemble the left side of the main panel with title and menu
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.BLACK);
        leftPanel.add(titlePanel, BorderLayout.NORTH);
        leftPanel.add(menuPanel, BorderLayout.CENTER);

        // Assemble the right panel with a placeholder for the title image
        JPanel rightPanel = createRightPanel(titleImagePanel);

        // Add left and right panels to the main panel
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        // Add the main panel to the frame
        add(mainPanel);
    }

    // Creates and returns the right panel containing the title image panel and spacing.
    private JPanel createRightPanel(JPanel titleImagePanel) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // Add vertical strut for top spacing to separate titleImagePanel from top of panel
        rightPanel.add(Box.createVerticalStrut(UIConstants.RIGHT_PANEL_TOP_SPACING));

        // Set alignment for the titleImagePanel to be centered horizontally
        titleImagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the placeholderPanel (e.g., title image) to the rightPanel
        rightPanel.add(titleImagePanel);

        // Add vertical strut for bottom spacing to separate titleImagePanel from bottom of panel
        rightPanel.add(Box.createVerticalStrut(UIConstants.RIGHT_PANEL_BOTTOM_SPACING));

        return rightPanel;
    }

    /*
     * Starts a single-player game.
     * Displays a message as a placeholder for single-player game start logic.
     */
    public void startSinglePlayer() {
        // TODO: Implement single-player start logic
        JOptionPane.showMessageDialog(this, "Starting Single-player...");
        this.getContentPane().removeAll();
        SinglePlayer SPGame = new SinglePlayer();
        this.add(SPGame);
        this.revalidate();
        this.repaint();
        //game.requestFocusInWindow(); useless for now

    }

    /*
     * Starts a multiplayer game.
     * Displays a message as a placeholder for multiplayer game start logic.
     */
    public void startMultiplayer() {
        // TODO: Implement multiplayer start logic
        JOptionPane.showMessageDialog(this, "Starting Multiplayer...");
    }

    /*
     * Opens the options menu.
     * Displays a message as a placeholder for options menu functionality.
     */
    public void openOptions() {
        // TODO: Implement options menu
        JOptionPane.showMessageDialog(this, "Opening Options...");
    }

    /*
     * Shows the credits for the game.
     * Displays a message as a placeholder for credits functionality.
     */
    public void showCredits() {
        // TODO: Implement credits display
        JOptionPane.showMessageDialog(this, "Showing Credits...");
    }

    // Exits the game.
    public void exitGame() {
        System.exit(0);
    }
}
