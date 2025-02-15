package com.mycompany.app.items;

import java.util.ArrayList;
import com.mycompany.app.humans.*;

public class Chest extends Item{
    private ArrayList<Item> treasure = new ArrayList<Item>();
    private Human owner;

    public Chest(String name, String description, Human owner) {
        super(name, description, 0, 0);
        this.owner = owner;
    }

    public Chest(String name, String description, Human owner, ArrayList<Item> treasure) {
        super(name, description, 0, 0);
        this.treasure = treasure;
        this.owner = owner;
    }

    public void addTreasure(Item thing) {
        this.treasure.add(thing);
    }

    public Human getOwner() {
        return this.owner;
    }

    public void participate(String event) {
        if (event == "foundTreasure") {
            System.out.println(this.owner.getName() + " нашёл в сундуке сокровище:");
            int treasureSize = this.treasure.size();
            for (int i = 0; i < treasureSize; i++) {
                this.owner.addItem(this.treasure.get(0));
                System.out.println(this.treasure.get(0).getName() + "(" + this.treasure.get(0).getAmount() + ")");
                this.treasure.remove(0);
            }
        }
    }
}