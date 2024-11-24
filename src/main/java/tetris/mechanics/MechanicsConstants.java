/*
 * MechanicsConstants.java
 *
 * This class holds shared constants used across the mechanics package.
 *
 * Last Updated Date: 11/24/2024
 */

package main.java.tetris.mechanics;

public class MechanicsConstants {

    // Timer interval for game updates (milliseconds)
    public static final int TIMER_INTERVAL_MS = 500;

    // Game Over dialog messages
    public static final String GAME_OVER_MESSAGE = "Game Over! Do you want to play again?";
    public static final String GAME_OVER_TITLE = "Game Over";

    // Piece movement boundary constraints
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private MechanicsConstants() {
        // Prevent instantiation
    }
}
