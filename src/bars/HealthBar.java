package bars;

import environment.mobs.Mob;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.util.Duration;

public class HealthBar extends Bar {

    public HealthBar(Mob mob, double width, int fontSize) {
        super(mob, width, fontSize);
    }

    @Override
    protected void style() {
        this.setStyle("-fx-accent: #c81a1a;"
                + "-fx-control-inner-background: #000000;"
                + "-fx-text-box-border: #aebdbd");
    }

    @Override
    protected double calcNewProgress() {
        return mob.getCurrentHealth() / mob.getMaxHealth();
    }

    @Override
    protected String getStatusText() {
        return mob.getCurrentHealth() + "-" + mob.getMaxHealth();
    }
}