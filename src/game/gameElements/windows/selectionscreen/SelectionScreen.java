package game.gameElements.windows.selectionscreen;

import game.gameElements.environment.Player;
import game.gameElements.environment.mobs.Slime;
import game.gameElements.system.Settings;
import game.resources.audio.AudioPlayer;
import javafx.animation.PauseTransition;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.gameElements.windows.fightingscreen.FightWindow;
import game.resources.Resource;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class SelectionScreen {
    private ImageView background;
    private ImageView knight;
    private Image knightLeft;
    private Image knightImage;
    private ImageView slime;
    private Group parent;

    public SelectionScreen() {
        parent = new Group();

        try {
            initBackground();
            initSlime();
            initKnight();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void initBackground() throws FileNotFoundException {
        background = new ImageView(new Image(new FileInputStream(Resource.STREET)));
        background.setLayoutY(-1000);
        parent.getChildren().add(background);
    }

    private void initSlime() throws FileNotFoundException {
        Image slimeImage = new Image(new FileInputStream(Resource.SLIME), 130, 100, false, false);
        slime = new ImageView(slimeImage);
        slime.setLayoutX(1000);
        slime.setLayoutY(550);

        parent.getChildren().add(slime);
    }

    private void initKnight() throws FileNotFoundException {
        knightImage = new Image(new FileInputStream(Resource.KNIGHT), 150, 150, false, false);
        knightLeft = new Image(new FileInputStream(Resource.KNIGHT_LEFT), 150, 150, false, false);
        knight = new ImageView(knightImage);
        knight.setLayoutY(500);
        knight.setLayoutX(200);

        parent.getChildren().add(knight);
    }

    public void changeToStreet(Stage stage) {
        double height = Settings.MIN_HEIGHT * 10;
        double width = Settings.MIN_WIDTH * 6;
        stage.setHeight(height);
        stage.setWidth(width);
        stage.setY(0);
        stage.setTitle("Street");
        stage.setResizable(false);

        slime.setOnMouseClicked(e -> {
            FightWindow f = new FightWindow();
            f.changeScene(stage);
        });

        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.CROSSHAIR);

        stage.setScene(scene);
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W:
                    knight.setLayoutY(knight.getLayoutY() - 10);
                    break;
                case S:
                    knight.setLayoutY(knight.getLayoutY() + 10);
                    break;
                case D:
                    knight.setImage(knightImage);
                    knight.setLayoutX(knight.getLayoutX() + 10);
                    break;
                case A:
                    knight.setImage(knightLeft);
                    knight.setLayoutX(knight.getLayoutX() - 10);
                    break;
                default:
                    break;
            }
        });
    }


    public static void changeScene(Stage stage) {
        double height = Settings.MIN_HEIGHT * 6;
        double width = Settings.MIN_WIDTH * 6;
        stage.setHeight(height);
        stage.setWidth(width);
        Group parent = new Group();
        stage.setTitle("OverWorld");

        Image fightButtonImage = null;
        try {
            fightButtonImage = new Image(new FileInputStream(Resource.FIGHT_BUTTON), 400, 100, false, false);
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

        Button healButton = new Button("HEAL");
        healButton.setFont(Resource.getFont(64));
        healButton.setStyle("-fx-background-color: #921696");
        healButton.setTextFill(Color.web("#00d1a4"));
        healButton.setLayoutY(500);
        healButton.setLayoutX(600);
        healButton.setOnAction(actionEvent -> {
            AudioPlayer.getInstance().playHealSound();
            Player player = Player.getPlayer();
            player.heal(player.getMaxHealth());
            player.gainMana(player.getMaxMana());
        });

        Label playerGold = new Label("Gold :" + Player.getPlayer().getGold());
        playerGold.setFont(Resource.getFont(20));
        playerGold.setTextFill(Color.web("#c1c400"));

        parent.getChildren().addAll(fightButton, healButton, playerGold);
        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.CROSSHAIR);

        stage.setScene(scene);
    }
}