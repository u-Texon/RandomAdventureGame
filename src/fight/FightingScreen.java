package fight;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import system.Settings;

public class FightingScreen {

    public void start(Stage stage) {
        double width = Settings.MIN_WIDTH * 6;
        double height = Settings.MIN_HEIGHT * 6;

        stage.setHeight(height);
        stage.setWidth(width);
        stage.setTitle("FIGHT!!");

        Group parent = new Group();

        Scene scene = new Scene(parent);
        stage.setScene(scene);

        stage.show();
    }
}
