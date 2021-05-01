package game.gameElements.environment.items;

import game.gameElements.environment.Player;
import game.resources.Resource;
import game.resources.audio.AudioPlayer;

public class ManaPotion extends Item {
    /**
     * the amount of mana the player regains from using this potion
     */
    public static final int MANA_AMOUNT = 10;

    @Override
    public int getGoldValue() {
        return MANA_AMOUNT;
    }

    @Override
    public void use() {
        AudioPlayer.getInstance().playHealSound();
        Player.getPlayer().gainMana(MANA_AMOUNT);
    }

    @Override
    public String getName() {
        return "Mana Potion";
    }

    @Override
    public String imagePath() {
        return Resource.QUESTION_MARK;
    }
}
