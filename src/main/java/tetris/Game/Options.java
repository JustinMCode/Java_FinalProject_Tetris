/*
 * Options.java
 *
 * This class provides the user option to adjust audio(sound and music) settings, choose the track music.
 *
 * Author: Daniyar Alimkhanov
 * Last Updated Date: 11/6/2024
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
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBackground(Color.black);

    //Music volume control
    JLabel musicLabel = new JLabel("Music");
    musicLabel.setForeground(Color.white);
    musicVolume = new JSlider(0,100,50);
    musicVolume.setMajorTickSpacing(20);
    musicVolume.setPaintTicks(true);
    musicVolume.setPaintLabels(true);
    musicVolume.addChangeListener(e -> AudioManager.setMusicVolume(musicVolume.getValue()/100.0f));
    add(musicLabel);
    add(musicVolume);

    //Sound volume control
    JLabel soundLabel = new JLabel("Sound");
    soundLabel.setForeground(Color.white);
    soundVolume = new JSlider(0,100,50);
    soundVolume.setMajorTickSpacing(20);
    soundVolume.setPaintTicks(true);
    soundVolume.setPaintLabels(true);
    soundVolume.addChangeListener(e -> AudioManager.setSoundVolume(soundVolume.getValue()/100.0f));
    add(soundLabel);
    add(soundVolume);

    //Track selection
    JLabel trackSelectorLabel = new JLabel("Track");
    trackSelectorLabel.setForeground(Color.white);
    trackLabel = new JLabel(AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()]);
    trackLabel.setForeground(Color.black);

    leftButton = new JButton("<");
    leftButton.addActionListener(this::changeTrack);
    rightButton = new JButton(">");
    rightButton.addActionListener(this::changeTrack);

    JPanel trackPanel = new JPanel();
    trackPanel.setLayout(new FlowLayout());
    trackPanel.add(leftButton);
    trackPanel.add(trackLabel);
    trackPanel.add(rightButton);

    add(trackSelectorLabel);
    add(trackPanel);

    //Save button
    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(e -> saveSettings());
    add(saveButton);

}

private void changeTrack(ActionEvent e) {
    if (e.getSource() == leftButton) {
        AudioManager.setCurrentTrack((AudioManager.getCurrentTrack()-1+AudioManager.getMusicTracks().length) % AudioManager.getMusicTracks().length);
    } else if (e.getSource() == rightButton) {
        AudioManager.setCurrentTrack((AudioManager.getCurrentTrack()+1) % AudioManager.getMusicTracks().length);
    }
    trackLabel.setText(AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()]);

}

private void saveSettings() {
    int musicVol = musicVolume.getValue();
    int soundVol = soundVolume.getValue();

    System.out.println("Music " + musicVol);
    System.out.println("Sound " + soundVol);


    AudioManager.setMusicVolume(musicVol/100.0f);
    AudioManager.setSoundVolume(soundVol/100.0f);

}

}
