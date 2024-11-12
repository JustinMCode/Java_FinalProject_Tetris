package main.java.tetris.Game;

import main.java.tetris.audio.AudioManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Options extends JPanel {

    private JSlider musicVolume;
    private JSlider soundVolume;
    private JLabel trackLabel;
    private JButton leftButton;
    private JButton rightButton;
    private Image customImage; // To hold the custom image

    public Options() {
        // 1. Change layout to GridBagLayout for better control over positioning
        setLayout(new GridBagLayout());
        setBackground(Color.black);

        GridBagConstraints gbc = new GridBagConstraints();  // To control positioning

        // Music volume control
        JLabel musicLabel = new JLabel("Music");
        musicLabel.setForeground(Color.yellow);
        musicVolume = new JSlider(0, 100, 50);
        musicVolume.setMajorTickSpacing(20);
        musicVolume.setPaintTicks(true);
        musicVolume.setPaintLabels(true);
        musicVolume.addChangeListener(e -> AudioManager.setMusicVolume(musicVolume.getValue() / 100.0f));

        musicVolume.setPreferredSize(new Dimension(300, 40));  // Bigger slider
        musicLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Bigger label

        // 2. Set GridBagConstraints for placement (move further to the left)
        gbc.insets = new Insets(10, 10, 10, 10);  // Add some padding
        gbc.weightx = 1.0;
        add(musicLabel, gbc);

        gbc.gridy = 1;
        add(musicVolume, gbc);

        // Sound volume control
        JLabel soundLabel = new JLabel("Sound");
        soundLabel.setForeground(Color.blue);
        soundVolume = new JSlider(0, 100, 50);
        soundVolume.setMajorTickSpacing(20);
        soundVolume.setPaintTicks(true);
        soundVolume.setPaintLabels(true);
        soundVolume.addChangeListener(e -> AudioManager.setSoundVolume(soundVolume.getValue() / 100.0f));

        soundVolume.setPreferredSize(new Dimension(300, 40));  // Bigger slider
        soundLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Bigger label

        gbc.gridy = 2;
        add(soundLabel, gbc);

        gbc.gridy = 3;
        add(soundVolume, gbc);

        // Track selection
        JLabel trackSelectorLabel = new JLabel("Track");
        trackSelectorLabel.setForeground(Color.green);
        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel = new JLabel(songName);
        trackLabel.setForeground(Color.black);

        trackLabel.setFont(new Font("Arial", Font.BOLD, 14));
        trackSelectorLabel.setFont(new Font("Arial", Font.BOLD, 20));

        leftButton = new JButton("<");
        leftButton.addActionListener(this::changeTrack);
        rightButton = new JButton(">");
        rightButton.addActionListener(this::changeTrack);
        leftButton.setForeground(Color.black);
        leftButton.setBackground(Color.green);
        rightButton.setForeground(Color.black);
        rightButton.setBackground(Color.green);

        JPanel trackPanel = new JPanel();
        trackPanel.setLayout(new FlowLayout());
        trackPanel.add(leftButton);
        trackPanel.add(trackLabel);
        trackPanel.add(rightButton);

        // Set background and foreground colors
        soundVolume.setBackground(Color.black);
        soundVolume.setForeground(Color.white);
        musicVolume.setBackground(Color.black);
        musicVolume.setForeground(Color.white);
        trackPanel.setBackground(Color.black);
        trackPanel.setForeground(Color.white);
        trackLabel.setBackground(Color.black);
        trackLabel.setForeground(Color.white);

        // 3. Place track selection more to the left
        gbc.gridy = 4;
        add(trackSelectorLabel, gbc);

        gbc.gridy = 5;
        add(trackPanel, gbc);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveSettings());
        gbc.gridy = 6;
        add(saveButton, gbc);

        // Load the custom image from the file path
        //loadCustomImage();
    }

    // Method to load the custom image
    private void loadCustomImage() {
        try {
            File imageFile = new File("src/main/resources/images/placeholder.png");

            if (imageFile.exists()) {
                System.out.println("Image found at: " + imageFile.getAbsolutePath());
                customImage = ImageIO.read(imageFile);  // Load the image
                repaint();  // Repaint the panel after loading the image
            } else {
                System.out.println("Image not found at: " + imageFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();  // Handle error if image is not found
        }
    }

    // Override the paintComponent method to draw the image at the correct size
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            // Scale the image to fit the panel size (you can modify this to resize as needed)
            g.drawImage(customImage, 550, 50, 400, 700, this);
    }

    private void changeTrack(ActionEvent e) {
        if (e.getSource() == leftButton) {
            AudioManager.setCurrentTrack(AudioManager.getCurrentTrack() % AudioManager.getMusicTracks().length);
        } else if (e.getSource() == rightButton) {
            AudioManager.setCurrentTrack((AudioManager.getCurrentTrack() + 1) % AudioManager.getMusicTracks().length);
        }
        String currentTrackPath = AudioManager.getMusicTracks()[AudioManager.getCurrentTrack()];
        String songName = extractSongName(currentTrackPath);
        trackLabel.setText(songName);
    }

    private String extractSongName(String trackPath) {
        String fileName = new java.io.File(trackPath).getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(0, dotIndex); // Removes the file extension
        }
        return fileName;
    }

    private void saveSettings() {
        int musicVol = musicVolume.getValue();
        int soundVol = soundVolume.getValue();

        System.out.println("Music " + musicVol);
        System.out.println("Sound " + soundVol);

        AudioManager.setMusicVolume(musicVol / 100.0f);
        AudioManager.setSoundVolume(soundVol / 100.0f);
    }
}
