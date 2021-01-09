package environment;

import environment.items.Item;
import environment.mobs.Mob;
import environment.spells.FireShot;
import environment.spells.Spell;

import java.util.ArrayList;
import java.util.List;

public final class Player extends Mob {
    private static Player player = null;
    private double magicAD;
    private int gold;
    private final List<Item> inventory;
    private final List<Spell> spells;

    //TODO: implement leveling system

    private Player() {
        super(20, 5, 10);
        this.magicAD = 2;
        this.gold = 0;
        inventory = new ArrayList<>(); //might use different List type
        spells = new ArrayList<>();
        spells.add(new FireShot());
    }

    public static Player getPlayer() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public double castSpell(Spell spell, Mob target) {
        //remember to check for mana cost
        return spell.cast(target);
    }

    public void receive(Item item) {
        inventory.add(item);
    }

    public void sellItem(Item item) {
        item.sell();
        inventory.remove(item);
    }

    public void gainGold(int goldAmount) {
        this.gold += goldAmount;
    }

    public void useItem(Item item) {
        item.use();
        inventory.remove(item);
    }

    public double getMagicAD() {
        return magicAD;
    }

    public List<Spell> getSpells() {
        return spells;
    }
}
