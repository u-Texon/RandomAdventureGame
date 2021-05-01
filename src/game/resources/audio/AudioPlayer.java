package game.resources.audio;

import javafx.scene.media.AudioClip;

public final class AudioPlayer {
    private static AudioPlayer instance = null;
    private AudioClip punch;
    private AudioClip fire;
    private AudioClip heal;

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
         punch.setVolume(0.1);
         fire = new AudioClip(AudioPlayer.class.getResource("fire.wav").toString());
         fire.setVolume(0.3);
         heal = new AudioClip(AudioPlayer.class.getResource("heal.wav").toString());
         heal.setVolume(0.05);
    }

    public void playHealSound() {
        heal.play();
    }

    public void playPunchSound() {
        punch.play();
    }

    public void playFireSound() {
        fire.play();
    }
}
