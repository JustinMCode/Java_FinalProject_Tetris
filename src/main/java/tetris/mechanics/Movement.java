/*
 * Movement.java
 *
 * This class provides methods to handle the movement and rotation of Tetris pieces.
 * It checks if a piece can move or rotate based on the current state of the game board.
 * Movement ensures that pieces adhere to game boundaries and do not overlap existing pieces.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate Movement with a GameBoard object to manage piece movements.
 *   - Use methods like moveLeft, moveRight, moveDown, and rotate to control the pieces.
 *
 * Dependencies:
 *   - GameBoard for board state and collision checking
 *   - Piece for handling piece shape, position, and rotation
 */

package main.java.tetris.mechanics;

import main.java.tetris.model.Piece;
import main.java.tetris.model.GameBoard;

import static main.java.tetris.mechanics.MechanicsConstants.*;

/*
 * Movement class handles the movement and rotation of Tetris pieces.
 * It interacts with the GameBoard to ensure valid movements and prevent collisions.
 */
public class Movement {

    private final GameBoard gameBoard;

    // Constructor: Initializes the Movement class with a reference to the game board.
    public Movement(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    // Checks if a piece can move to a specified position on the board.
    public boolean canMove(Piece piece, int newX, int newY) {

        // Get the shape matrix of the piece
        int[][] shape = piece.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                // Check only non-empty cells in the piece
                if (shape[i][j] != 0) {
                    int x = newX + j;   // Calculate the absolute X position on the board
                    int y = newY + i;   // Calculate the absolute Y position on the board

                    // Check if the position is out of bounds
                    if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                        return false; // Invalid move: out of bounds
                    }

                    // Check if the position overlaps an existing cell on the board
                    if (gameBoard.getCell(x, y) != 0) {
                        return false; // Invalid move: collision detected
                    }
                }
            }
        }
        return true; // Movement is valid
    }

    // Moves the piece one cell to the left if possible.
    public boolean moveLeft(Piece piece) {

        // Check if the move is valid
        if (canMove(piece, piece.getX() - 1, piece.getY())) {
            piece.moveLeft();
            return true;
        }
        return false; // Move not allowed
    }

    // Moves the piece one cell to the right if possible.
    public boolean moveRight(Piece piece) {
        // Check if the move is valid
        if (canMove(piece, piece.getX() + 1, piece.getY())) {
            piece.moveRight();
            return true;
        }
        return false; // Move not allowed
    }

    // Moves the piece one cell downward if possible.
    public boolean moveDown(Piece piece) {
        // Check if the move is valid
        if (canMove(piece, piece.getX(), piece.getY() + 1)) {
            piece.moveDown();
            return true;
        }
        return false; // Move not allowed
    }

    /*
     * Rotates the piece clockwise if possible.
     * If the rotation is invalid (e.g., overlaps other pieces or boundaries),
     * the piece will revert to its original orientation.
     */
    public boolean rotate(Piece piece) {
        // Attempt to rotate the piece
        piece.rotate();

        // Check if the rotation is valid
        if (!canMove(piece, piece.getX(), piece.getY())) {
            piece.rotateBack(); // Revert the rotation if invalid
            return false;
        }
        return true; // Rotation successful
    }
}