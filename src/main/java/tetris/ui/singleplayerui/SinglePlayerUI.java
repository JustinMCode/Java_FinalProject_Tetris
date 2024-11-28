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
import main.java.tetris.mechanics.GameController;
import main.java.tetris.utility.ButtonFactory;
import main.java.tetris.ui.startmenu.StartMenu;

import javax.swing.*;
import java.awt.*;

import static main.java.tetris.ui.singleplayerui.SinglePlayerUIConstants.*;

public class SinglePlayerUI {

    private JPanel scorePanel;
    private JPanel nextPiecePanel;

    public SinglePlayerUI(SinglePlayer parent, GameController gameController) {
        // Set the parent layout to null for manual positioning of components
        parent.setLayout(null);

        // Create and configure the game board UI
        GameBoardUI gameBoardUI = gameController.getGameBoardUI();
        gameBoardUI.setBounds(GAME_BOARD_X, GAME_BOARD_Y, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
        gameBoardUI.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a border to the game board

        // Create and configure the "Score" panel
        scorePanel = createScorePanel(gameController);  // Use this method to create and initialize the score panel
        scorePanel.setBounds(SCORE_PANEL_X, SCORE_PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT);  // Using PANEL_WIDTH and PANEL_HEIGHT

        // Create and configure the "Next" panel
        nextPiecePanel = createNextPiecePanel(); // Create next panel
        nextPiecePanel.setBounds(NEXT_PANEL_X, NEXT_PANEL_Y, PANEL_WIDTH, PANEL_HEIGHT); // Position it properly

        // Create and configure the rainbow-colored title label
        JLabel titleLabel = new JLabel(RAINBOW_TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        // Create the "Back" button using ButtonFactory
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
        parent.add(titleLabel);
        parent.add(gameBoardUI);     // Add the game board
        parent.add(scorePanel);      // Add the score panel
        parent.add(nextPiecePanel);  // Add the next piece panel
        parent.add(backButton);      // Add the back button
    }

    // Creates the score panel and initializes the score label
    private JPanel createScorePanel(GameController gameController) {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL_BACKGROUND_COLOR);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(PANEL_BORDER_COLOR, PANEL_BORDER_THICKNESS));

        // Create the label for the score
        JLabel scoreLabel = new JLabel(SCORE_LABEL_TEXT + ": 0", SwingConstants.CENTER);
        scoreLabel.setFont(PANEL_FONT);
        panel.add(scoreLabel, BorderLayout.CENTER);

        // Pass the score label to the game controller to update it
        gameController.setScoreLabel(scoreLabel);

        return panel;
    }

    // Creates the next piece panel
    private JPanel createNextPiecePanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL_BACKGROUND_COLOR);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(PANEL_BORDER_COLOR, PANEL_BORDER_THICKNESS));

        // Create the label for the next piece
        JLabel nextPieceLabel = new JLabel(NEXT_LABEL_TEXT, SwingConstants.CENTER);
        nextPieceLabel.setFont(PANEL_FONT);
        panel.add(nextPieceLabel, BorderLayout.CENTER);

        return panel;
    }
}