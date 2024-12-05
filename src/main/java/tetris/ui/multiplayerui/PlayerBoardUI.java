/*
 * PlayerBoardUI.java
 *
 * This class represents the individual player board in Multiplayer mode.
 * It includes the game board, a score panel, and a next-piece panel,
 * all organized within a single UI component.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/03/2024
 *
 * Features:
 * - Game board rendering for the current state.
 * - Score display panel linked to the GameController.
 * - Next-piece preview panel for the upcoming Tetris piece.
 *
 * Dependencies:
 * - GameController for managing game logic and state.
 * - GameBoardUI for rendering the game board.
 */
package main.java.tetris.ui.multiplayerui;

import main.java.tetris.mechanics.GameController;
import main.java.tetris.ui.components.GameBoardUI;

import javax.swing.*;
import java.awt.*;

public class PlayerBoardUI extends JPanel {

    private final GameBoardUI gameBoardUI;

    private static final int CELL_SIZE = 30;
    private static final int GAME_BOARD_WIDTH = 300;
    private static final int GAME_BOARD_HEIGHT = 600;
    private static final int PANEL_WIDTH = 200;
    private static final int PANEL_HEIGHT = 50;

    public PlayerBoardUI(GameController gameController, String playerName, int x, int y) {
        setLayout(null);
        // Extend width and height to accommodate next piece and score panels
        setBounds(x, y, GAME_BOARD_WIDTH + 50, GAME_BOARD_HEIGHT + PANEL_HEIGHT + 60);
        setOpaque(false);

        // Game board setup
        gameBoardUI = gameController.getGameBoardUI();
        gameBoardUI.setBounds(0, 0, GAME_BOARD_WIDTH, GAME_BOARD_HEIGHT);
        gameBoardUI.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Score panel setup (lower left of the game board)
        JPanel scorePanel = createScorePanel(gameController, playerName);
        scorePanel.setBounds(0, GAME_BOARD_HEIGHT, GAME_BOARD_WIDTH / 2, PANEL_HEIGHT);

        // Next piece panel setup (top right of game board)
        JPanel nextPiecePanel = createNextPiecePanel(gameController);
        nextPiecePanel.setBounds(150, GAME_BOARD_HEIGHT - PANEL_HEIGHT + 50, 150, PANEL_HEIGHT);

        // Add components
        add(scorePanel);
        add(nextPiecePanel);
        add(gameBoardUI);
    }

    private JPanel createScorePanel(GameController gameController, String playerName) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel scoreLabel = new JLabel(playerName + " Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(scoreLabel, BorderLayout.CENTER);

        gameController.setScoreLabel(scoreLabel); // Connect score label to GameController

        return panel;
    }

    private JPanel createNextPiecePanel(GameController gameController) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (gameController.getNextPiece() != null) {
                    drawNextPiece(g, gameController.getNextPiece());
                }
            }

            private void drawNextPiece(Graphics g, main.java.tetris.model.Piece nextPiece) {
                int[][] shape = nextPiece.getShape();
                Color color = nextPiece.getColor();

                int pieceWidth = shape[0].length;
                int pieceHeight = shape.length;
                int offsetX = (getWidth() - pieceWidth * CELL_SIZE) / 2;
                int offsetY = (getHeight() - pieceHeight * CELL_SIZE) / 2;

                for (int i = 0; i < pieceHeight; i++) {
                    for (int j = 0; j < pieceWidth; j++) {
                        if (shape[i][j] != 0) {
                            int x = offsetX + j * CELL_SIZE;
                            int y = offsetY + i * CELL_SIZE;
                            g.setColor(color);
                            g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                            g.setColor(color.darker());
                            g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                        }
                    }
                }
            }
        };

        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(Color.LIGHT_GRAY);

        // Refresh next piece panel on change
        gameController.getGameBoardUI().addPropertyChangeListener(evt -> {
            if ("nextPiece".equals(evt.getPropertyName())) {
                panel.repaint();
            }
        });

        return panel;
    }

    public GameBoardUI getGameBoardUI() {
        return gameBoardUI;
    }
}
