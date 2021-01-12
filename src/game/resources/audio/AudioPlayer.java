package game.resources.audio;

import javafx.scene.media.AudioClip;

public final class AudioPlayer {
    private static AudioPlayer instance = null;
    private AudioClip punch;
    private AudioClip fire;

    private AudioPlayer() {
    }

    public static AudioPlayer getInstance() {
        if (instance == null) {
            instance = new AudioPlayer();
        }
        return instance;
    }

    public void loadSounds() {
         punch = new AudioClip(AudioPlayer.class.getResource("punch.wav").toString());
         punch.setVolume(0.2);
         fire = new AudioClip(AudioPlayer.class.getResource("fire.wav").toString());
         fire.setVolume(0.5);
    }

    public void playPunchSound() {
        punch.play();
    }

    public void playFireSound() {
        fire.play();
    }
}
