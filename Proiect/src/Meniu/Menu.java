package Meniu;

import Food.Beverage; //importam clasa beverage din pachetul Food
import java.util.*;

public abstract class Menu {

    protected String name;
    protected List<Beverage> beverages;
    protected double price;

    public Menu(){
        this.beverages = new ArrayList<Beverage>();
    }

    public Menu(String name, List<Beverage> beverages) {
        this.name = name;
        this.beverages = beverages;
        //pretul se calculeaza in functie de fiecare produs
    }

    public abstract void reader();

    @Override
    public abstract String toString();

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public List<Beverage> getBeverages() {
        return beverages;
    }
}
