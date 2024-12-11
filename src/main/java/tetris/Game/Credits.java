package main.java.tetris.Game;

import main.java.tetris.ui.startmenu.StartMenu;

import javax.swing.*;
import java.awt.*;

public class Credits extends JPanel {

    // CreditsPanel class creates a styled panel to display game credits.
    // class CreditsPanel extends JPanel {

        // Define constants for layout and styling
        private static final int PADDING_TOP = 20;
        private static final int PADDING_LEFT = 20;
        private static final int PADDING_BOTTOM = 20;
        private static final int PADDING_RIGHT = 20;
        private static final String FONT_NAME = "Arial";
        private static final int FONT_SIZE = 14;
        private static final int WINDOW_WIDTH = 400;
        private static final int CREDITS_HEIGHT = 150;
        private static final int BUTTON_WIDTH = 200;
        private static final int BUTTON_HEIGHT = 40;

        private JButton backButton;

        /*
         * Constructs a CreditsPanel, setting the background color, padding,
         * and adding the credits content.
         */
        public Credits() {
            // Set background color to match the game's theme
            setBackground(Color.GRAY);

            // Set padding around the credits for proper alignment
            setBorder(BorderFactory.createEmptyBorder(
                    PADDING_TOP,
                    PADDING_LEFT,
                    PADDING_BOTTOM,
                    PADDING_RIGHT
            ));


                // Set the background and layout for the panel
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                BackButton(gbc);

            // Create and add the credits component
            JComponent creditsComponent = createCreditsComponent();
            add(creditsComponent);
        }

        /*
         * Creates and returns the credits component.
         * The component is a JLabel styled with HTML for formatting the text.
         */
        private JComponent createCreditsComponent() {
            // Create a JLabel for displaying the credits
            JLabel creditsLabel = new JLabel();
            creditsLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
            creditsLabel.setForeground(Color.WHITE);
            creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            creditsLabel.setText(getCreditsText());

            // Set preferred size to ensure proper layout in the panel
            creditsLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, CREDITS_HEIGHT));
            return creditsLabel;
        }

        /*
         * Generates the styled credits text using HTML for formatting.
         * Includes team members and their roles.
         */
        private String getCreditsText() {
            return "<html>"
                    + "<div style='text-align: center;'>"
                    + "<h2 style='color: cyan;'>Tetris Game Credits</h2>"
                    + "<p style='color: white;'>Developed by:</p>"
                    + "<p style='color: yellow;'>Daniyar Alimkhanov, Justin Morgan, Lauren Gregory - Lead Developer</p>"
                    + "</div>"
                    + "</html>";
        }

    // Creates the back button in the Options screen
    private void BackButton(GridBagConstraints gbc) {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.WHITE);
        backButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        backButton.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            frame.getContentPane().add(new StartMenu());
            frame.revalidate();
            frame.repaint();
        });
        gbc.gridy = 7;
        add(backButton, gbc);
    }
    }

