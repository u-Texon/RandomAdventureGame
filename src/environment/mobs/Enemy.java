package environment.mobs;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;

public abstract class Enemy extends Mob {
    private final ProgressBar healthBar;
    private final ImageView image;

    public Enemy(int health, double physicATK, int mana) {
        super(health, physicATK, mana);
        healthBar = new ProgressBar(health);
        healthBar.setProgress(1);
        image = initImage();
    }

    public ImageView getImage() {
        return image;
    }

    public ProgressBar getHealthBar() {
        return healthBar;
    }

    public void updateHealthBar() {
        if ((getCurrentHealth() / getMaxHealth()) <= 0) {
            healthBar.setProgress(0);
        } else {
            healthBar.setProgress((getCurrentHealth() / getMaxHealth()));
        }
    }

    public abstract String getName();


    public abstract ImageView initImage();
}
