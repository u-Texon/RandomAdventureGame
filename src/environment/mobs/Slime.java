package environment.mobs;

public class Slime extends Mob {
    /**
     * standard slime max health
     */
    public static final int HEALTH = 15;
    /**
     * standard slime attack damage
     */
    public static final double ATK = 3;
    /**
     * standard slime max mana
     */
    public static final int MANA = 0;


    public Slime(int health, double physicATK, int mana) {
        super(health, physicATK, mana);
    }

}
