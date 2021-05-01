package game.gameElements.environment;

import game.gameElements.environment.bars.HealthBar;
import game.gameElements.environment.bars.ManaBar;
import game.gameElements.environment.items.HealingPotion;
import game.gameElements.environment.items.Item;
import game.gameElements.environment.items.ManaPotion;
import game.gameElements.environment.mobs.Mob;
import game.gameElements.environment.spells.FireShot;
import game.gameElements.environment.spells.Spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Player extends Mob {
    private static Player player = null;
    private double magicAD;
    private int gold;
    private final HashMap<Item, Integer> inventory;
    private final List<Spell> spells;

    //TODO: implement leveling java.gameElements.system

    private Player() {
        super(20, 5, 10);
        this.magicAD = 2;
        this.gold = 0;
        inventory = new HashMap<Item, Integer>();
        spells = new ArrayList<>();
        spells.add(new FireShot());
        receiveItem(new HealingPotion(), 2);
        receiveItem(new ManaPotion(), 2);
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

    public void receiveItem(Item item) {
        for (Item i: inventory.keySet()) {
            if (i.getClass() == item.getClass()) {
                inventory.replace(i, inventory.get(i) + 1);
                return;
            }
        }
        inventory.put(item, 1);
    }

    public void receiveItem(Item item, int amount) {
        for (Item i: inventory.keySet()) {
            if (i.getClass() == item.getClass()) {
                inventory.replace(i, inventory.get(i) + amount);
                return;
            }
        }
        inventory.put(item, amount);
    }

    private void removeItem(Item item) {
        if (inventory.containsKey(item)) {
            if (inventory.get(item) <= 1 ) {
                inventory.remove(item);
            } else {
                inventory.replace(item, inventory.get(item) - 1);
            }
        }
    }

    public void sellItem(Item item) {
        item.sell();
        removeItem(item);
    }

    public HashMap<Item, Integer> getItems() {
        return inventory;
    }

    public void gainGold(int goldAmount) {
        this.gold += goldAmount;
    }

    public void useItem(Item item) {
        item.use();
        removeItem(item);
    }

    public double getMagicAD() {
        return magicAD;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public int getGold() {
        return gold;
    }

    public void receiveGold(int goldAmount) {
        this.gold += goldAmount;
    }
}
