package game.gameElements.environment.mobs;

import game.gameElements.environment.bars.HealthBar;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Enemy extends Mob {
    private final HealthBar healthBar;
    private final ImageView image;

    public Enemy(int health, double physicATK, int mana) {
        super(health, physicATK, mana);
        healthBar = new HealthBar(this, 300, 15);
        healthBar.setProgress(1);
        image = initImage();
    }

    public ImageView getImage() {
        return image;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public void spawnAnimation() {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }

            @Override
            protected void interpolate(double v) {
                getImage().setScaleX(v);
                getImage().setScaleY(v);

                getHealthBar().setScaleX(v);
                getHealthBar().setScaleY(v);
            }
        };
        animation.play();
    }

    public void takesHitAnimation() {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(400));
            }

            @Override
            protected void interpolate(double v) {
                if (v < 0.5) {
                    getImage().setScaleX(1 - v);
                    getImage().setScaleY(1 - v);
                } else {
                    getImage().setScaleX(v);
                    getImage().setScaleY(v);
                }
            }
        };
        animation.play();
    }

    public void attackAnimation() {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(400));
            }

            @Override
            protected void interpolate(double v) {
                if (v < 0.5) {
                    getImage().setScaleX(1 + v);
                    getImage().setScaleY(1 + v);
                } else {
                    getImage().setScaleX(2 - v);
                    getImage().setScaleY(2 - v);
                }
            }
        };
        animation.play();
    }

    public void dieAnimation() {
        RotateTransition rotate = new RotateTransition(Duration.millis(1500), getImage());
        rotate.setByAngle(1000);
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }
            @Override
            protected void interpolate(double v) {
                getImage().setScaleX(1 - v);
                getImage().setScaleY(1 - v);
            }
        };
        animation.setOnFinished(e -> rotate.stop());
        animation.play();
        rotate.play();
    }

    public abstract String getName();
    public abstract int getGoldValue();
    public abstract ImageView initImage();
}
