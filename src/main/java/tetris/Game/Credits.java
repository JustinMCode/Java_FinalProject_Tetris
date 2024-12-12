/*
 * Credits.java
 *
 * This class provides the author credits for the game.
 *
 * Authors: Lauren Gregory, Justin Morgan, Daniyar Alimkhanov
 * Last Updated Date: 12/11/2024
 *
 * Usage:
 *   - Displays from the StartMenu when the Credit option is selected by the user.
 *
 * Dependencies:
 *   - Java Swing library for GUI components
 *   - StartMenu as the main menu UI component
 */

package main.java.tetris.Game;

import main.java.tetris.ui.startmenu.StartMenu;

import javax.swing.*;
import java.awt.*;

public class Credits extends JPanel {

    // CreditsPanel class creates a styled panel to display game credits.

        // Define constants for layout and styling
        private static final String FONT_NAME = "Arial";
        private static final int FONT_SIZE = 30;
        private static final int BUTTON_WIDTH = 200;
        private static final int BUTTON_HEIGHT = 40;
        private static final int SPACING = 20;

        private JButton backButton;

        /*
         * Constructs a CreditsPanel, setting the background color, padding,
         * and adding the credits content.
         */
        public Credits() {
            // Set BoxLayout for the parent panel
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(Color.BLACK);

            // Create and add the credits component
            JComponent creditsComponent = createCreditsComponent();
            add(creditsComponent);

            // Create and add the back button
            backButton = createBackButton();
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.add(backButton);
            add(buttonPanel);
        }

        /*
         * Creates and returns the credits component.
         */
        private JComponent createCreditsComponent() {
            //Credit Panel
            JPanel creditsPanel = new JPanel();
            creditsPanel.setBackground(Color.BLACK);
            creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));

            // Title Label
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titlePanel.setBackground(Color.BLACK);

            JLabel titleLabel = new JLabel("Credits");
            titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE + 20));
            titleLabel.setText(getRainbowTitleText());
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titlePanel.add(titleLabel);

            creditsPanel.add(titlePanel);

            // Add spacing
            creditsPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));

            // Developer Label
            JLabel DeveloperLabel = new JLabel("Developed By");
            DeveloperLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
            DeveloperLabel.setForeground(Color.YELLOW);
            DeveloperLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(DeveloperLabel);

            JLabel developers = new JLabel("<html>Daniyar Alimkhanov, Justin Morgan, Lauren Gregory</html>");
            developers.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE - 10));
            developers.setForeground(Color.WHITE);
            developers.setHorizontalAlignment(SwingConstants.CENTER);
            developers.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(developers);

            // Add spacing
            creditsPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));

            // UI Label
            JLabel UILabel = new JLabel("UI Designers");
            UILabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
            UILabel.setForeground(Color.GREEN);
            UILabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(UILabel);

            JLabel UItext = new JLabel("<html>Justin Morgan, Daniyar Alimkhanov, Lauren Gregory</html>");
            UItext.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE - 10));
            UItext.setForeground(Color.WHITE);
            UItext.setHorizontalAlignment(SwingConstants.CENTER);
            UItext.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(UItext);

            // Add spacing
            creditsPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));

            // Gameplay Designers Label
            JLabel GameplayLabel = new JLabel("Gameplay Designers");
            GameplayLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
            GameplayLabel.setForeground(Color.MAGENTA);
            GameplayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(GameplayLabel);

            JLabel gameplay = new JLabel("<html>Justin Morgan and Lauren Gregory</html>");
            gameplay.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE - 10));
            gameplay.setForeground(Color.WHITE);
            gameplay.setHorizontalAlignment(SwingConstants.CENTER);
            gameplay.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(gameplay);

            // Add spacing
            creditsPanel.add(Box.createRigidArea(new Dimension(0, SPACING)));

            // Music/SFX Label
            JLabel MusicLabel = new JLabel("Music/SFX");
            MusicLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
            MusicLabel.setForeground(Color.BLUE);
            MusicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(MusicLabel);

            JLabel musicText = new JLabel("<html>Daniyar Alimkhanov</html>");
            musicText.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE - 10));
            musicText.setForeground(Color.WHITE);
            musicText.setHorizontalAlignment(SwingConstants.CENTER);
            musicText.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(musicText);

            return creditsPanel;
        }


    // Creates the back button in the Options screen
    private JButton createBackButton() {
        add(Box.createRigidArea(new Dimension(0, SPACING)));

        backButton = new JButton("Back");
        backButton.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
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
        return backButton;
    }
    /*
     * Generates the rainbow-colored title text using HTML for styling.
     * Each character is assigned a different color.
     */
    private String getRainbowTitleText() {
        return "<html><div style='text-align: center;'>"
                + "<span style='color: red;'>C</span>"
                + "<span style='color: orange;'>r</span>"
                + "<span style='color: yellow;'>e</span>"
                + "<span style='color: green;'>d</span>"
                + "<span style='color: blue;'>i</span>"
                + "<span style='color: purple;'>t</span>"
                + "<span style='color: red;'>s</span>"
                + "</div></html>";
    }

}



