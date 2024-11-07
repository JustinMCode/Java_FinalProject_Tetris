package main.java.tetris.audio;


import javax.sound.sampled.*;
import java.net.URL;

public class AudioManager {
    private static Clip clip;
    private static float musicVolume = 1.0f;
    private static final String[] musicTracks = {
            "/main/resources/music/musicbox.wav",
            "/main/resources/music/piano.wav",
            "/main/resources/music/strings.wav"
    };
    private static int currentTrack = 0;

    public static void playMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
        try{
            //checks for null url - if track not found
            URL trackURL = AudioManager.class.getResource(musicTracks[currentTrack]);
            if (trackURL == null) {
                throw new IllegalArgumentException("Track not found: " + musicTracks[currentTrack]);
            }else {
                System.out.println("Track found at: " + trackURL);
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(trackURL);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            setMusicVolume(musicVolume);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP){
                    clip.close();
                    nextTrack(); //Switch to next track when it ends
                }
            });
            clip.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nextTrack() {
        currentTrack = (currentTrack + 1) % musicTracks.length;
        playMusic();
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(musicVolume));
        }
    }

    public static void setSoundVolume(float volume) {
        //empty until sounds will be here
    }

    public static void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public static String[] getMusicTracks() {
        return musicTracks;
    }

    public static int getCurrentTrack() {
        return currentTrack;
    }

    public static void setCurrentTrack(int index) {
        if (index >= 0 && index < musicTracks.length) {
            currentTrack = index;
            playMusic();
        }
    }
}
