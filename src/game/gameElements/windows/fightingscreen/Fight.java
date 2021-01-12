package game.gameElements.windows.fightingscreen;

import game.gameElements.environment.mobs.Enemy;
import game.gameElements.environment.mobs.Slime;

public final class Fight {
    private static Fight fight = null;
    private Enemy enemy;
    private Phase phase;

    private Fight() {
        phase = Phase.YOUR_TURN;
        enemy = generateNewEnemy();
    }

    public static Fight get() {
        if (fight == null) {
            fight = new Fight();
        }
        return fight;
    }


    public void getAction() {
        if (phase == Phase.ENEMY_TURN) {

        } else if (phase == Phase.FINISHED) {

        }
    }


    public void resetFight() {
        phase = Phase.YOUR_TURN;
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
