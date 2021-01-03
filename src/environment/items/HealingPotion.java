package environment.items;

import environment.Player;

public class HealingPotion extends Item {
    /**
     * healing amount for a standard potion
     */
    public static final int HEAL_AMOUNT = 10;

    public HealingPotion() {
        super(HEAL_AMOUNT / 2);
    }

    @Override
    public void use() {
        Player.getPlayer().heal(HEAL_AMOUNT);
    }
}
