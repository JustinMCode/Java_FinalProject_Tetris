/*
 * StartMenuActionHandler.java
 *
 * This class handles button actions in the StartMenu of the Tetris game. It listens
 * for user interactions with menu buttons and executes the corresponding actions,
 * such as starting the single-player mode, opening the options menu, or exiting the game.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/24/2024
 *
 * Usage:
 *   - Attach an instance of this class to a MenuPanel to handle button actions.
 *   - Actions are defined in the ButtonAction enum and executed based on the action command.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries for handling UI events
 *   - ButtonAction enum for defining button actions
 *   - SinglePlayer for single-player game mode
 *   - Options for the options menu
 */

package main.java.tetris.ui.startmenu;

import main.java.tetris.Game.MultiPlayer;
import main.java.tetris.Game.Options;
import main.java.tetris.Game.SinglePlayer;
import main.java.tetris.ui.components.ButtonAction;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;

public class StartMenuActionHandler implements ActionListener {
    private final StartMenu startMenu;

    public StartMenuActionHandler(StartMenu startMenu) {
        this.startMenu = startMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ButtonAction action = ButtonAction.valueOf(e.getActionCommand());

            switch (action) {
                case SINGLEPLAYER -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(startMenu);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new SinglePlayer());
                    frame.revalidate();
                    frame.repaint();
                }
                case MULTIPLAYER -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(startMenu);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new MultiPlayer());
                    frame.revalidate();
                    frame.repaint();
                }
                case OPTIONS -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(startMenu);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(new Options());
                    frame.revalidate();
                    frame.repaint();
                }
                case CREDITS -> System.out.println("Credits not implemented");
                case EXIT -> System.exit(0);
            }
        } catch (IllegalArgumentException ex) {
            System.err.println("Unknown action command: " + e.getActionCommand());
        }
    }
}
