/*
 * GameController.java
 *
 * This class manages the core mechanics of the Tetris game, including piece spawning,
 * movement, line clearing, scoring, and game over logic. It handles user interactions
 * via key bindings and updates the UI accordingly.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/05/2024
 *
 * Usage:
 *   - Instantiate GameController with a map of key bindings for movement and actions.
 *   - Use setScoreLabel to link the score display to the game logic.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries for event handling and UI components.
 *   - GameBoard for managing the game state.
 *   - Movement for validating and executing piece movements.
 *   - GameBoardUI for rendering the game state and pieces.
 *
 * Constants:
 *   - MechanicsConstants: Shared constants like board dimensions and timer intervals.
 */

package main.java.tetris.mechanics;

import main.java.tetris.model.Piece;
import main.java.tetris.model.PieceType;
import main.java.tetris.model.GameBoard;
import main.java.tetris.ui.components.GameBoardUI;

import javax.swing.*;
import java.util.Map;
import java.awt.event.*;
import java.util.Random;

import static main.java.tetris.mechanics.MechanicsConstants.*;

public class GameController {

    private final GameBoard gameBoard;                  // Manages the state of the game board.
    private final Movement movement;                    // Handles movement logic for pieces.
    private final GameBoardUI gameBoardUI;              // Renders the game board and pieces.
    private final Map<Integer, Runnable> keyBindings;   // Maps key events to actions.

    private Piece currentPiece;                      // The piece currently controlled by the player.
    private Piece nextPiece;                         // The next piece to be placed on the board.
    private Timer timer;                             // Handles the automatic downward movement of pieces.
    private JLabel scoreLabel;                       // Displays the current score.
    private int score;                               // Tracks the player's current score.
    private final int LINE_CLEAR_SCORE;
    public static final int TIMER_INTERVAL_MS = 500; // Timer interval for game updates (milliseconds)

    // Game Over dialog messages
    public static final String GAME_OVER_MESSAGE = "Game Over! Do you want to play again?";
    public static final String GAME_OVER_TITLE = "Game Over";

    // Enum for Dialog Options
    private enum GameOverOption {
        RESTART,
        MAIN_MENU
    }

    public GameController(Map<Integer, Runnable> keyBindings) {
        this.gameBoard = new GameBoard();
        this.movement = new Movement(gameBoard);
        this.gameBoardUI = new GameBoardUI(gameBoard, null);
        this.keyBindings = keyBindings;
        this.score = 0;
        this.LINE_CLEAR_SCORE = 100;

        initKeyListener();
        spawnNewPiece();
        initTimer();
    }

    // Moves the current piece left if possible.
    public void moveLeft() {
        if (currentPiece != null && movement.moveLeft(currentPiece)) {
            gameBoardUI.repaint();
        }
    }

    // Moves the current piece right if possible.
    public void moveRight() {
        if (currentPiece != null && movement.moveRight(currentPiece)) {
            gameBoardUI.repaint();
        }
    }

    // Moves the current piece down, or locks it in place if it cannot move further.
    public void moveDown() {
        if (currentPiece != null) {
            if (!movement.moveDown(currentPiece)) {
                mergePieceToBoard();
                clearFullLines();
                spawnNewPiece();
            }
            gameBoardUI.repaint();
        }
    }

    // Rotates the current piece if possible.
    public void rotate() {
        if (currentPiece != null && movement.rotate(currentPiece)) {
            gameBoardUI.repaint();
        }
    }

