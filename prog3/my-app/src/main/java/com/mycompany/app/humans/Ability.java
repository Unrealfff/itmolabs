package com.mycompany.app.humans;

public record Ability(String name, String info) {
    public void use() {
        System.out.println(info);
    }
}