package main.java.tetris.ui.gameoverUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameOverUI.java
 *
 * This class represents a custom dialog displayed when the game is over.
 * It provides options to restart the game or return to the main menu with customized buttons.
 *
 * Author: Justin Morgan
 * Last Updated Date: 12/05/2024
 */
public class GameOverUI extends JDialog {
    private JButton restartButton;
    private JButton mainMenuButton;

    /**
     * Constructor for the GameOverUI dialog.
     *
     * @param parentFrame The parent JFrame to which the dialog is relative.
     * @param onRestart   Runnable action to execute when the "Restart" button is clicked.
     * @param onMainMenu  Runnable action to execute when the "Main Menu" button is clicked.
     */
    public GameOverUI(JFrame parentFrame, Runnable onRestart, Runnable onMainMenu) {
        super(parentFrame, "Game Over", true);
        initComponents(onRestart, onMainMenu);
    }

    /**
     * Initializes the components of the dialog.
     *
     * @param onRestart  Action to perform on restart.
     * @param onMainMenu Action to perform on returning to the main menu.
     */
    private void initComponents(Runnable onRestart, Runnable onMainMenu) {
        // Set dialog properties
        setSize(350, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(getParent());

        // Message Panel
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.DARK_GRAY);
        JLabel messageLabel = new JLabel("Game Over! Do you want to play again?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setForeground(Color.WHITE);
        messagePanel.add(messageLabel);
        add(messagePanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.DARK_GRAY);
        buttonsPanel.setLayout(new FlowLayout());

        // Customize Restart Button
        restartButton = new JButton("Restart");
        customizeButton(restartButton, new Color(0, 153, 0), Color.WHITE, new Font("Arial", Font.BOLD, 14));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRestart.run();
                dispose();
            }
        });

        // Customize Main Menu Button
        mainMenuButton = new JButton("Main Menu");
        customizeButton(mainMenuButton, new Color(0, 0, 204), Color.WHITE, new Font("Arial", Font.BOLD, 14));
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onMainMenu.run();
                dispose();
            }
        });

        // Add buttons to panel
        buttonsPanel.add(restartButton);
        buttonsPanel.add(mainMenuButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        // Optional: Additional styling can be added here
    }

    /**
     * Helper method to customize buttons.
     *
     * @param button      The JButton to customize.
     * @param bgColor     Background color of the button.
     * @param fgColor     Foreground (text) color of the button.
     * @param buttonFont  Font of the button text.
     */
    private void customizeButton(JButton button, Color bgColor, Color fgColor, Font buttonFont) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(buttonFont);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }
}
