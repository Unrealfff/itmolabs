package com.mycompany.app.humans;

import java.util.ArrayList; 
import com.mycompany.app.items.*;
import com.mycompany.app.events.Participant;
import java.util.Map;
import java.util.HashMap;


public class Human extends AbstractHuman implements Participant{
    private ArrayList<Ability> skills = new ArrayList<Ability>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private Map<String, String> events = new HashMap<>();
    private int temp = 30;
    public Human(String name) {
        super(name);
    }

    public Human(String name, ArrayList<Item> items) {
        super(name);
        this.items = items;
    }

    public void addSkill(Ability skill) {
        for (int i = 0; i < this.skills.size(); i++) {
            if (this.skills.get(i).name() == skill.name()) {
                throw new IllegalArgumentException("Error: skill with name " + skill.name() + " already exists!");
            }
        }
        this.skills.add(skill);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public ArrayList<Ability> getSkills() {
        return this.skills;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public void addEvent(String event, String description) {
        this.events.put(event, description);
    }

    public void useSkill(String skillName) {
        for (int i = 0; i < this.skills.size(); i++) {
            if (this.skills.get(i).name() == skillName) {
                this.skills.get(i).use();
                break;
            }
        }
    }

    public int getTemp() {
        return this.temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void liveDay() {
        for(int i = 0; i < this.items.size(); i++) {
            this.items.get(i).use(this.temp);
        }
    }

    public void participate(String event) {
        if (event == "gainGather") {
            this.addSkill(new Ability("makeBread", "делаю хлеб"));
            System.out.println("Я получил возможность добывать хлеб");
        }
        else if (event == "whineAboutWeather") {
            System.out.println("В таком жарком климате не было нужды одеваться, но я стыдился ходить нагишом");
        }
        else if (this.events.containsKey(event)) {
            System.out.println(this.events.get(event));
        }
        else {
            System.out.println(this.name + " поучавствовал в " + event);
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        return this.getName();
    }

}