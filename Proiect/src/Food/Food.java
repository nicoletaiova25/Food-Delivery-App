package Food;

import java.util.Scanner;

public class Food extends Produs{

    private String ingredients;

    public Food(){}

    public Food(String name, double price, String ingredients) {
        super(name, price);
        this.ingredients = ingredients;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.print("The name of the product is: ");
        String name = sc.nextLine();

        System.out.print("The ingredients are:");
        String ingredients = sc.nextLine();

        System.out.print("The price of the product is: ");
        double price = sc.nextDouble();

        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    @Override
    public String toString(){
        String output = ".....Food Details.......\n";
        output += "Name: " + this.name + "\n";
        output += "Ingredients: " + this.ingredients + "\n";
        output += "Price: " + this.price + "\n";

        return output;
    }

    public String getIngredients() {
        return ingredients;
    }
}
