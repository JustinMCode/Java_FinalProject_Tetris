/*
 * Options.java
 *
 * This class provides the user option to adjust audio(sound and music) settings,
 * choose the track for music and save the changes.
 * Author: Daniyar Alimkhanov
 * Last Updated Date: 11/11/2024
 *
 * Dependent: AudioManager
 */

package main.java.tetris.Game;

import main.java.tetris.audio.AudioManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Options extends JPanel {

    private JSlider musicVolume;
    private JSlider soundVolume;
    private JLabel trackLabel;
    private JButton leftButton;
    private JButton rightButton;

    public Options() {
        //Sets the background and layout form the panel
        setLayout(new GridBagLayout());
        setBackground(Color.black);
        GridBagConstraints gbc = new GridBagConstraints();

        MusicControl(gbc);
        SoundControl(gbc);
        TrackSetup(gbc);
        SaveButton(gbc);
    }

    //Music volume/position control function
    private void MusicControl(GridBagConstraints gbc){
        JLabel musicLabel = new JLabel("Music");
        musicLabel.setForeground(Color.yellow);
        musicVolume = new JSlider(0, 100, 50);
        musicVolume.setMajorTickSpacing(20);
        musicVolume.setPaintTicks(true);
        musicVolume.setPaintLabels(true);
        musicVolume.addChangeListener(e -> AudioManager.setMusicVolume(musicVolume.getValue() / 100.0f));

        //Sets the size of music panel
        musicVolume.setPreferredSize(new Dimension(300, 40));
        musicLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //Adjusts the position of music control in order
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        add(musicLabel, gbc);
        gbc.gridy = 1;
        add(musicVolume, gbc);

        //Sets the color of background and numbers on the slider
        musicVolume.setBackground(Color.black);
        musicVolume.setForeground(Color.white);
    }

    //Sound volume/position control function
    private void SoundControl(GridBagConstraints gbc){
        JLabel soundLabel = new JLabel("Sound");
        soundLabel.setForeground(Color.blue);
        soundVolume = new JSlider(0, 100, 50);
        soundVolume.setMajorTickSpacing(20);
        soundVolume.setPaintTicks(true);
        soundVolume.setPaintLabels(true);
        soundVolume.addChangeListener(e -> AudioManager.setSoundVolume(soundVolume.getValue() / 100.0f));

        //Sets the size of sound panel
        soundVolume.setPreferredSize(new Dimension(300, 40));
        soundLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //Adjusts the position of sound control in order
        gbc.gridy = 2;
        add(soundLabel, gbc);
        gbc.gridy = 3;
        add(soundVolume, gbc);

        //Sets the color of background and numbers on the slider
        soundVolume.setBackground(Color.black);
        soundVolume.setForeground(Color.white);
    }

    //Track selector/position control function
    private void TrackSetup(GridBagConstraints gbc){
        JLabel trackSelectorLabel = new JLabel("Track");
        trackSelectorLabel.setForeground(Color.green);
        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel = new JLabel(songName);
        trackLabel.setForeground(Color.black);

        //Sets the size of track panel
        trackLabel.setFont(new Font("Arial", Font.BOLD, 14));
        trackSelectorLabel.setFont(new Font("Arial", Font.BOLD, 20));

        //Adjusts the symbol for buttons
        leftButton = new JButton("<");
        leftButton.addActionListener(this::changeTrack);
        rightButton = new JButton(">");
        rightButton.addActionListener(this::changeTrack);

        //Adjusts the color of buttons
        leftButton.setForeground(Color.black);
        leftButton.setBackground(Color.green);
        rightButton.setForeground(Color.black);
        rightButton.setBackground(Color.green);

        //Holds the track buttons and label
        JPanel trackPanel = new JPanel();
        trackPanel.setLayout(new FlowLayout());
        trackPanel.add(leftButton);
        trackPanel.add(trackLabel);
        trackPanel.add(rightButton);

        //Adjusts the position of track panel in order
        gbc.gridy = 4;
        add(trackSelectorLabel, gbc);
        gbc.gridy = 5;
        add(trackPanel, gbc);

        //Sets the color of background, panel and track name
        trackPanel.setBackground(Color.black);
        trackPanel.setForeground(Color.white);
        trackLabel.setBackground(Color.black);
        trackLabel.setForeground(Color.white);
    }

    //Logic that gives the left/right buttons to change tracks
    private void changeTrack(ActionEvent e) {
        //If left button clicked - go to previous track
        if (e.getSource() == leftButton) {
            AudioManager.setCurrentTrack(AudioManager.getCurrentTrack() % AudioManager.getMusicTracks().length);
        }
        //If right button clicked - go to the next track
        else if (e.getSource() == rightButton) {
            AudioManager.setCurrentTrack((AudioManager.getCurrentTrack() + 1) % AudioManager.getMusicTracks().length);
        }

        //Updates the label with the new track name
        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel.setText(songName);
    }

    //Removes the file extension of the song file path, returns just the song name
    private String extractSongName(String trackPath) {
        String fileName = new java.io.File(trackPath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex);
        }
        return fileName;
    }

    //Creates the save button
    private void SaveButton(GridBagConstraints gbc){
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveSettings());
        gbc.gridy = 6;
        add(saveButton, gbc);
    }

    //Saves the chosen music/sound settings
    private void saveSettings() {
        int musicVol = musicVolume.getValue();
        int soundVol = soundVolume.getValue();

        //Prints the values(for debugging)
        System.out.println("Music " + musicVol);
        System.out.println("Sound " + soundVol);

        //Updates the audio manager with the new volume
        AudioManager.setMusicVolume(musicVol / 100.0f);
        AudioManager.setSoundVolume(soundVol / 100.0f);
    }
}
