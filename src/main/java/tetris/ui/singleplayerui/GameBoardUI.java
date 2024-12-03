package main.java.tetris.ui.singleplayerui;

import main.java.tetris.model.GameBoard;
import main.java.tetris.model.Piece;
import main.java.tetris.model.PieceType;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static main.java.tetris.model.ModelConstants.*;

public class GameBoardUI extends JPanel {
    private final GameBoard gameBoard;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Piece currentPiece;
    private Piece nextPiece;

    private static final int CELL_SIZE = 30;

    public GameBoardUI(GameBoard gameBoard, Piece currentPiece) {
        this.gameBoard = gameBoard;
        this.currentPiece = currentPiece;

        int width = BOARD_WIDTH * CELL_SIZE;
        int height = BOARD_HEIGHT * CELL_SIZE;
        Dimension size = new Dimension(width, height);

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        setFocusable(true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
        repaint();
    }

    public void setNextPiece(Piece nextPiece) {
        Piece oldPiece = this.nextPiece;
        this.nextPiece = nextPiece;
        pcs.firePropertyChange("nextPiece", oldPiece, nextPiece);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                int cellValue = gameBoard.getCell(x, y);
                if (cellValue != 0) {
                    PieceType type = PieceType.values()[cellValue - 1];
                    drawCell(g, x, y, type.getColor());
                }
            }
        }

        if (currentPiece != null) {
            drawPiece(g, currentPiece);
        }
    }

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

    private void drawCell(Graphics g, int x, int y, Color color) {
        int xPos = x * CELL_SIZE;
        int yPos = y * CELL_SIZE;
        g.setColor(color);
        g.fillRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
        g.setColor(color.darker());
        g.drawRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
    }
}
