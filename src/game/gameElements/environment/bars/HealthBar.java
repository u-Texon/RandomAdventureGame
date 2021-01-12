package game.gameElements.environment.bars;

import game.gameElements.environment.mobs.Mob;

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
        return String.format("%1$8s", String.format("%.2f-%d", mob.getCurrentHealth(), mob.getMaxHealth()));
    }
}