package windows.fightingscreen;

import environment.mobs.Enemy;
import environment.mobs.Slime;

public final class Fight {
    private static Fight fight = null;
    private Enemy enemy;

    private Fight() {
        enemy = generateNewEnemy();
    }

    public static Fight get() {
        if (fight == null) {
            fight = new Fight();
        }
        return fight;
    }

    public void resetFight() {
        enemy = generateNewEnemy();
    }



    private Enemy generateNewEnemy() {
        //TODO: generate random enemy
        return new Slime(Slime.HEALTH, Slime.ATK, Slime.MANA);
    }


    public Enemy getEnemy() {
        return enemy;
    }
}
