package Food;

import java.util.Scanner;

public class Beverage extends Produs {
    private String flavour;

    public Beverage() {}

    public Beverage (String name, double price, String flavour) {
        super(name, price);
        this.flavour = flavour;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the beverage + quantity: "); //example ColaZero300 as in 300 ml
        String name = sc.nextLine();

        System.out.print("Enter the flavour of the beverage:");
        String flavour = sc.nextLine();

        System.out.print("Enter the price of the beverage:");
        double price = sc.nextDouble();

        this.name = name;
        this.price = price;
        this.flavour = flavour;
    }

    @Override
    public String toString() {
        String output= ".....Beverage..........\n";
        output+= "Name: " +this.name+"\n";
        output+= "Price: " +this.price+"\n";
        output+= "Flavour: " +this.flavour+"\n";

        return output;
    }

    public String getFlavour() {
        return flavour;
    }
}
