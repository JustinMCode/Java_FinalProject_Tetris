/*
 * MenuPanel.java
 *
 * This class represents the main menu panel for the Tetris StartMenu.
 * It creates and arranges the menu buttons based on the actions defined in ButtonAction.
 * Each button is styled using the ButtonFactory to maintain a consistent look across the UI.
 *
 * Author: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Instantiate MenuPanel with an ActionListener to handle button actions.
 *   - Button actions and styles are predefined in ButtonAction and ButtonFactory respectively.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for layout configurations
 *   - ButtonFactory for consistent button styling
 */

package main.java.tetris.ui.startmenu;

import main.java.tetris.audio.AudioManager;
import main.java.tetris.ui.UIConstants;
import main.java.tetris.ui.components.ButtonAction;
import main.java.tetris.utility.ButtonFactory;

import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.EnumMap;
import java.awt.event.ActionListener;

/*
 * MenuPanel class creates the menu buttons for the StartMenu.
 * Arranges buttons vertically with spacing, styled according to the ButtonFactory.
 */
public class MenuPanel extends JPanel {

    // Map to hold buttons associated with their actions for future reference
    private final Map<ButtonAction, JButton> buttons = new EnumMap<>(ButtonAction.class);

    /*
     * Constructs a MenuPanel with buttons created for each ButtonAction enum.
     * Buttons are aligned vertically, centered, and are configured to trigger
     * the provided ActionListener.
     */
    public MenuPanel(ActionListener actionListener) {
        // Set panel background and layout settings
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(
                UIConstants.MENU_PANEL_PADDING_TOP, 0, 0, 0
        ));

        // Loop through each ButtonAction to create a corresponding button
        for (ButtonAction action : ButtonAction.values()) {
            // Create a styled button using ButtonFactory, set action command, and add action listener
            JButton button = ButtonFactory.createColoredButton(action.getText(), action.getColor());
            button.setActionCommand(action.name());
            button.addActionListener(actionListener);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Store button in the map for potential future use
            buttons.put(action, button);

            // Add vertical spacing and button to the panel
            add(Box.createVerticalStrut(UIConstants.BUTTON_SPACING));
            add(button);

        }
        AudioManager.playMusic();
    }

    // Returns the button associated with the specified ButtonAction.
    public JButton getButton(ButtonAction action) {
        return buttons.get(action);
    }
}
