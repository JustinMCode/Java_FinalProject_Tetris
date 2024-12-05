/*
 * GameBoardUI.java
 *
 * This class handles the graphical representation of the Tetris game board and its components.
 * It displays the current state of the board, the active piece, and renders each cell with
 * appropriate colors and borders.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/03/2024
 *
 * Features:
 * - Dynamically renders the game board and active piece.
 * - Supports property change listeners to notify changes in the next piece.
 * - Customizable cell size and board dimensions via constants.
 *
 * Dependencies:
 * - GameBoard for the state of the game board.
 * - Piece and PieceType for active piece and color information.
 */

package main.java.tetris.ui.components;

import main.java.tetris.model.Piece;
import main.java.tetris.model.GameBoard;
import main.java.tetris.model.PieceType;

import java.awt.*;
import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static main.java.tetris.model.ModelConstants.*;

public class GameBoardUI extends JPanel {

    private static final int CELL_SIZE = 30; // Cell size in pixels
    private static final Color BACKGROUND_COLOR = Color.GRAY; // Default background color

    private final GameBoard gameBoard;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Piece currentPiece;
    private Piece nextPiece;

    // Constructor for the GameBoardUI.
    public GameBoardUI(GameBoard gameBoard, Piece currentPiece) {
        this.gameBoard = gameBoard;
        this.currentPiece = currentPiece;

        // Calculate dimensions based on board size and cell size
        int width = BOARD_WIDTH * CELL_SIZE;
        int height = BOARD_HEIGHT * CELL_SIZE;
        Dimension size = new Dimension(width, height);

        // Configure panel size and appearance
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(BACKGROUND_COLOR);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    // Adds a PropertyChangeListener for changes to properties such as "nextPiece."
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    // Removes a PropertyChangeListener.
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    // Sets the currently active piece and triggers a repaint.
    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
        repaint();
    }

    // Sets the next piece and notifies listeners of the change.
    public void setNextPiece(Piece nextPiece) {
        Piece oldPiece = this.nextPiece;
        this.nextPiece = nextPiece;
        pcs.firePropertyChange("nextPiece", oldPiece, nextPiece);
    }

    // Renders the game board and the current piece.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the game board cells
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                int cellValue = gameBoard.getCell(x, y);
                if (cellValue != 0) {
                    PieceType type = PieceType.values()[cellValue - 1];
                    drawCell(g, x, y, type.getColor());
                }
            }
        }

        // Draw the current active piece
        if (currentPiece != null) {
            drawPiece(g, currentPiece);
        }
    }

    // Draws a piece on the board.
    private void drawPiece(Graphics g, Piece piece) {
        int[][] shape = piece.getShape();
        Color color = piece.getColor();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    drawCell(g, piece.getX() + j, piece.getY() + i, color);
                }
            }
        }
    }

    // Draws a single cell on the board.
    private void drawCell(Graphics g, int x, int y, Color color) {
        int xPos = x * CELL_SIZE;
        int yPos = y * CELL_SIZE;
        g.setColor(color);
        g.fillRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
        g.setColor(color.darker());
        g.drawRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
    }
}

