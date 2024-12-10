/*
 * Options.java
 *
 * This class provides the user option to adjust audio (sound and music) settings,
 * choose the track for music, and save the changes.
 * Author: Daniyar Alimkhanov
 * Last Updated Date: 11/11/2024
 *
 * Dependent: AudioManager
 */

package main.java.tetris.Game;

import main.java.tetris.audio.AudioManager;
import main.java.tetris.ui.startmenu.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Options extends JPanel {

    // Constants for layout and sizes
    private static final int SLIDER_MIN = 0;
    private static final int SLIDER_MAX = 100;
    private static final int SLIDER_INITIAL = 50;
    private static final int SLIDER_TICK_SPACING = 20;
    private static final int SLIDER_WIDTH = 300;
    private static final int SLIDER_HEIGHT = 40;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 40;

    private static final int FONT_SIZE_TITLE = 20;
    private static final int FONT_SIZE_TRACK = 14;

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final Color MUSIC_LABEL_COLOR = Color.YELLOW;
    private static final Color SOUND_LABEL_COLOR = Color.BLUE;
    private static final Color TRACK_LABEL_COLOR = Color.GREEN;
    private static final Color TRACK_BUTTON_COLOR = Color.GREEN;

    private JSlider musicVolume;
    private JSlider soundVolume;
    private JLabel trackLabel;
    private JButton leftButton;
    private JButton rightButton;
    private JButton backButton;

    public Options() {
        // Set the background and layout for the panel
        setLayout(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);
        GridBagConstraints gbc = new GridBagConstraints();

        MusicControl(gbc);
        SoundControl(gbc);
        TrackSetup(gbc);
        SaveButton(gbc);
        BackButton(gbc);
    }

    // Music volume/position control function
    private void MusicControl(GridBagConstraints gbc) {
        JLabel musicLabel = new JLabel("Music");
        musicLabel.setForeground(MUSIC_LABEL_COLOR);
        musicLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));

        musicVolume = new JSlider(SLIDER_MIN, SLIDER_MAX, SLIDER_INITIAL);
        musicVolume.setMajorTickSpacing(SLIDER_TICK_SPACING);
        musicVolume.setPaintTicks(true);
        musicVolume.setPaintLabels(true);
        musicVolume.setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        musicVolume.setBackground(BACKGROUND_COLOR);
        musicVolume.setForeground(Color.WHITE);
        musicVolume.addChangeListener(e -> AudioManager.setMusicVolume(musicVolume.getValue() / 100.0f));

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        add(musicLabel, gbc);
        gbc.gridy = 1;
        add(musicVolume, gbc);
    }

    // Sound volume/position control function
    private void SoundControl(GridBagConstraints gbc) {
        JLabel soundLabel = new JLabel("Sound");
        soundLabel.setForeground(SOUND_LABEL_COLOR);
        soundLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));

        soundVolume = new JSlider(SLIDER_MIN, SLIDER_MAX, SLIDER_INITIAL);
        soundVolume.setMajorTickSpacing(SLIDER_TICK_SPACING);
        soundVolume.setPaintTicks(true);
        soundVolume.setPaintLabels(true);
        soundVolume.setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        soundVolume.setBackground(BACKGROUND_COLOR);
        soundVolume.setForeground(Color.WHITE);
        soundVolume.addChangeListener(e -> AudioManager.setSoundVolume(soundVolume.getValue() / 100.0f));

        gbc.gridy = 2;
        add(soundLabel, gbc);
        gbc.gridy = 3;
        add(soundVolume, gbc);
    }

    // Track selector/position control function
    private void TrackSetup(GridBagConstraints gbc) {
        JLabel trackSelectorLabel = new JLabel("Track");
        trackSelectorLabel.setForeground(TRACK_LABEL_COLOR);
        trackSelectorLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));

        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel = new JLabel(songName);
        trackLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TRACK));
        trackLabel.setForeground(Color.WHITE);

        leftButton = new JButton("<");
        rightButton = new JButton(">");
        leftButton.setBackground(TRACK_BUTTON_COLOR);
        leftButton.setForeground(Color.BLACK);
        rightButton.setBackground(TRACK_BUTTON_COLOR);
        rightButton.setForeground(Color.BLACK);
        leftButton.addActionListener(this::changeTrack);
        rightButton.addActionListener(this::changeTrack);

        JPanel trackPanel = new JPanel();
        trackPanel.setLayout(new FlowLayout());
        trackPanel.setBackground(BACKGROUND_COLOR);
        trackPanel.add(leftButton);
        trackPanel.add(trackLabel);
        trackPanel.add(rightButton);

        gbc.gridy = 4;
        add(trackSelectorLabel, gbc);
        gbc.gridy = 5;
        add(trackPanel, gbc);
    }

    // Logic to change tracks using left/right buttons
    private void changeTrack(ActionEvent e) {
        if (e.getSource() == leftButton) {
            AudioManager.setCurrentTrack((AudioManager.getCurrentTrack() - 1 + AudioManager.getMusicTracks().length) % AudioManager.getMusicTracks().length);
        } else if (e.getSource() == rightButton) {
            AudioManager.setCurrentTrack((AudioManager.getCurrentTrack() + 1) % AudioManager.getMusicTracks().length);
        }
        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel.setText(songName);
    }

    // Removes the file extension of the song file path, returns just the song name
    private String extractSongName(String trackPath) {
        String fileName = new java.io.File(trackPath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
    }

    // Creates the save button
    private void SaveButton(GridBagConstraints gbc) {
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        saveButton.addActionListener(e -> saveSettings());
        gbc.gridy = 6;
        add(saveButton, gbc);
    }

    // Saves the chosen music/sound settings
    private void saveSettings() {
        int musicVol = musicVolume.getValue();
        int soundVol = soundVolume.getValue();

        // Updates the audio manager with the new volume
        AudioManager.setMusicVolume(musicVol / 100.0f);
        AudioManager.setSoundVolume(soundVol / 100.0f);
    }

    // Creates the back button in the Options screen
    private void BackButton(GridBagConstraints gbc) {
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, FONT_SIZE_TITLE));
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(TRACK_BUTTON_COLOR);
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
