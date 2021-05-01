package game.map.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Tile {
    private final int size = 100;
    private final ImageView image;

    public Tile() {
        this.image = initImage();
    }

    public abstract boolean walkable();
    public abstract String imagePath();
    public ImageView getImage() {
        return image;
    };

    private ImageView initImage() {
        try {
            return new ImageView(new Image(new FileInputStream(imagePath()), size, size, false, false));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}