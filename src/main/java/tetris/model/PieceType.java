/*
 * PieceType.java
 *
 * This enum represents the different types of Tetrominoes used in Tetris.
 * Each PieceType is associated with a specific color, which is used for rendering the pieces in the game.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Use PieceType values (I, J, L, O, S, T, Z) to define the type of a Tetris piece.
 *   - Retrieve the color associated with a PieceType using the getColor() method.
 *
 * Dependencies:
 *   - Java AWT Color class for color representation
 */

package main.java.tetris.model;

import java.awt.Color;

// The PieceType enum defines the seven Tetromino types, each with a unique color.
public enum PieceType {
    I(Color.CYAN),      // Light Blue - I-shaped Tetromino
    J(Color.BLUE),      // Blue - J-shaped Tetromino
    L(Color.ORANGE),    // Orange - L-shaped Tetromino
    O(Color.YELLOW),    // Yellow - O-shaped Tetromino
    S(Color.GREEN),     // Green - S-shaped Tetromino
    T(Color.MAGENTA),   // Magenta - T-shaped Tetromino
    Z(Color.RED);       // Red - Z-shaped Tetromino

    private final Color color;

    // Constructor: Associates a specific color with each PieceType.
    PieceType(Color color) {
        this.color = color;
    }

    // Retrieves the color associated with the PieceType.
    public Color getColor() {
        return color;
    }
}
