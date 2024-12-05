package main.java.tetris.model;

import java.awt.Color;
import static main.java.tetris.model.ModelConstants.*;

/*
 * Piece.java
 *
 * This class represents a Tetris game piece (Tetromino). Each piece has a specific shape,
 * type, position on the game board, and behavior for movement and rotation.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate Piece with a specific PieceType to create a new Tetromino.
 *   - Use movement methods (moveLeft, moveRight, moveDown) to control the piece's position.
 *   - Use rotation methods (rotate, rotateBack) to change the orientation of the piece.
 *
 * Dependencies:
 *   - ModelConstants for shared constants
 *   - PieceType for defining the type and color of the piece
 */
public class Piece {

    private final PieceType type;
    private int[][] shape;
    private int x, y;

    /*
     * Constructor: Creates a new Piece of a given type.
     * Initializes the shape, starting position, and type of the piece.
     */
    private static final int STARTING_Y = 0;
    private static final int COUNTER_ROTATION_STEPS = 3;

    public Piece(PieceType type) {
        this.type = type;
        this.shape = SHAPES[type.ordinal()];
        this.x = BOARD_WIDTH / 2 - shape[0].length / 2; // Center the piece horizontally
        this.y = STARTING_Y; // Start at the top of the board
    }

    // Retrieves the shape matrix of the piece.
    public int[][] getShape() {
        return shape;
    }

    // Retrieves the width of the piece (number of columns in the shape matrix).
    public int getWidth() {
        return shape[0].length;
    }

    // Retrieves the height of the piece (number of rows in the shape matrix).
    public int getHeight() {
        return shape.length;
    }

    // Retrieves the X position of the piece on the game board.
    public int getX() {
        return x;
    }

    // Retrieves the Y position of the piece on the game board.
    public int getY() {
        return y;
    }

    // Retrieves the type of the piece.
    public PieceType getType() {
        return type;
    }

    // Retrieves the color associated with the piece type.
    public Color getColor() {
        return type.getColor();
    }

    // Moves the piece one row down on the game board.
    public void moveDown() {
        y++;
    }

    // Moves the piece one column to the left on the game board.
    public void moveLeft() {
        x--;
    }

    // Moves the piece one column to the right on the game board.
    public void moveRight() {
        x++;
    }

    // Rotates the piece 90 degrees clockwise.
    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotatedShape = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedShape[j][rows - 1 - i] = shape[i][j];
            }
        }
        shape = rotatedShape;
    }

    // Rotates the piece back to its previous orientation (90 degrees counterclockwise).
    public void rotateBack() {
        for (int i = 0; i < COUNTER_ROTATION_STEPS; i++) {
            rotate();
        }
    }
}
