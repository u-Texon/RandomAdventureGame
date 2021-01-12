package game.gameElements.environment.mobs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import game.resources.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Slime extends Enemy {
    /**
     * standard slime max health
     */
    public static final int HEALTH = 30;
    /**
     * standard slime attack damage
     */
    public static final double ATK = 3;
    /**
     * standard slime max mana
     */
    public static final int MANA = 0;
    /**
     * the amount of gold the player will get after defeating this slime
     */
    public static final int VALUE = 5;


    public Slime(int health, double physicATK, int mana) {
        super(health, physicATK, mana);
    }

    @Override
    public String getName() {
        return "Slime";
    }

    @Override
    public ImageView initImage() {
        try {
            return new ImageView(new Image(new FileInputStream(Resource.SLIME_PATH)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getGoldValue() {
        return VALUE;
    }
}
