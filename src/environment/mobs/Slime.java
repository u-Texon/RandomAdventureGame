package environment.mobs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Slime extends Enemy {
    /**
     * standard slime max health
     */
    public static final int HEALTH = 15;
    /**
     * standard slime attack damage
     */
    public static final double ATK = 3;
    /**
     * standard slime max mana
     */
    public static final int MANA = 0;


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
            return new ImageView(new Image(new FileInputStream("src/resources/Slime.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
