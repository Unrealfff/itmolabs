package com.mycompany.app.items;

import com.mycompany.app.events.Participant;
import com.mycompany.app.exceptions.*;

public class Item extends AbstractItem implements Participant{
    protected int spendPerDay;
    protected String description;
    protected int highTemp = 40;
    protected int lowTemp = 0;
    protected int lastTemp;

    public Item(String name, String description, int amount, int spendPerDay) {
        super(name, amount);
        this.spendPerDay = spendPerDay;
        this.description = description;
    }

    public void use(int temp) {
        if (!this.isFinished() && !this.wrongTemp()) {
            try {
                if (this.highTemp < temp) {
                    throw new TooHotException("too hot to use " + this.toString(), null);
                }
                else if (this.lowTemp > temp) {
                    throw new TooColdException("too cold to use " + this.toString(), null);
                }
                this.amount -= spendPerDay;
                if (this.amount <= 0) {
                    throw new AmountEqualsZeroException(this.toString() + " has ended", null);
                }
            }
            catch (AmountEqualsZeroException e) {
                this.end();
            }
            catch (TooColdException e) {
                System.out.println("слишком холодно для использования " + this.name);
            }
            catch (TooHotException e) {
                System.out.println("слишком жарко для использования " + this.name);
            }
        }
        this.lastTemp = temp;
    }

    void add(int n) {
        this.amount += n;
    }

    void setAmount(int n) {
        this.amount = n;
    }

    int getSpendPerDay() {
        return this.spendPerDay;
    }

    public int getHighTemp() {
        return this.highTemp;
    }

    public int getLowTemp() {
        return this.lowTemp;
    }

    public void setHighTemp(int highTemp) {
        this.highTemp = highTemp;
    }

    public void setLowTemp(int lowTemp) {
        this.lowTemp = lowTemp;
    }

    String getDescription() {
        return this.description;
    }

    void end() {
        System.out.println("Закончился " + this.getName());
        if (this.description != "") {
            System.out.println(this.description);
        }
    }

    boolean isFinished() {
        return this.getAmount() == 0;
    }

    boolean wrongTemp() {
        if (this.lastTemp > this.lowTemp && this.lastTemp < this.highTemp) {
            return false;
        }
        else {
            return true;
        }
    }

    public void participate(String event) {
        System.out.println(this.name + " поучавствовал в " + event);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}