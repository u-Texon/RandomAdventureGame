package sample;

import system.Settings;

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
import overworld.SelectionScreen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TitleScreen extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        double height = Settings.MIN_HEIGHT * 6;
        double width = Settings.MIN_WIDTH * 6;
        stage.setTitle("Title Screen");
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setResizable(false);
        VBox parent = new VBox();


        Button startButton = new Button("Start New Game");
        Font fontKemco = Font.loadFont(
                new FileInputStream("src/resources/Pixel_Bold.ttf"), 50);
        startButton.setFont(fontKemco);
        startButton.setOnAction(actionEvent -> {
            stage.close();
            SelectionScreen world = new SelectionScreen();
            try {
                world.start(new Stage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        Button settingsButton = new Button("Settings");
        settingsButton.setFont(fontKemco);
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
        closeButton.setFont(fontKemco);
        closeButton.setOnAction(e -> stage.close());

        ImageView image = new ImageView("https://www.linux-magazin.de/wp-content/uploads/2016/04/kernel_klein.png");

        parent.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(image, startButton, settingsButton, closeButton);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        scene.setCursor(Cursor.CROSSHAIR);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.F) {
                if (stage.isFullScreen()) {
                    parent.setScaleX(1);
                    parent.setScaleY(1);
                    stage.setFullScreen(false);

                } else {
                    double newX = Screen.getPrimary().getBounds().getWidth() / width;
                    double newY = Screen.getPrimary().getBounds().getHeight() / height;

                    parent.setScaleX(newX);
                    parent.setScaleY(newY);
                    stage.setFullScreen(true);
                }
            }
        });
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
