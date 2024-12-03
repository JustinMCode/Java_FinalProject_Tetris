package main.java.tetris.mechanics;

import main.java.tetris.model.Piece;
import main.java.tetris.model.PieceType;
import main.java.tetris.model.GameBoard;
import main.java.tetris.ui.singleplayerui.GameBoardUI;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

import static main.java.tetris.mechanics.MechanicsConstants.*;

public class GameController {

    private final GameBoard gameBoard;
    private Piece currentPiece;
    private Piece nextPiece;
    private final Movement movement;
    private final GameBoardUI gameBoardUI;
    private Timer timer;
    private JLabel scoreLabel;
    private int score;

    public GameController() {
        this.gameBoard = new GameBoard();
        this.movement = new Movement(gameBoard);
        this.gameBoardUI = new GameBoardUI(gameBoard, null);
        this.score = 0;
        initKeyListener();
        spawnNewPiece();
        initTimer();
    }

    public void setScoreLabel(JLabel scoreLabel) {
        this.scoreLabel = scoreLabel;
        updateScoreDisplay();
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    private void spawnNewPiece() {
        if (nextPiece == null) {
            nextPiece = new Piece(PieceType.values()[new Random().nextInt(PieceType.values().length)]);
        }

        currentPiece = nextPiece;
        nextPiece = new Piece(PieceType.values()[new Random().nextInt(PieceType.values().length)]);

        gameBoardUI.setCurrentPiece(currentPiece);
        gameBoardUI.setNextPiece(nextPiece); // Triggers property change

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
        score = 0;
        updateScoreDisplay();
        spawnNewPiece();
        timer.start();
        gameBoardUI.repaint();
    }

    private void updateScoreDisplay() {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
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
                score += 100;
                updateScoreDisplay();
            }
        }
    }

    public GameBoardUI getGameBoardUI() {
        return gameBoardUI;
    }
}
