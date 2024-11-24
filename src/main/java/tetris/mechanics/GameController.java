/*
 * GameController.java
 *
 * This class controls the game logic for Tetris, including piece movement, rotation,
 * collision detection, and game board updates. It also manages the game state and user input.
 *
 * Author: Justin Morgan, Lauren Greg
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate GameController to manage the game mechanics and UI updates.
 *   - The controller initializes the game board, spawns pieces, and handles user input.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - GameBoard for managing the game grid
 *   - GameBoardUI for rendering the game board and pieces
 *   - Piece and PieceType for handling individual game pieces
 *   - Movement for collision detection and movement logic
 */

package main.java.tetris.mechanics;

import main.java.tetris.model.Piece;
import main.java.tetris.model.PieceType;
import main.java.tetris.model.GameBoard;
import main.java.tetris.ui.singleplayerui.GameBoardUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

import static main.java.tetris.mechanics.MechanicsConstants.*;

/*
 * GameController class manages the core game logic, including piece spawning, movement,
 * line clearing, and user input handling.
 */
public class GameController {

    private final GameBoard gameBoard;
    private Piece currentPiece;
    private final Movement movement;
    private final GameBoardUI gameBoardUI;
    private Timer timer;

    /*
     * Constructor: Initializes the game controller, sets up the game board and UI components,
     * and starts the game loop.
     */
    public GameController() {
        this.gameBoard = new GameBoard();
        this.movement = new Movement(gameBoard);
        this.gameBoardUI = new GameBoardUI(gameBoard, null);
        initKeyListener();
        spawnNewPiece();
        initTimer();
    }

    private void spawnNewPiece() {
        // Select a random piece type
        PieceType type = PieceType.values()[new Random().nextInt(PieceType.values().length)];
        currentPiece = new Piece(type);
        gameBoardUI.setCurrentPiece(currentPiece);

        // Check if the new piece can be placed on the board
        if (!movement.canMove(currentPiece, currentPiece.getX(), currentPiece.getY())) {
            timer.stop();
            int option = JOptionPane.showOptionDialog(gameBoardUI,
                    GAME_OVER_MESSAGE,
                    GAME_OVER_TITLE,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    null,
                    null);
            if (option == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
        }
    }

    private void resetGame() {
        gameBoard.clearBoard();
        spawnNewPiece();
        timer.start();
        gameBoardUI.repaint();
    }

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

    private void initKeyListener() {
        gameBoardUI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentPiece == null) return;
                boolean repaint = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> repaint = movement.moveLeft(currentPiece);
                    case KeyEvent.VK_RIGHT -> repaint = movement.moveRight(currentPiece);
                    case KeyEvent.VK_DOWN -> {
                        if (!movement.moveDown(currentPiece)) {
                            mergePieceToBoard();
                            clearFullLines();
                            spawnNewPiece();
                        }
                        repaint = true;
                    }
                    case KeyEvent.VK_UP -> repaint = movement.rotate(currentPiece);
                }
                if (repaint) gameBoardUI.repaint();
            }
        });
        gameBoardUI.setFocusable(true);
        gameBoardUI.requestFocusInWindow();
    }

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

    private void clearFullLines() {
        for (int y = BOARD_HEIGHT - 1; y >= 0; y--) {
            boolean fullLine = true;
            for (int x = 0; x < BOARD_WIDTH; x++) {
                if (gameBoard.getCell(x, y) == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                for (int i = y; i > 0; i--) {
                    for (int x = 0; x < BOARD_WIDTH; x++) {
                        gameBoard.setCell(x, i, gameBoard.getCell(x, i - 1));
                    }
                }
                for (int x = 0; x < BOARD_WIDTH; x++) {
                    gameBoard.setCell(x, 0, 0);
                }
                y++;
            }
        }
    }

    public GameBoardUI getGameBoardUI() {
        return gameBoardUI;
    }
}
