package game.gameElements.environment.mobs;

import java.util.Random;

public abstract class Mob {
    private int maxHealth;
    private int maxMana;
    private double currentHealth;
    private double physicATK;
    private double currentMana;

    /**
     * Constructor for a Mob
     * @param health health
     * @param physicATK physical attack value
     * @param mana amount of mana
     */
    public Mob(int health, double physicATK, int mana) {
        this.maxHealth = health;
        this.physicATK = physicATK;
        this.maxMana = mana;
        this.currentHealth = health;
        this.currentMana = mana;
    }

    /**
     * this mob will attack the selected mob and reducing its HP by the physicalAD of this mob.
     *
     * @param mob the mob that will take damage
     * @return the amount of dmg the selected mob will get
     */
    public double physicAttackOn(Mob mob) {
        Random random = new Random();
        double percentage = (random.nextInt(41) + 80);
        double dmgValue = getPhysicATK() * (percentage / 100); //dmg = 80% up to 120% from Mob AD
        mob.takeDmg(dmgValue);
        return dmgValue;
    }

    public void takeDmg(double dmgTaken) {
        this.currentHealth -= dmgTaken;
    }

    public void heal(double healValue) {
        this.currentHealth = ((currentHealth + healValue) > maxHealth) ? maxHealth : (currentHealth + healValue);
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public boolean isDead() {
        return this.currentHealth <= 0.1;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public double getPhysicATK() {
        return physicATK;
    }

    public double getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = currentMana;
    }

    /*
     * --------------MIGHT USE THIS FOR LEVELING--------------------
     *
     *     public void setMaxHealth(int maxHealth) {
     *         this.maxHealth = maxHealth;
     *     }
     *
     *     public void setMaxMana(int maxMana) {
     *         this.maxMana = maxMana;
     *     }
     *
     *     public void setPhysicATK(double physicATK) {
     *         this.physicATK = physicATK;
     *     }
     */

}