package game.gameElements.environment.spells;

import game.gameElements.environment.Player;
import game.gameElements.environment.mobs.Mob;

import java.util.Random;

public abstract class Spell {
    private final int manaCost;
    private final int initialDmg;

    public Spell(int manaCost, int initialDmg) {
        this.manaCost = manaCost;
        this.initialDmg = initialDmg;
    }

    public double cast(Mob target) {
        Player player = Player.getPlayer();
        if (player.getCurrentMana() - manaCost < 0) {
            //not enough mana
            return -1;
        }
        player.setCurrentMana(player.getCurrentMana() - manaCost);
        double magicDMG = player.getMagicAD() + initialDmg;
        Random random = new Random();
        double percentage = (random.nextInt(41) + 80);
        double dmgValue = magicDMG * (percentage / 100); //dmg = 80% up to 120%
        target.takeDmg(dmgValue);
        effect();
        return dmgValue;
    }

    public abstract void effect();

    public int getManaCost() {
        return manaCost;
    }

    public int getInitialDmg() {
        return initialDmg;
    }
}
