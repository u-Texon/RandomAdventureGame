package game.gameElements.environment.bars;

import game.gameElements.environment.mobs.Mob;

public class ManaBar extends Bar {

    public ManaBar(Mob mob, double width, int fontSize) {
        super(mob, width, fontSize);
    }

    @Override
    protected void style() {
        this.setStyle("-fx-accent: #1fdad6;"
                + "-fx-control-inner-background: #000000;"
                + "-fx-text-box-border: #aebdbd");
    }

    @Override
    protected double calcNewProgress() {
        return mob.getCurrentMana() / mob.getMaxMana();
    }

    @Override
    protected String getStatusText() {
        return String.format("%1$8s", String.format("%.2f-%d", mob.getCurrentMana(), mob.getMaxMana()));
    }
}
