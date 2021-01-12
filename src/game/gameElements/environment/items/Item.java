package game.gameElements.environment.items;

import game.gameElements.environment.Player;

public abstract class Item {
    private int goldValue;

    public abstract void use();

    public Item(int goldValue) {
        this.goldValue = goldValue;
    }

    public void sell() {
        Player.getPlayer().gainGold(goldValue);
    }


    String getName() {
       return this.getClass().toString();
    }
}
