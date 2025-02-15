package com.mycompany.app.humans;

public abstract class AbstractHuman {
    protected String name;

    public AbstractHuman() {
    }

    public AbstractHuman(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract void addSkill(Ability skill);

    public abstract void useSkill(String skillName);
}