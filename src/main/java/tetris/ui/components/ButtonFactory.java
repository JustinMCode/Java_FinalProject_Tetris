/*
 * ButtonFactory.java
 *
 * This class provides methods to create styled buttons with consistent appearance and behavior
 * across the Tetris game application. Each button can be customized with text and color,
 * and includes hover effects for improved interactivity.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Use `createColoredButton` to generate buttons with a consistent look and feel.
 *   - Button color can be customized, and each button displays hover effects to enhance the UI experience.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for font settings and default button styles
 */

package main.java.tetris.ui.components;

import java.awt.*;
import javax.swing.*;
import main.java.tetris.ui.UIConstants;

// ButtonFactory provides methods to create styled buttons with consistent appearance and behavior.
public class ButtonFactory {

    // Creates a JButton with specified text and color, including hover effects.
    public static JButton createColoredButton(String text, Color color) {

        // Initialize button with text
        JButton button = new JButton(text);

        // Set font, text color, background_color, and appearance options.
        button.setFont(new Font(UIConstants.FONT_NAME, Font.BOLD, UIConstants.BUTTON_FONT_SIZE));
        button.setForeground(color);
        button.setBackground(Color.BLACK);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setToolTipText(text);
        button.setContentAreaFilled(false);

        // Add hover effects using MouseAdapter
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Change text and background color, and enable border when hovered.
                button.setForeground(Color.WHITE);
                button.setBackground(color.darker());
                button.setBorderPainted(true);
                button.setContentAreaFilled(true);
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Revert to original appearance when no longer hovered.
                button.setForeground(color);
                button.setBackground(Color.BLACK);
                button.setBorderPainted(false);
                button.setContentAreaFilled(false);
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }
}
