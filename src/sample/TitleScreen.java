package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class TitleScreen extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Title Screen");
        stage.setHeight(800);
        stage.setWidth(1200);
        VBox parent = new VBox();

        Button startButton = new Button("Start New Game");
        Font fontKemco = Font.loadFont(
                new FileInputStream("C:\\Users\\david\\.Font\\kemco_pixel\\Pixel_Bold.ttf"), 50);
        startButton.setFont(fontKemco);

        Button closeButton = new Button("Close Game");
        closeButton.setFont(fontKemco);
        closeButton.setOnAction(e -> stage.close());


        parent.setAlignment(Pos.CENTER);
        parent.getChildren().addAll(startButton, closeButton);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
