package com.mycompany.app.items;

public class Scythe extends Item{
    protected int enough = 10;

    public Scythe(String name, String description, int amount, int spendPerDay) {
        super(name, description, amount, spendPerDay);
    }

    public int getEnough() {
        return this.enough;
    }

    public void setEnough(int enough) {
        this.enough = enough;
    }

    @Override
    public void participate(String event) {
        if (event == "harvest") {
            if (this.amount >= this.enough) {
                System.out.println("собрали достаточно зерна");
                if (this.description != "") {
                    System.out.println(this.description);
                }
            }
            else {
                System.out.println("собрали недостаточно зерна");
            }
        }
    }
}