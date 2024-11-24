/*
 * SinglePlayerUI.java
 *
 * This class manages the UI layout for the SinglePlayer mode in Tetris.
 * It arranges key components like the game board, score panel, next piece panel,
 * title label, and a back button to navigate to the main menu.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate SinglePlayerUI to set up the UI components for SinglePlayer mode.
 *   - The constructor requires a parent panel (SinglePlayer) and a game controller.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries for UI components
 *   - GameController for game logic
 *   - GameBoardUI for rendering the game board
 *   - ButtonFactory for creating styled buttons
 *   - StartMenu for returning to the main menu
 *   - SinglePlayerUIConstants for shared UI constants
 */

package main.java.tetris.ui.singleplayerui;

import main.java.tetris.Game.SinglePlayer;
import main.java.tetris.utility.ButtonFactory;
import main.java.tetris.ui.startmenu.StartMenu;
import main.java.tetris.mechanics.GameController;
import static main.java.tetris.ui.singleplayerui.SinglePlayerUIConstants.*;

import java.awt.*;
import javax.swing.*;

public class SinglePlayerUI {

    public SinglePlayerUI(SinglePlayer parent, GameController gameController) {
        // Set the parent layout to null for manual positioning of components
        parent.setLayout(null);

        // Create and configure the game board UI
        GameBoardUI gameBoardUI = gameController.getGameBoardUI();
        gameBoardUI.setBounds(GAME_BOARD_X, GAME_BOARD_Y, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
        gameBoardUI.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a border to the game board

        // Create and configure the "Score" panel
        JPanel scorePanel = createUIPanel(SCORE_LABEL_TEXT, SCORE_PANEL_X, SCORE_PANEL_Y);

        // Create and configure the "Next" panel
        JPanel nextPiecePanel = createUIPanel(NEXT_LABEL_TEXT, NEXT_PANEL_X, NEXT_PANEL_Y);

        // Create and configure the rainbow-colored title label
        JLabel titleLabel = new JLabel(getRainbowTitleText(), SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        // Create a "Back" button using ButtonFactory
        JButton backButton = ButtonFactory.createColoredButton(BACK_BUTTON_TEXT, BACK_BUTTON_COLOR);
        backButton.setBounds(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);

        // Add an action listener to the button to return to the main menu
        backButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(parent);
            frame.getContentPane().removeAll(); // Clear the current content
            frame.getContentPane().add(new StartMenu()); // Add the StartMenu panel
            frame.revalidate(); // Refresh the UI
            frame.repaint();
        });

        // Add all components to the parent SinglePlayer panel
        parent.add(titleLabel);      // Add the title label
        parent.add(scorePanel);      // Add the score box
        parent.add(nextPiecePanel);  // Add the next piece box
        parent.add(gameBoardUI);     // Add the game board
        parent.add(backButton);      // Add the back button
    }

    // Creates a reusable panel UI component for display purposes (e.g., Score, Next)
    private static JPanel createUIPanel(String labelText, int x, int y) {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL_BACKGROUND_COLOR);
        panel.setBounds(x, y, PANEL_WIDTH, PANEL_HEIGHT);
        panel.setBorder(BorderFactory.createLineBorder(PANEL_BORDER_COLOR, PANEL_BORDER_THICKNESS));

        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(PANEL_FONT);
        panel.add(label);

        return panel;
    }

    // Generates the rainbow-colored title text for the SinglePlayer label using HTML
    private String getRainbowTitleText() {
        return RAINBOW_TITLE_TEXT;
    }
}
