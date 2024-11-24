/*
 * GameBoardUI.java
 *
 * This class handles all rendering of the Tetris game board and pieces.
 * It uses the game board model to draw settled pieces and the current moving piece on the screen.
 * Each cell is rendered as a colored square with an optional border for better visibility.
 *
 * Author: Justin Morgan, Lauren Greg
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate GameBoardUI with a GameBoard and optionally the current piece.
 *   - Call setCurrentPiece to update the currently active piece being rendered.
 *   - The paintComponent method automatically redraws the game board and pieces during gameplay.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries for rendering
 *   - GameBoard for the state of the grid
 *   - Piece and PieceType for handling the current piece and its color
 *   - ModelConstants for board dimensions
 */

package main.java.tetris.ui.singleplayerui;

import main.java.tetris.model.Piece;
import main.java.tetris.model.PieceType;
import main.java.tetris.model.GameBoard;
import static main.java.tetris.model.ModelConstants.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/*
 * GameBoardUI class manages the visual representation of the game board and pieces.
 * It is a Swing JPanel that dynamically redraws the board and current piece based on the game state.
 */
public class GameBoardUI extends JPanel {

    private final GameBoard gameBoard;
    private Piece currentPiece;

    private static final int CELL_SIZE = 30;

    /*
     * Constructor: Initializes the GameBoardUI with the game board and the current piece.
     * Sets up the panel size, background color, border, and rendering optimizations.
     */
    public GameBoardUI(GameBoard gameBoard, Piece currentPiece) {
        this.gameBoard = gameBoard;
        this.currentPiece = currentPiece;

        // Calculate panel dimensions based on board size and cell size
        int width = BOARD_WIDTH * CELL_SIZE;
        int height = BOARD_HEIGHT * CELL_SIZE;
        Dimension size = new Dimension(width, height);

        // Set panel dimensions and properties
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(Color.GRAY);
        setDoubleBuffered(true);    // Enable double buffering for smoother rendering
        setFocusable(true);         // Allows the panel to receive keyboard input

        // Add a black border around the game board
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        setBorder(lineBorder);
    }

    // Updates the current active piece being rendered.
    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    /*
     * Overrides the paintComponent method to render the game board and current piece.
     * Draws settled pieces based on the game board grid and the current piece in motion.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Call superclass to clear the panel
        super.paintComponent(g);

        // Draw settled pieces based on the game board grid
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                int cellValue = gameBoard.getCell(x, y);

                // Non-zero values represent placed pieces
                if (cellValue != 0) {
                    PieceType type = PieceType.values()[cellValue - 1];
                    Color cellColor = type.getColor();      // Get the color associated with the piece type
                    drawCell(g, x, y, cellColor);           // Draw the cell with the appropriate color
                }
            }
        }

        // Draw the current piece if it exists
        if (currentPiece != null) {
            int[][] shape = currentPiece.getShape();
            int xPos = currentPiece.getX();
            int yPos = currentPiece.getY();
            Color pieceColor = currentPiece.getColor();

            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[0].length; j++) {
                    // Only draw non-empty cells
                    if (shape[i][j] != 0) {
                        int x = xPos + j;      // Calculate the cell's X position on the board
                        int y = yPos + i;      // Calculate the cell's Y position on the board
                        // Ensure the cell is within the visible grid
                        if (y >= 0) {
                            drawCell(g, x, y, pieceColor);
                        }
                    }
                }
            }
        }
    }

    /*
     * Draws a single cell at the specified grid coordinates with the given color.
     * The cell is drawn as a filled rectangle with an optional border for visibility.
     */
    private void drawCell(Graphics g, int x, int y, Color color) {
        int xPos = x * CELL_SIZE;
        int yPos = y * CELL_SIZE;
        g.setColor(color);
        g.fillRect(xPos, yPos, CELL_SIZE, CELL_SIZE);

        // Draw a darker border around the cell for better visibility
        g.setColor(color.darker());
        g.drawRect(xPos, yPos, CELL_SIZE, CELL_SIZE);
    }
}
