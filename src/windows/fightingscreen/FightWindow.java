package windows.fightingscreen;

import environment.Player;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FightWindow {


    public void changeScene(Stage stage) throws FileNotFoundException {
        stage.setTitle("FIGHT  !!!");
        GridPane parent = new GridPane();
        parent.setPrefHeight(700);
        parent.setPrefWidth(1200);

        parent.setPadding(new Insets(10, 10, 10, 10));
        parent.setVgap(1);
        parent.setHgap(1);
        parent.setGridLinesVisible(true);

        VBox enemyField = new VBox();
        enemyField.setAlignment(Pos.CENTER);
        enemyField.setPrefHeight(450);
        enemyField.setPrefWidth(700);


        VBox textField = new VBox();
        textField.setAlignment(Pos.CENTER);
        Label text = new Label("");
        Font fontText = Font.loadFont(new FileInputStream("src/resources/Pixel_Bold.ttf"), 25);
        text.setFont(fontText);
        text.setWrapText(true);
        text.setTextAlignment(TextAlignment.CENTER);
        textField.getChildren().addAll(text);
        textField.setPrefWidth(700);
        textField.setPrefHeight(250);

        GridPane buttonPane = new GridPane();
        buttonPane.setPrefWidth(500);
        buttonPane.setPrefHeight(250);
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setPadding(new Insets(5, 5, 5, 5));
        Font fontButton = Font.loadFont(new FileInputStream("src/resources/Pixel_Bold.ttf"), 35);
            Button attackButton = new Button("ATTACK");
            attackButton.setFont(fontButton);
            attackButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
            buttonPane.add(attackButton, 0, 0);
            attackButton.setOnAction(actionEvent -> {
                if (Fight.get().getEnemy().isDead()) {
                    return;
                }
                double dmg = Player.getPlayer().physicAttackOn(Fight.get().getEnemy());
                dmg = Math.round(dmg * 100.0) / 100.0;
                Fight.get().getEnemy().updateHealthBar();
                animateText(text, "You dealt " + dmg + " damage!");
                if (Fight.get().getEnemy().isDead()) {
                    animateText(text, "You have slain an " + Fight.get().getEnemy().getName() + "!!");
                    enemyField.getChildren().removeAll(Fight.get().getEnemy().getImage(),
                            Fight.get().getEnemy().getHealthBar());
                }
            });


            Button spellButton = new Button("SPELL");
            spellButton.setFont(fontButton);
            spellButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
            buttonPane.add(spellButton, 0, 1);


            Button itemButton = new Button("ITEMS");
            itemButton.setFont(fontButton);
            itemButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
            buttonPane.add(itemButton, 1, 0);


            Button runButton = new Button("RUN");
            runButton.setFont(fontButton);
            runButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
            buttonPane.add(runButton, 1, 1);
            runButton.setOnAction(action -> {
                stage.close();
            });


        parent.add(enemyField, 0, 0);
        parent.add(textField, 0, 1);
        parent.add(buttonPane, 1, 1);
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();


        animateText(text, "An enemy " + Fight.get().getEnemy().getName() + " has appeared!!");
        enemyField.getChildren().addAll(Fight.get().getEnemy().getImage(), Fight.get().getEnemy().getHealthBar());
    }



    public static void animateText(Label lbl, String descImp) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = descImp.length();
                final int n = Math.round(length * (float) frac);
                lbl.setText(descImp.substring(0, n));
            }
        };
        animation.play();
    }
}
