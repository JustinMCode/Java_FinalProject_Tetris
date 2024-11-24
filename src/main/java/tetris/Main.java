/*
 * Main.java
 *
 * This is the entry point for the Tetris game application. It initializes
 * the StartMenu and ensures that the UI is created on the Event Dispatch Thread (EDT).
 *
 * Authors: Daniyar Alimkhanov, Lauren Gregory, Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Run this class to start the Tetris application.
 *   - The StartMenu is displayed as the main menu interface.
 *
 * Dependencies:
 *   - Java Swing library for GUI components
 *   - StartMenu as the main menu UI component
 */

package main.java.tetris;

import main.java.tetris.ui.startmenu.StartMenu;

import javax.swing.*;

public class Main {

    /*
     * The main method, which serves as the entry point for the Tetris application.
     * It creates the StartMenu on the Event Dispatch Thread (EDT) to ensure thread safety.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the main JFrame
            JFrame frame = new JFrame("Tetris");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setLocationRelativeTo(null);

            // Set the StartMenu as the initial content pane
            frame.setContentPane(new StartMenu());
            frame.setVisible(true);
        });
    }
}
