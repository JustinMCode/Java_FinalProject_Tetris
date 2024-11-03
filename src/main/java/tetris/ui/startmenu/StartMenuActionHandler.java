/*
 * StartMenuActionHandler.java
 *
 * This class handles the button click events for the StartMenu.
 * It acts as an ActionListener, triggering the corresponding action method in StartMenu
 * based on the selected ButtonAction.
 *
 * Authors: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Instantiate StartMenuActionHandler with a StartMenu reference.
 *   - Each button click in the StartMenu triggers the appropriate action
 *     (e.g., starting single-player, multiplayer, options, etc.).
 *
 * Dependencies:
 *   - StartMenu for access to action methods.
 *   - ButtonAction for mapping actions to menu items.
 */

package main.java.tetris.ui.startmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.tetris.ui.ButtonAction;

/*
 * StartMenuActionHandler class handles the button click events for the StartMenu.
 * Implements ActionListener to listen for and handle button actions.
 */
public class StartMenuActionHandler implements ActionListener {
    private final StartMenu startMenu;  // Reference to StartMenu for calling action methods

    /*
     * Constructs an ActionHandler for the StartMenu, allowing button clicks to
     * trigger the appropriate StartMenu actions.
     */
    public StartMenuActionHandler(StartMenu startMenu) {
        this.startMenu = startMenu;
    }

    /*
     * Handles button click events by mapping the action command to a ButtonAction
     * and invoking the corresponding StartMenu method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Retrieve the action command and map it to a ButtonAction enum
        ButtonAction action = ButtonAction.valueOf(e.getActionCommand());

        // Perform the corresponding StartMenu action based on the ButtonAction
        switch (action) {
            case SINGLEPLAYER:
                startMenu.startSinglePlayer();  // Start single-player game
                break;
            case MULTIPLAYER:
                startMenu.startMultiplayer();   // Start multiplayer game
                break;
            case OPTIONS:
                startMenu.openOptions();        // Open options menu
                break;
            case CREDITS:
                startMenu.showCredits();        // Show credits
                break;
            case EXIT:
                startMenu.exitGame();           // Exit the game
                break;
            default:
                // Handle unknown actions by throwing an exception
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
