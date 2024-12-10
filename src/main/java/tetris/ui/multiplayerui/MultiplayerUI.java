/*
 * MultiplayerUI.java
 *
 * This class handles the UI layout for the Multiplayer mode in Tetris.
 * It sets up the player boards for Player 1 and Player 2 and includes
 * a back button for returning to the StartMenu.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/03/2024
 *
 * Features:
 * - Separate player boards for Player 1 and Player 2 with custom positioning.
 * - A back button created using ButtonFactory to return to the StartMenu.
 *
 * Dependencies:
 * - GameController for managing player game logic.
 * - ButtonFactory for creating styled buttons.
 * - PlayerBoardUI for individual player board rendering.
 */

package main.java.tetris.ui.multiplayerui;

import main.java.tetris.utility.ButtonFactory;
import main.java.tetris.mechanics.GameController;
import javax.swing.*;
import java.awt.*;

public class MultiplayerUI extends JPanel {

    // Constants for player board positions
    private static final int PLAYER1_BOARD_X = 150;
    private static final int PLAYER1_BOARD_Y = 70;
    private static final int PLAYER2_BOARD_X = 550;
    private static final int PLAYER2_BOARD_Y = 70;

    // Title properties
    private static final String RAINBOW_TITLE_TEXT = "<html>"
            + "<span style='color: red;'>M</span>"
            + "<span style='color: orange;'>u</span>"
            + "<span style='color: yellow;'>l</span>"
            + "<span style='color: green;'>t</span>"
            + "<span style='color: blue;'>i</span>"
            + "<span style='color: purple;'>p</span>"
            + "<span style='color: red;'>l</span>"
            + "<span style='color: orange;'>a</span>"
            + "<span style='color: yellow;'>y</span>"
            + "<span style='color: green;'>e</span>"
            + "<span style='color: blue;'>r</span>"
            + "</html>";
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 40);
    private static final int TITLE_X = 300;
    private static final int TITLE_Y = 10;
    private static final int TITLE_WIDTH = 400;
    private static final int TITLE_HEIGHT = 50;

    // Back button properties
    public static final String BACK_BUTTON_TEXT = "Back";
    public static final Color BACK_BUTTON_COLOR = Color.RED;
    public static final int BACK_BUTTON_X = 0;
    public static final int BACK_BUTTON_Y = 20;
    public static final int BACK_BUTTON_WIDTH = 175;
    public static final int BACK_BUTTON_HEIGHT = 40;

    // Constructor for MultiplayerUI
    public MultiplayerUI(GameController player1Controller, GameController player2Controller) {
        setLayout(null); // Manual positioning for components
        setOpaque(false);

        // Add the "Multiplayer" title
        JLabel titleLabel = createTitleLabel();
        add(titleLabel);

        // Initialize and add Player 1 board UI
        PlayerBoardUI player1Board = new PlayerBoardUI(player1Controller, "Player 1", PLAYER1_BOARD_X, PLAYER1_BOARD_Y);
        add(player1Board);

        // Initialize and add Player 2 board UI
        PlayerBoardUI player2Board = new PlayerBoardUI(player2Controller, "Player 2", PLAYER2_BOARD_X, PLAYER2_BOARD_Y);
        add(player2Board);

        // Create and add the back button
        JButton backButton = createBackButton();
        add(backButton);
    }

    // Creates the rainbow-colored "Multiplayer" title label
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel(RAINBOW_TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setBounds(TITLE_X, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);
        return titleLabel;
    }

    // Creates a back button to return to the StartMenu
    private JButton createBackButton() {
        JButton backButton = ButtonFactory.createColoredButton(BACK_BUTTON_TEXT, BACK_BUTTON_COLOR);
        backButton.setBounds(BACK_BUTTON_X, BACK_BUTTON_Y, BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);

        backButton.addActionListener(e -> {
            // Navigate back to the StartMenu
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new main.java.tetris.ui.startmenu.StartMenu());
            frame.revalidate();
            frame.repaint();
        });
        return backButton;
    }
}
