/*
 * ImageUtils.java
 *
 * This class provides utility methods for loading and scaling images in the Tetris game project.
 * It includes methods to create an image label with an option to scale the image to a specified width
 * while maintaining the aspect ratio. This utility class ensures that images are displayed
 * consistently across the application.
 *
 * Authors: Justin Morgan
 * Last Updated Date: 11/3/2024
 *
 * Usage:
 *   - Use `createImageLabel` to load an image from the specified path and scale it for consistent display.
 *   - If the image fails to load, an error label will be shown instead.
 *
 * Dependencies:
 *   - Java AWT and Swing libraries
 *   - UIConstants for font settings
 *
 * Note:
 *   Ensure that image paths are correct and accessible within the classpath.
 *   This class logs any issues related to image loading for debugging purposes.
 */

package main.java.tetris.utility;

import java.awt.*;
import javax.swing.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.tetris.ui.UIConstants;

// ImageUtils provides utility methods for loading and scaling images.
public class ImageUtils {
    private static final Logger LOGGER = Logger.getLogger(ImageUtils.class.getName());

    /*
     * Loads an image from the specified path and scales it to the given width.
     * The height is adjusted to maintain the aspect ratio.
     */
    public static JLabel createImageLabel(String imagePath, int width) {
        ImageIcon imageIcon;
        try {
            // Attempts to load the image from the path you specify
            imageIcon = new ImageIcon(Objects.requireNonNull(ImageUtils.class.getResource(imagePath)));
            if (imageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new Exception("Image not loaded");
            }

            // Get loaded image and scale to specific width; height is auto-adjusted
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            // Create JLabel to display image, centered both horizontally and vertically
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            return imageLabel;

            // Error message if loading image fails
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading image: " + imagePath, e);
            JLabel errorLabel = new JLabel("Image not found", SwingConstants.CENTER);
            errorLabel.setForeground(Color.LIGHT_GRAY);
            errorLabel.setFont(new Font(UIConstants.FONT_NAME, Font.PLAIN, UIConstants.ERROR_FONT_SIZE));
            return errorLabel;
        }
    }
}
