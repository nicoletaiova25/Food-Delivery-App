package Food;

import java.util.Scanner;

public class Candy extends Produs {

    private int calories;
    private boolean isVegan;

    public Candy(){}

    public Candy(String name, double price, int calories, boolean isVegan){
        super(name, price);
        this.calories = calories;
        this.isVegan = isVegan;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the name of the sweet product:");
        String name = sc.nextLine();

        System.out.print("Enter the calories of the sweet product:");
        int calories = sc.nextInt();

        System.out.print("Is the product vegan? (true/false):");
        boolean isVegan = sc.nextBoolean();

        System.out.print("Enter the price of the sweet product:");
        double price = sc.nextDouble();

        this.name = name;
        this.calories = calories;
        this.isVegan = isVegan;
        this.price = price;

    }

    @Override
    public String toString(){
        String output = ".......Sweets Section....\n";
        output += "Name: " + this.name + "\n";
        output += "Calories: " + this.calories + "\n";
        output += "Vegan: " + this.isVegan + "\n";
        output += "Price: " + this.price + "\n";

        return output;
    }

    public int getCalories() {
        return calories;
    }

}
