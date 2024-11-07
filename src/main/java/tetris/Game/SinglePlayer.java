package main.java.tetris.Game;

import main.java.tetris.audio.AudioManager;

import javax.swing.*;
import java.awt.*;

public class SinglePlayer extends JPanel {
    private static int width = 10;
    private static int height = 20;
    private static int cell = 30;

    private int[][] board;
    private boolean GameOver;

    public SinglePlayer() {
        setPreferredSize(new Dimension(width*cell, height*cell));
        setBackground(Color.GRAY);
        board = new int [width][height];
        GameOver = false;

    }
}
