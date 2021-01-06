package environment.mobs;
import java.util.Random;

public abstract class Mob {
    private int maxHealth;
    private int maxMana;
    private double currentHealth;
    private double physicATK;
    private double currentMana;

    public Mob(int health, double physicATK, int mana) {
        this.maxHealth = health;
        this.physicATK = physicATK;
        this.maxMana = mana;
        this.currentHealth = health;
        this.currentMana = mana;
    }

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
        return this.currentHealth <= 0;
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

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setCurrentMana(double currentMana) {
        this.currentMana = currentMana;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setPhysicATK(double physicATK) {
        this.physicATK = physicATK;
    }

}