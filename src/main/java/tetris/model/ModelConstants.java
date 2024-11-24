/*
 * ModelConstants.java
 *
 * This class defines shared constants used across model-related classes in the Tetris game.
 * It centralizes configuration values for easier management and maintenance.
 *
 * Last Updated Date: 11/24/2024
 */

package main.java.tetris.model;

public class ModelConstants {
    public static final int BOARD_WIDTH = 10;   // Width of the game board (number of columns)
    public static final int BOARD_HEIGHT = 20;  // Height of the game board (number of rows)

    // Tetromino shapes as 3D arrays
    public static final int[][][] SHAPES = {
            // I-shape
            {{1, 1, 1, 1}},
            // J-shape
            {{1, 0, 0},
                    {1, 1, 1}},
            // L-shape
            {{0, 0, 1},
                    {1, 1, 1}},
            // O-shape
            {{1, 1},
                    {1, 1}},
            // S-shape
            {{0, 1, 1},
                    {1, 1, 0}},
            // T-shape
            {{0, 1, 0},
                    {1, 1, 1}},
            // Z-shape
            {{1, 1, 0},
                    {0, 1, 1}}
    };
}
