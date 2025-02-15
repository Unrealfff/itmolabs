package com.mycompany.app;

import com.mycompany.app.humans.*;
import com.mycompany.app.items.*;
import com.mycompany.app.events.*;
import com.mycompany.app.clothes.*;

public class App {
    public static void main(String[] args) {
        Human chara = new Human("Иван");
        Timeline time = new Timeline();
        Item ink = new Item("чернила", "", 1, 1);
        Item bread = new Item("хлеб", "я растягивал его до последней возможности", 2, 1);
        Scythe scythe = new Scythe("коса", "для употребления в пищу", 1, -11);
        Chest chest = new Chest("сундук", "одежда", chara);
        chest.addTreasure(Clothes.SHIRT.item(36, 0));
        chest.addTreasure(Clothes.OVERCOAT.item(5, 0));
        chest.addTreasure(Clothes.HAT.item(2, 1));
        chara.addItem(ink);
        chara.addItem(bread);
        chara.addItem(scythe);
        chara.addEvent("thankGod", "Я благодарю бога");
        time.addTrigger(3, "harvest"); 
        time.addTrigger(4, "thankGod");
        time.addTrigger(4, "gainGather");
        time.addTrigger(5, "foundTreasure");
        time.addTrigger(9, "whineAboutWeather");
        time.addParticipant("harvest", scythe);
        time.addParticipant("gainGather", chara);
        time.addParticipant("thankGod", chara);
        time.addParticipant("foundTreasure", chest);
        time.addParticipant("whineAboutWeather", chara);
        for(int i = 0; i < 10; i++) {
            chara.liveDay();
            time.tick();
        }
    }
}
