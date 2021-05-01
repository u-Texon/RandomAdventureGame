package game.gameElements.windows;

import javafx.scene.image.Image;
import game.gameElements.system.Settings;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import game.gameElements.windows.selectionscreen.SelectionScreen;
import game.resources.Resource;
import game.resources.audio.AudioPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TitleScreen extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        AudioPlayer.getInstance().loadSounds();
        double height = Settings.MIN_HEIGHT * 6;
        double width = Settings.MIN_WIDTH * 6;
        primaryStage.setTitle("Title Screen");
        primaryStage.setHeight(height);
        primaryStage.setWidth(width);
        primaryStage.setResizable(false);
        VBox parent = new VBox();


        Button startButton = new Button("Start New Game");
        Font font = Font.loadFont(new FileInputStream(Resource.FONT), 50);
        startButton.setFont(font);
        startButton.setOnAction(actionEvent -> {
            SelectionScreen s = new SelectionScreen();
            s.changeToStreet(primaryStage);
        });

        Button settingsButton = new Button("Settings");
        settingsButton.setFont(font);
        settingsButton.setOnAction(e -> {
            Stage settings = new Stage();
            VBox root = new VBox();
            settings.setHeight(400);
            settings.setWidth(600);
            settings.initModality(Modality.APPLICATION_MODAL);

            //TODO Implement this
            Label label = new Label("Needs To Be Implemented");
            label.setTextFill(Color.web("#a83232"));
            label.setFont(new Font("Noto Mono", 40));


            root.getChildren().addAll(label);
            root.setAlignment(Pos.CENTER);
            Scene scene = new Scene(root);
            settings.setScene(scene);
            settings.show();
        });


        Button closeButton = new Button("Close Game");
        closeButton.setFont(font);
        closeButton.setOnAction(e -> primaryStage.close());

        Image knightImage = new Image(new FileInputStream(Resource.KNIGHT), 250, 250, false, false);
        ImageView knight = new ImageView(knightImage);

        parent.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(knight, startButton, settingsButton, closeButton);

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F) {
                if (primaryStage.isFullScreen()) {
                    parent.setScaleX(1);
                    parent.setScaleY(1);
                    primaryStage.setFullScreen(false);

                } else {
                    double newX = Screen.getPrimary().getBounds().getWidth() / width;
                    double newY = Screen.getPrimary().getBounds().getHeight() / height;

                    parent.setScaleX(newX);
                    parent.setScaleY(newY);
                    primaryStage.setFullScreen(true);
                }
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
