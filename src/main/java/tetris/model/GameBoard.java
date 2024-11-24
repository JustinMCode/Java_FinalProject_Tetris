/*
 * GameBoard.java
 *
 * This class represents the game board for Tetris. It defines the grid dimensions,
 * stores the state of each cell, and provides methods to manage the grid.
 * The game board keeps track of placed pieces and supports operations like clearing the board
 * and updating individual cells.
 *
 * Author: Lauren Greg
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Instantiate GameBoard to create a new game grid.
 *   - Use methods like setCell, getCell, and clearBoard to manage the grid state.
 *
 * Dependencies:
 *   - ModelConstants for board dimensions
 */

package main.java.tetris.model;

import static main.java.tetris.model.ModelConstants.*;

public class GameBoard {

    // 2D array representing the game board grid
    private final int[][] grid;

    // Constructor
    public GameBoard() {
        grid = new int[BOARD_WIDTH][BOARD_HEIGHT]; // Initialize grid with predefined dimensions
        clearBoard(); // Clear the grid
    }

    /*
     * Clears the entire game board by setting all cells to 0 (empty).
     * This method is used to reset the board at the start of a game or after a game over.
     */
    public void clearBoard() {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                grid[x][y] = 0; // Set each cell to empty
            }
        }
    }

    // Retrieves the entire grid representing the game board.
    public int[][] getGrid() {
        return grid;
    }

    // Sets the value of a specific cell in the grid.
    public void setCell(int x, int y, int value) {
        grid[x][y] = value; // Update the specified cell
    }

    // Retrieves the value of a specific cell in the grid.
    public int getCell(int x, int y) {
        return grid[x][y];
    }
}
