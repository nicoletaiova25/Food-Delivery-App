package Meniu;

import Food.Beverage;
import Food.Food;
import Food.Candy;


import java.util.*;

public class RMenu extends Menu {

    private List<Candy> candyList;
    private List<Food> foodList;

    public RMenu() {
        this.candyList = new ArrayList<Candy>();
        this.foodList = new ArrayList<Food>();
    }

    public RMenu(String name, List<Beverage> drinks, List<Candy> candyList, List<Food> foodList) {
        super(name, drinks);

        this.candyList = candyList;
        this.foodList = foodList;

        //calculam pretul
        double totalPrice = 0;
        for (Candy candy : candyList) {
            totalPrice += candy.getPrice();
        }
        for (Food food : foodList) {
            totalPrice += food.getPrice();
        }
        for (Beverage beverage : drinks) {
            totalPrice += beverage.getPrice();
        }
        this.price = totalPrice;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Restaurant Menu");
        String name = sc.nextLine();
        this.name = name;

        System.out.println("List of drinks on the Menu");
        System.out.print("Number of Drinks: ");

        int numberDrinks = sc.nextInt();

        for (int i = 0; i < numberDrinks; i++) {
            System.out.print("Enter details about drink number" + i + ":");
            Beverage drink = new Beverage();
            drink.reader();
            this.beverages.add(drink);
        }

        System.out.println("List of sweet items on the Menu");
        System.out.print("Number of sweet items: ");

        int numberSweets = sc.nextInt();

        for (int i = 0; i < numberSweets; i++) {
            System.out.print("Enter sweet item number" + i + ":");
            Candy sweet = new Candy();
            sweet.reader();
            this.candyList.add(sweet);
        }

        System.out.println("List of food items on the Menu");
        System.out.print("Number of food items: ");

        int numberFoods = sc.nextInt();

        for (int i = 0; i < numberFoods; i++) {
            System.out.print("Enter food item number" + i + ":");
            Food food = new Food();
            food.reader();
            this.foodList.add(food);
        }

        double totalPrice = 0;
        for (Candy candy : candyList) {
            totalPrice += candy.getPrice();
        }

        for (Food food : foodList) {
            totalPrice += food.getPrice();
        }

        for(Beverage beverage : beverages) {
            totalPrice += beverage.getPrice();
        }

        this.price = totalPrice;
    }

    @Override
    public String toString() {
        String output=".......Restaurant Menu......\n";
        output += "Name: " + this.name + "\n";
        output += "Menu Price: " + this.price + " lei \n";
        output += "Food options: \n";

        for (Food food : this.foodList) {
            output += food;
        }

        output += "Drink options: \n";

        for (Beverage beverage : this.beverages) {
            output += beverage;
        }

        output += "Desert options: \n";

        for (Candy candy : this.candyList) {
            output += candy;
        }

        return output;
    }

    public List<Candy> getCandyList() {
        return candyList;
    }

    public List<Food> getFoodList() {
        return foodList;
    }
}
