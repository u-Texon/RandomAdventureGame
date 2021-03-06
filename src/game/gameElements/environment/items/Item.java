package game.gameElements.environment.items;

import game.gameElements.environment.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Item {
    private final int size = 50;
    private final ImageView image;

    public abstract int getGoldValue();
    public abstract void use();
    public abstract String getName();
    public abstract String imagePath();

    public Item() {
        this.image = initImage();
    }

    private ImageView initImage() {
        try {
            return new ImageView(new Image(new FileInputStream(imagePath()), size, size, false, false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageView getImage() {
        return image;
    }

    public void sell() {
        Player.getPlayer().gainGold(getGoldValue());
    }
}
