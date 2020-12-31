package overworld;

import system.Settings;
import fight.FightingScreen;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SelectionScreen {


    public void start(Stage stage) throws FileNotFoundException {
        double height = Settings.MIN_HEIGHT * 6;
        double width = Settings.MIN_WIDTH * 6;
        stage.setHeight(height);
        stage.setWidth(width);
        Group parent = new Group();
        stage.setTitle("OverWorld");
        stage.setResizable(false);

        ImageView fightButton = new ImageView(new Image(new FileInputStream("src/resources/FightButton.gif")));
        fightButton.setScaleX(1.5);
        fightButton.setScaleY(1.5);
        fightButton.setX(100);
        fightButton.setY(500);

        fightButton.setOnMouseClicked(action -> {
            FightingScreen fight = new FightingScreen();
            fight.start(new Stage());

            stage.close();
        });

        parent.getChildren().addAll(fightButton);
        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.CROSSHAIR);
        stage.setScene(scene);

        stage.show();
    }
}