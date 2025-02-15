package com.mycompany.app.clothes;

import com.mycompany.app.items.*;

public enum Clothes {
    SHIRT("рубашка", "", 20, 40),
    OVERCOAT("шинель", "", -10, 10),
    HAT("шапка", "", -20, 40);

    private String name;
    private String description;
    private int highTemp;
    private int lowTemp;
    
    
    Clothes(String name, String description, int lowTemp, int highTemp) {
        this.name = name;
        this.description = description;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public Item item(int amount, int spendPerDay) {
        Item item = new Item(this.name, this.description, amount, spendPerDay);
        item.setHighTemp(this.highTemp);
        item.setLowTemp(this.lowTemp);
        return item;
    }
}