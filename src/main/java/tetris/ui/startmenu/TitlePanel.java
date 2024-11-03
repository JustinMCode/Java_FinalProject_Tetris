/*
 * TitlePanel.java
 *
 * This class creates the title section of the StartMenu for the Tetris game.
 * It displays the title with a rainbow-colored text effect and a shadow.
 * The title is centered and includes padding to align with the menu design.
 *
 * Authors: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Instantiate TitlePanel to display the Tetris title at the top of the StartMenu.
 *   - The title includes a colorful, HTML-styled text effect.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for title font and layout settings
 */

package main.java.tetris.ui.startmenu;

import java.awt.*;
import javax.swing.*;
import main.java.tetris.ui.UIConstants;

// TitlePanel class creates the title section of the StartMenu with a shadow effect.
public class TitlePanel extends JPanel {

    /*
     * Constructs a TitlePanel, setting the background color and padding,
     * and adding the title component with shadow.
     */
    public TitlePanel() {
        // Set background color for consistency with the menu theme
        setBackground(Color.BLACK);

        // Set padding around the title for alignment within the StartMenu
        setBorder(BorderFactory.createEmptyBorder(
                UIConstants.TITLE_PADDING_TOP,
                0,
                0,
                0
        ));

        // Create and add the title component with shadow effect
        JComponent titleComponent = createTitleComponent();
        add(titleComponent);
    }

    /*
     * Creates and returns the title component with a shadow effect.
     * The component is a layered pane that contains the title text
     * with a rainbow color effect, displayed in HTML format.
     */
    private JComponent createTitleComponent() {
        // Create the main title label with custom font and rainbow colors
        JLabel titleLabel = getjLabel();
        titleLabel.setBounds(0, 0, UIConstants.WINDOW_WIDTH, UIConstants.TITLE_FONT_SIZE);

        // Create a JLayeredPane to hold the title label (can later add shadow effects)
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(UIConstants.WINDOW_WIDTH, UIConstants.TITLE_FONT_SIZE));
        layeredPane.add(titleLabel, Integer.valueOf(1));

        return layeredPane;
    }

    /*
     * Creates and returns a JLabel configured for displaying the Tetris title.
     * The label is styled with a specific font, centered alignment, and rainbow-colored text.
     */
    private JLabel getjLabel() {
        JLabel titleLabel = new JLabel();
        titleLabel.setFont(new Font(UIConstants.FONT_NAME, Font.BOLD, UIConstants.TITLE_FONT_SIZE));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText(getRainbowTitleText());
        return titleLabel;
    }

    /*
     * Generates the rainbow-colored title text using HTML for styling.
     * Each character is assigned a different color.
     */
    private String getRainbowTitleText() {
        return "<html><span style='color: red;'>T</span>"
                + "<span style='color: orange;'>e</span>"
                + "<span style='color: yellow;'>t</span>"
                + "<span style='color: green;'>r</span>"
                + "<span style='color: blue;'>i</span>"
                + "<span style='color: purple;'>s</span></html>";
    }
}
