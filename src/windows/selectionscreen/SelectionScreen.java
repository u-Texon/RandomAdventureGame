package windows.selectionscreen;

import system.Settings;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import windows.fightingscreen.FightWindow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class SelectionScreen {


    public void changeScene(Stage stage) {
        double height = Settings.MIN_HEIGHT * 6;
        double width = Settings.MIN_WIDTH * 6;
        stage.setHeight(height);
        stage.setWidth(width);
        Group parent = new Group();
        stage.setTitle("OverWorld");

        Image fightButtonImage = null;
        try {
            fightButtonImage = new Image(new FileInputStream("src/resources/FightButton.gif"), 400, 100, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView fightButton = new ImageView(fightButtonImage);
        fightButton.setX(100);
        fightButton.setY(500);
        fightButton.setOnMouseClicked(action -> {

            FightWindow fightWindow = new FightWindow();
            fightWindow.changeScene(stage);

        });

        parent.getChildren().addAll(fightButton);
        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.CROSSHAIR);

        stage.setScene(scene);

    }
}