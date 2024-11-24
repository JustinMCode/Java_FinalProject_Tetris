// Can get removed, was broken up for UI purposes, the game board is created in /model/GameBoard

package main.java.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class Matrix extends JPanel {
    //Declaring constant values
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 30;
    //creating tetris grid
    private final int[][] tetris_grid;

    //Create the matrix board
    public Matrix() {
        tetris_grid = new int[BOARD_WIDTH][BOARD_HEIGHT];
        createBoard();
        setPreferredSize(new Dimension(BOARD_WIDTH*CELL_SIZE, BOARD_HEIGHT*CELL_SIZE));
    }

    //Create empty board
    public void createBoard() {
        for (int rows = 0; rows < BOARD_WIDTH; rows++) {
            for (int cols = 0; cols < BOARD_HEIGHT; cols++) {
                tetris_grid[rows][cols] = 0;
            }
        }
    }

    @Override
    //draw the number of rows and columns for entire width and height
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int rows = 0; rows < BOARD_WIDTH; rows++) {
            for (int cols = 0; cols < BOARD_HEIGHT; cols++) {
                drawCell(g, rows, cols);
            }
        }
    }

    //draw each cell in the matrix
    private void drawCell(Graphics g, int rows, int cols) {
        int x = rows * CELL_SIZE;
        int y = cols * CELL_SIZE;
        if (tetris_grid[rows][cols] == 0) {
            g.setColor(Color.LIGHT_GRAY);
        }
        else {
            g.setColor(Color.RED);
        }
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        //g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    public static void main(String[] args) {
        //create frame and matrix panel
        JFrame frame = new JFrame("Tetris");
        Matrix matrix = new Matrix();
        JPanel matrixPanel = new JPanel();

        //create panel for the score
        JPanel scorePanel = new JPanel();
        scorePanel.setPreferredSize(new Dimension(BOARD_WIDTH*CELL_SIZE, CELL_SIZE));

        //create score field (default for now)
        JTextField scoreField = new JTextField("Score: 0", 10);
        scoreField.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreField.setEditable(false);
        scorePanel.add(scoreField);

        //add matrix to panel
        matrixPanel.add(matrix);

        //set position of panels
        frame.setLayout(new BorderLayout());
        frame.add(matrixPanel, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.NORTH);

        frame.setSize(BOARD_WIDTH * CELL_SIZE + 16, BOARD_HEIGHT * CELL_SIZE + 70);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}