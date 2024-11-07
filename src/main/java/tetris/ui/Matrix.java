import javax.swing.*;
import java.awt.*;

public class Matrix extends JPanel {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 20;
    private static final int CELL_SIZE = 30;
    private int[][] tetris_grid;

    public Matrix() {
        tetris_grid = new int[BOARD_WIDTH][BOARD_HEIGHT];
        createBoard();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int rows = 0; rows < BOARD_WIDTH; rows++) {
            for (int cols = 0; cols < BOARD_HEIGHT; cols++) {
                drawCell(g, rows, cols);
            }
        }
    }

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
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris Game");
        Matrix matrix = new Matrix();
        //frame.setContentPane(matrix);
        frame.add(matrix);
        frame.setSize(BOARD_WIDTH * CELL_SIZE + 16, BOARD_HEIGHT * CELL_SIZE + 39);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}