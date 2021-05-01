package game.gameElements.windows.fightingscreen;

import game.gameElements.environment.Player;
import game.gameElements.environment.mobs.Enemy;
import game.gameElements.environment.spells.Spell;
import game.gameElements.environment.bars.HealthBar;
import game.gameElements.windows.inventory.InventoryScreen;
import game.gameElements.windows.selectionscreen.SelectionScreen;
import game.resources.Resource;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import game.resources.audio.AudioPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FightWindow {
    private static final int ANIMATION_TIME = 1500;
    private final int numThreads = Runtime.getRuntime().availableProcessors();
    private final Player player = Player.getPlayer();
    private final Enemy enemy = Fight.get().getEnemy();

    private Stage stage;

    private final GridPane parent;
    private final AnchorPane enemyField;
    private final VBox playerInterface;
    private final VBox textField;



    private Button attackButton;
    private Button spellButton;
    private Button itemButton;
    private Button runButton;
    private Button backButton;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 700;

    /**
     * the text that will be shown in in the FightWindow
     */
    public static final Label TEXT = new Label("");

    public FightWindow() {
        parent = new GridPane();
        enemyField = new AnchorPane();
        playerInterface = new VBox();
        textField = new VBox();

        ExecutorService exe = Executors.newFixedThreadPool(numThreads);
        exe.execute(this::initParent);
        exe.execute(this::initTextField);
        exe.execute(this::initEnemyField);
        exe.execute(this::initPlayerInterface);
        exe.execute(this::initButtonPane);

        exe.shutdown();
        try {
            exe.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initParent() {
        parent.setPrefHeight(HEIGHT);
        parent.setPrefWidth(WIDTH);
        parent.setStyle("-fx-background-color: #222222");

        parent.setPadding(new Insets(10, 10, 10, 10));
        parent.setVgap(1);
        parent.setHgap(1);
    }

    private void initEnemyField() {
        ImageView imageEnemy = enemy.getImage();
        HealthBar bar = enemy.getHealthBar();
        Label enemyHealth = bar.getStatus();
        enemyField.setPrefSize(WIDTH - 500, HEIGHT - 250);

        backButton = new Button("Back");
        Font font = Resource.getFont(20);
        backButton.setFont(font);
        backButton.setTextFill(Color.web("#222222"));
        backButton.setStyle("-fx-background-color: red");
        backButton.setOnAction(actionEvent -> returnBack());
        backButton.setVisible(false);

        imageEnemy.setLayoutY(125);
        imageEnemy.setLayoutX(250);

        bar.setPrefSize(200, 20);
        enemyHealth.setLayoutX(290);
        enemyHealth.setLayoutY(335);

        enemyField.getChildren().addAll(backButton, imageEnemy, enemyHealth);
        parent.add(enemyField, 0, 0);
    }

    private void initPlayerInterface() {
        double width = 450 - parent.getPrefWidth();
        double height = 700 - parent.getPrefHeight();

        playerInterface.setAlignment(Pos.BOTTOM_CENTER);
        playerInterface.setPrefSize(width, height);

        playerInterface.getChildren().addAll(player.getHealthBar().getStatus(), player.getManaBar().getStatus());

        parent.add(playerInterface, 1, 0);
    }

    private void initTextField() {
        textField.setAlignment(Pos.CENTER);
        Font fontText = null;
        try {
            fontText = Font.loadFont(new FileInputStream(Resource.FONT), 25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TEXT.setFont(fontText);
        TEXT.setWrapText(true);
        TEXT.setTextAlignment(TextAlignment.CENTER);
        TEXT.setTextFill(Color.web("#d4c4be"));
        textField.getChildren().addAll(TEXT);
        textField.setPrefWidth(WIDTH - 500);
        textField.setPrefHeight(250);

        parent.add(textField, 0, 1);
    }

    private void initButtonPane() {
        GridPane buttonPane = new GridPane();
        buttonPane.setPrefWidth(500);
        buttonPane.setPrefHeight(250);
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setPadding(new Insets(5, 5, 5, 5));

        attackButton = new Button("ATTACK");
        spellButton = new Button("SPELL");
        itemButton = new Button("ITEMS");
        runButton = new Button("RUN");

        //set fonts
        Font fontButton = Resource.getFont(35);
        attackButton.setFont(fontButton);
        spellButton.setFont(fontButton);
        itemButton.setFont(fontButton);
        runButton.setFont(fontButton);


        //ATTACK-Button
        attackButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        attackButton.setTextFill(Color.web("#d4c4be"));
        attackButton.setStyle("-fx-background-color: black");
        buttonPane.add(attackButton, 0, 0);
        attackButton.setOnAction(actionEvent -> {
            if (enemy.isDead()) {
                return;
            }
            AudioPlayer.getInstance().playPunchSound();
            double dmg = player.physicAttackOn(enemy);
            attackEnemy(dmg);
        });


        //SPELL-Button
        spellButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        spellButton.setTextFill(Color.web("#d4c4be"));
        spellButton.setStyle("-fx-background-color: black");
        buttonPane.add(spellButton, 1, 0);
        spellButton.setOnAction(action -> {
            List<Spell> spells = player.getSpells();
            if (!spells.isEmpty()) {
                if (enemy.isDead()) {
                    return;
                }
                Spell spell = spells.get(0);
                double dmg = player.castSpell(spell, enemy);
                if (dmg == -1) {
                    animateText("You don't have enough mana to cast this spell");
                } else {
                    AudioPlayer.getInstance().playFireSound();
                    ///////////////////////////////////////////////////////
                    ImageView explosion = null;
                    try {
                         explosion = new ImageView(new Image(new FileInputStream(Resource.EXPLOSION)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    explosion.setLayoutX(enemy.getImage().getLayoutX());
                    explosion.setLayoutY(enemy.getImage().getLayoutY() - 50);
                    enemyField.getChildren().add(explosion);
                    PauseTransition p = new PauseTransition(Duration.millis(1500));
                    ImageView finalExplosion = explosion;
                    p.setOnFinished(e -> enemyField.getChildren().removeAll(finalExplosion));
                    p.play();
                    //////////////////////////////////////////////////////////
                    attackEnemy(dmg);
                }
            }

        });


        //ITEM-Button
        itemButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        itemButton.setTextFill(Color.web("#d4c4be"));
        itemButton.setStyle("-fx-background-color: black");
        itemButton.setOnAction(e -> itemButtonEffect());
        buttonPane.add(itemButton, 0, 1);


        //RUN-Button
        runButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        runButton.setTextFill(Color.web("#d4c4be"));
        runButton.setStyle("-fx-background-color: black");
        buttonPane.add(runButton, 1, 1);
        runButton.setOnAction(action -> {
            if (enemy.isDead()) {
                return;
            }
            Random random = new Random();
            int num = random.nextInt(3);
            if (num < 2) {
                animateText("You successfully ran away!");
                pauseAllButtons(2000);
                PauseTransition p = new PauseTransition(Duration.millis(2000));
                p.setOnFinished(e -> returnBack());
                p.play();
            } else {
                animateText("You failed to Run away..");
                pauseAllButtons(2000);
                PauseTransition p = new PauseTransition(Duration.millis(2000));
                p.setOnFinished(e -> enemyAttack());
                p.play();
            }
        });
        parent.add(buttonPane, 1, 1);
    }


    public void changeScene(Stage stage) {
        this.stage = stage;
        stage.setTitle("FIGHT  !!!");
        stage.setResizable(true);

        stage.setWidth(parent.getPrefWidth());
        stage.setHeight(parent.getPrefHeight());

        Scene scene = new Scene(parent);
        scene.setCursor(Cursor.CROSSHAIR);
        stage.setScene(scene);
        stage.show();

        enemy.spawnAnimation();
        animateText("An enemy " + enemy.getName() + " has appeared!!");
        pauseAllButtons(ANIMATION_TIME);
    }


    private void attackEnemy(double dmg) {
        double dmgValue = Math.round(dmg * 100.0) / 100.0;
        enemy.getHealthBar().updateAndAnimate();
        if (!enemy.isDead()) {
            int waitTime = 3000;
            animateText("You dealt " + dmgValue + " damage!");
            enemy.takesHitAnimation();
            pauseAllButtons(waitTime);
            PauseTransition p = new PauseTransition(Duration.millis(waitTime));
            p.setOnFinished(e -> enemyAttack());
            p.play();
        } else {
            enemy.dieAnimation();
            animateText("You dealt " + dmgValue + " damage and you have slain an " + enemy.getName() + "!!");
            player.receiveGold(enemy.getGoldValue());
            enemyField.getChildren().removeAll(enemy.getHealthBar(), enemy.getHealthBar().getStatus());
            backButton.setVisible(true);
        }
    }

    public void pauseAllButtons(int timeMillis) {
        attackButton.setDisable(true);
        spellButton.setDisable(true);
        itemButton.setDisable(true);
        runButton.setDisable(true);

        PauseTransition p = new PauseTransition(Duration.millis(timeMillis));
        p.setOnFinished(e -> {
            attackButton.setDisable(false);
            spellButton.setDisable(false);
            itemButton.setDisable(false);
            runButton.setDisable(false);
        });
        p.play();
    }

    public void enemyAttack() {
        pauseAllButtons(ANIMATION_TIME);
        double dmg = enemy.physicAttackOn(player);
        dmg = Math.round(dmg * 100.0) / 100.0;
        animateText("The " + enemy.getName() + " fought back and dealt " + dmg + " to you!");
        enemy.attackAnimation();

        if (player.isDead()) {
            animateText("You died :(");
            //...
        }

    }

    private void returnBack() {
        SelectionScreen.changeScene(stage);
        Fight.get().resetFight();
    }


    public static void animateText(String descImp) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(ANIMATION_TIME));
            }

            protected void interpolate(double frac) {
                final int length = descImp.length();
                final int n = Math.round(length * (float) frac);
                TEXT.setText(descImp.substring(0, n));
            }
        };
        animation.play();
    }

    public void itemButtonEffect() {
        if (enemy.isDead()) {
            return;
        }
        if (player.getItems().isEmpty()) {
            animateText("Empty Inventory");
            return;
        }
        InventoryScreen.show();
    }

}
