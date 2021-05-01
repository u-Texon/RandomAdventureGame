package game.resources;

import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class Resource {
        public static final String FONT = "src/game/resources/font/Pixel_Bold.ttf";
        public static final String KNIGHT = "src/game/resources/images/knight.png";
        public static final String FIGHT_BUTTON = "src/game/resources/images/FightButton.gif";
        public static final String SLIME = "src/game/resources/images/Slime.gif";
        public static final String EXPLOSION = "src/game/resources/images/Explosion.gif";
        public static final String STREET = "src/game/resources/images/StreetAtNight.png";
        public static final String KNIGHT_LEFT = "src/game/resources/images/knight_Left.png";
        public static final String ENCOUNTER = "src/game/resources/images/EncounterAnimation.gif";
        public static final String QUESTION_MARK = "src/game/resources/images/questionmark.jpg";

        private Resource() {
        }

        public static Font getFont(int size) {
            try {
                return Font.loadFont(new FileInputStream(FONT), size);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
}