    // Links the score display to the controller.
    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
        updateScoreDisplay();
    }

    // Returns the next piece to be displayed in the UI.
    public Piece getNextPiece() {
        return nextPiece;
    }

    // Spawns a new piece on the board and checks for game over conditions.
    private void spawnNewPiece() {
        if (nextPiece == null) {
            nextPiece = createRandomPiece();
        }

        currentPiece = nextPiece;
        nextPiece = createRandomPiece();

        gameBoardUI.setCurrentPiece(currentPiece);
        gameBoardUI.setNextPiece(nextPiece);

        if (!movement.canMove(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            handleGameOver();
        }
    }

    private void handleGameOver() {
        timer.stop(); // Stop the game timer

        // Ensure the dialog is displayed on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Show a dialog to the user with restart and main menu options
            int option = JOptionPane.showOptionDialog(gameBoardUI,
                    GAME_OVER_MESSAGE,
                    GAME_OVER_TITLE,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Restart", "Main Menu"},
                    "Restart"); //

            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else if (option == JOptionPane.NO_OPTION) {
                returnToMainMenu(); // Navigate to the main menu
            } else {
                returnToMainMenu();
            }
        });
    }

    // Navigates back to the main menu by replacing the current content pane.
    private void returnToMainMenu() {
        // Ensure this runs on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Get the parent JFrame
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(gameBoardUI);

            if (frame != null) {
                // Replace the current content pane with the main menu panel
                frame.getContentPane().removeAll();
                frame.getContentPane().add(new main.java.tetris.ui.startmenu.StartMenu());
                frame.revalidate(); // Refresh the JFrame to reflect changes
                frame.repaint();
            } else {
                // If the frame is not found, log an error or handle accordingly
                System.err.println("Unable to locate the parent JFrame.");
            }
        });
    }

    // Resets the game state to start a new game.
    private void resetGame() {
        SwingUtilities.invokeLater(() -> {
            gameBoard.clearBoard();
            score = 0;
            updateScoreDisplay();
            spawnNewPiece();
            timer.start();
            gameBoardUI.repaint();
            gameBoardUI.requestFocusInWindow(); // Ensure the game board regains focus
        });
    }

    // Updates the score display in the UI.
    private void updateScoreDisplay() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
    }

    // Initializes the game timer for automatic piece descent.
    private void initTimer() {
        timer = new Timer(TIMER_INTERVAL_MS, e -> {
            if (!movement.moveDown(currentPiece)) {
                mergePieceToBoard();
                clearFullLines();
                spawnNewPiece();
            }
            gameBoardUI.repaint();
        });
        timer.start();
    }

    // Initializes key listeners for controlling the game.
    private void initKeyListener() {
        gameBoardUI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentPiece == null) return;
                Runnable action = keyBindings.get(e.getKeyCode());
                if (action != null) {
                    action.run();
                }
            }
        });
        gameBoardUI.setFocusable(true);
        gameBoardUI.requestFocusInWindow();
    }

    // Merges the current piece into the game board.
    private void mergePieceToBoard() {
        int[][] shape = currentPiece.getShape();
        int xPos = currentPiece.getX();
        int yPos = currentPiece.getY();
        int pieceIndex = currentPiece.getType().ordinal() + 1;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    gameBoard.setCell(xPos + j, yPos + i, pieceIndex);
                }
            }
        }
    }

    // Clears any full lines on the board and updates the score.
    private void clearFullLines() {
        for (int y = BOARD_HEIGHT - 1; y >= 0; y--) {
            if (isFullLine(y)) {
                clearLine(y);
                y++; // Check the same line again since it was shifted.
                score += LINE_CLEAR_SCORE;
                updateScoreDisplay();
            }
        }
    }

    // Checks if a given line is full.
    private boolean isFullLine(int y) {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            if (gameBoard.getCell(x, y) == 0) {
                return false;
            }
        }
        return true;
    }

    // Clears a full line and shifts the above lines down.
    private void clearLine(int y) {
        for (int i = y; i > 0; i--) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                gameBoard.setCell(x, i, gameBoard.getCell(x, i - 1));
            }
        }
        for (int x = 0; x < BOARD_WIDTH; x++) {
            gameBoard.setCell(x, 0, 0);
        }
    }

    // Creates a random Tetris piece.
    private Piece createRandomPiece() {
        return new Piece(PieceType.values()[new Random().nextInt(PieceType.values().length)]);
    }

    // Returns the game board UI component.
    public GameBoardUI getGameBoardUI() {
        return gameBoardUI;
    }
}
