package com.mycompany.app.items;

public abstract class AbstractItem {
    protected String name;
    protected int amount;

    public AbstractItem() {

    }

    public AbstractItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return this.name;
    }

    public int getAmount() {
        return this.amount;
    }
}