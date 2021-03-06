package game.gameElements.environment.items;

import game.gameElements.environment.Player;
import game.resources.Resource;
import game.resources.audio.AudioPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HealingPotion extends Item {
    /**
     * healing amount for a standard potion
     */
    public static final int HEAL_AMOUNT = 20;


    @Override
    public int getGoldValue() {
        return HEAL_AMOUNT / 2;
    }

    @Override
    public void use() {
        AudioPlayer.getInstance().playHealSound();
        Player.getPlayer().heal(HEAL_AMOUNT);
    }

    @Override
    public String getName() {
        return "Healing Potion";
    }

    @Override
    public String imagePath() {
        return Resource.QUESTION_MARK;
    }

}
