package environment.spells;

import environment.Player;
import environment.mobs.Mob;

import java.util.Random;

public abstract class Spell {
    private final int manaCost;
    private final int initialDmg;

    public Spell(int manaCost, int initialDmg) {
        this.manaCost = manaCost;
        this.initialDmg = initialDmg;
    }

    public double cast(Mob target) {
        if (Player.getPlayer().getCurrentMana() - manaCost < 0) {
            //not enough mana
            return -1;
        }
        double magicDMG = Player.getPlayer().getMagicAD() + initialDmg;
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
