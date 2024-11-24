/*
 * StartMenu.java
 *
 * This class represents the main menu of the Tetris game. It provides the user
 * interface for navigating between different game modes and settings. The StartMenu
 * includes options for single-player mode, multiplayer mode, settings, and credits.
 * It also displays the game's title and a placeholder image.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate StartMenu to initialize and display the main menu UI for the Tetris game.
 *   - Components like TitlePanel, MenuPanel, and TitleImagePanel are added for a structured layout.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries for UI components
 *   - TitlePanel for the game's title display
 *   - MenuPanel for menu buttons
 *   - TitleImagePanel for the game's placeholder image
 *   - StartMenuActionHandler for handling button actions
 */

package main.java.tetris.ui.startmenu;

import java.awt.*;
import javax.swing.*;
import main.java.tetris.ui.UIConstants;

// StartMenu class represents the main menu of the Tetris game.
public class StartMenu extends JPanel {

    public StartMenu() {
        // Set up the layout and background color
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

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
        add(leftPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createRightPanel(JPanel titleImagePanel) {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        rightPanel.add(Box.createVerticalStrut(UIConstants.RIGHT_PANEL_TOP_SPACING));
        titleImagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(titleImagePanel);
        rightPanel.add(Box.createVerticalStrut(UIConstants.RIGHT_PANEL_BOTTOM_SPACING));

        return rightPanel;
    }
}
