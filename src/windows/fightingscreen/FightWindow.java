package windows.fightingscreen;

import environment.Player;
import environment.mobs.Enemy;
import environment.spells.Spell;
import bars.HealthBar;
import bars.ManaBar;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class FightWindow {
    private final Player player = Player.getPlayer();
    private final Enemy enemy = Fight.get().getEnemy();

    private GridPane parent;
    private VBox enemyField;
    private VBox playerInterface;

    private HealthBar healthBar;
    private ManaBar manaBar;

    private Button attackButton;
    private Button spellButton;
    private Button itemButton;
    private Button runButton;

    /**
     * the text that will be shown in in the FightWindow
     */
    public static final Label TEXT = new Label("");

    public FightWindow() {
        initParent();
        initTextField();
        initEnemyField();
        initPlayerInterface();
        initButtonPane();
    }

    private void initParent() {
        parent = new GridPane();
        parent.setPrefHeight(700);
        parent.setPrefWidth(1200);
        parent.setStyle("-fx-background-color: #222222");

        parent.setPadding(new Insets(10, 10, 10, 10));
        parent.setVgap(1);
        parent.setHgap(1);
    }

    private void initEnemyField() {
        enemyField = new VBox();
        enemyField.setAlignment(Pos.CENTER);
        enemyField.setPrefHeight(450);
        enemyField.setPrefWidth(700);

        enemyField.getChildren().addAll(enemy.getImage(), enemy.getHealthBar());
        enemy.getHealthBar().setPrefSize(200, 20);


        parent.add(enemyField, 0, 0);
    }

    private void initPlayerInterface() {
        double width = enemyField.getPrefWidth() - parent.getPrefWidth();
        double height = enemyField.getPrefHeight() - parent.getPrefHeight();
        playerInterface = new VBox();
        playerInterface.setAlignment(Pos.BOTTOM_CENTER);
        playerInterface.setPrefSize(width, height);


        healthBar = new HealthBar(player, 300, 15);
        manaBar = new ManaBar(player, 300, 15);

        playerInterface.getChildren().addAll(manaBar.getStatus(), healthBar.getStatus());

        parent.add(playerInterface, 1, 0);
    }

    private void initTextField() {
        VBox textField = new VBox();
        textField.setAlignment(Pos.CENTER);
        Font fontText = null;
        try {
            fontText = Font.loadFont(new FileInputStream("src/resources/Pixel_Bold.ttf"), 25);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TEXT.setFont(fontText);
        TEXT.setWrapText(true);
        TEXT.setTextAlignment(TextAlignment.CENTER);
        TEXT.setTextFill(Color.web("#d4c4be"));
        textField.getChildren().addAll(TEXT);
        textField.setPrefWidth(700);
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
        Font fontButton = null;
        try {
            fontButton = Font.loadFont(new FileInputStream("src/resources/Pixel_Bold.ttf"), 35);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //ATTACK-Button
        attackButton = new Button("ATTACK");
        attackButton.setFont(fontButton);
        attackButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        attackButton.setTextFill(Color.web("#d4c4be"));
        attackButton.setStyle("-fx-background-color: #041158");
        buttonPane.add(attackButton, 0, 0);
        attackButton.setOnAction(actionEvent -> {
            if (enemy.isDead()) {
                return;
            }
            double dmg = player.physicAttackOn(enemy);
            calcDmg(dmg);
        });
        //SPELL-Button
        spellButton = new Button("SPELL");
        spellButton.setFont(fontButton);
        spellButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        spellButton.setTextFill(Color.web("#d4c4be"));
        spellButton.setStyle("-fx-background-color: #041158");
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
                    calcDmg(dmg);
                    manaBar.updateAndAnimate();
                }
            }

        });
        //ITEM-Button
        itemButton = new Button("ITEMS");
        itemButton.setFont(fontButton);
        itemButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        itemButton.setTextFill(Color.web("#d4c4be"));
        itemButton.setStyle("-fx-background-color: #041158");
        buttonPane.add(itemButton, 0, 1);
        //RUN-Button
        runButton = new Button("RUN");
        runButton.setFont(fontButton);
        runButton.setPrefSize(buttonPane.getPrefWidth() / 2, buttonPane.getPrefHeight() / 2);
        runButton.setTextFill(Color.web("#d4c4be"));
        runButton.setStyle("-fx-background-color: #041158");
        buttonPane.add(runButton, 1, 1);
        runButton.setOnAction(action -> Platform.exit());

        parent.add(buttonPane, 1, 1);
    }


    public void changeScene(Stage stage) {
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
    }


    private void calcDmg(double dmg) {
        double dmgValue = Math.round(dmg * 100.0) / 100.0;
        enemy.getHealthBar().updateAndAnimate();
        animateText("You dealt " + dmgValue + " damage!");
        enemy.getsHitAnimation();
        pauseAllButtons(2000);
        if (enemy.isDead()) {
            enemy.die();
            animateText("You dealt " + dmgValue + " damage and you have slain an " + enemy.getName() + "!!");
            enemyField.getChildren().removeAll(enemy.getHealthBar());
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
        double dmg = enemy.physicAttackOn(player);
    }


    public static void animateText(String descImp) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = descImp.length();
                final int n = Math.round(length * (float) frac);
                TEXT.setText(descImp.substring(0, n));
            }
        };
        animation.play();
    }

}
