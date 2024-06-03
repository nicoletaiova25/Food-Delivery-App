package Shop;

import Food.Beverage;
import Meniu.*;
import Food.*;
import User.DeliveryGuy;
import User.Owner;

import java.util.*;
public class Restaurant extends Shop{

    private List<RMenu> rMenus;
    private List<Beverage> drinks;

    public Restaurant(){

        this.drinks = new ArrayList<Beverage>();
        this.rMenus = new ArrayList<RMenu>();
    }

    public Restaurant(String name, Owner owner, List<DeliveryGuy> deliveryGuys, List<Beverage> drinks, List<RMenu> rMenus, HashMap<String, Integer> stock){
        super(name, owner, deliveryGuys, stock);
        this.rMenus = rMenus;
        this.drinks = drinks;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Restaurant name:");
        String name = sc.nextLine();
        this.name = name;

        System.out.println(">>>Restaurant delivery guys:");
        System.out.print("How many delivery guys do you want:");
        int nrDeliveryGuys = sc.nextInt();

        for(int i = 0; i < nrDeliveryGuys; i++){
            DeliveryGuy deliveryGuy = new DeliveryGuy();
            deliveryGuy.reader();
            deliveryGuys.add(deliveryGuy);
        }

        System.out.println("List of drinks available:");
        System.out.print("Enter the number of drinks:");
        int nrDrinks = sc.nextInt();

        for (int i = 0; i < nrDrinks; i++){
            System.out.println("Enter the drink number " + i + ": ");
            Beverage drink = new Beverage();
            drink.reader();
            this.drinks.add(drink);

            System.out.print ("Enter the quantity of " + drink.getName() + ": ");
            int quantity = sc.nextInt();
            stock.put(drink.getName(), quantity);
        }

        System.out.println(">>>> Restaurant's list of food items:");
        System.out.print("Enter the number of food items: ");
        int nrFoodItems = sc.nextInt();

        for(int  i = 0; i < nrFoodItems; i++){
            System.out.println("Enter the food item number " +i + ": ");
            RMenu rMenu = new RMenu();
            rMenu.reader();
            rMenus.add(rMenu);

            System.out.print("Enter the quantity " + rMenu.getName() + ": ");
            int quantity = sc.nextInt();
            stock.put(rMenu.getName(), quantity);

        }

        Owner owner = new Owner();
        owner.reader(this);
        this.owner = owner;
    }

    @Override
    public String toString(){
        String output = "........Restaurant.........\n";
        output += "Name: " + this.name + "\n";
        output += this.owner + "\n";
        output += "Restaurant rating: " + this.rating + "\n";
        output += "List of Delivery Guys: \n";

        for (DeliveryGuy deliveryGuy : this.deliveryGuys){
            output += deliveryGuy + "\n";
        }

        output += ">> List of Drinks: \n";

        for (Beverage drink: this.drinks){
            output += drink + "\nStock:" + stock.get(drink.getName()) + "\n";
        }
        output += ">>> List of Menus: \n";

        for (RMenu rMenu: rMenus){
            output += rMenu + "\nStock:" + stock.get(rMenu.getName()) + "\n";
        }
        return output;
    }

    @Override
    public List<Menu> getMenus(){
        List<Menu> lista = new ArrayList<Menu>();
        for (RMenu it: rMenus){
            lista.add(it);
        }
        return lista;
    }

    //???
    @Override
    public List<Produs> getProduses(){
        List<Produs> lista = new ArrayList<Produs>();
        for (Beverage it : drinks){
            lista.add(it);
        }
        return lista;
    }

    public void addrMenu(RMenu menu){
        rMenus.add(menu);
    }

    public void addBeverage(Beverage drink){
        drinks.add(drink);
    }

    public void removeDrink (Beverage drink){
        for(Beverage it: drinks){
            if (it.equals(drink)){
                drinks.remove(drink);
                break;
            }
        }
    }

    public List<Beverage> getBeverages(){
        return drinks;
    }

    public RMenu orderRMenu(RMenu choice){
        Scanner sc = new Scanner (System.in);
        List <Candy> S = new ArrayList <Candy>();
        int i = 0;

        System.out.println(">>>>List of sweets: ");

        for (Candy candy: choice.getCandyList() ){
            System.out.println("Sweet item number " + i + ": \n");
            i++;
            System.out.println(candy);
        }

        System.out.print("Chooce from the list above: ");
        int choose = sc.nextInt();
        S.add(choice.getCandyList().get(choose));

        List <Food> food = new ArrayList<Food>();
        i=0;
        System.out.println(">>>List of food items to choose from: ");

        for (Food rfood: choice.getFoodList()) {
            System.out.println("Food item number " + i + ":\n");
            i++;
            System.out.println(rfood);
        }

        System.out.print(">>> Choose a food item's number:");
        choose = sc.nextInt();
        food.add(choice.getFoodList().get(choose));

        List <Beverage> B = new ArrayList<Beverage>();
        i=0;
        System.out.println(">>> List of drinks to choose from");

        for (Beverage drink : choice.getBeverages()){
            System.out.println("Drink item number " + i + ":\n");
            i++;
            System.out.println(drink);
        }

        System.out.print(">>> Choose a beverage item's number:");
        choose = sc.nextInt();
        B.add(choice.getBeverages().get(choose));

        RMenu rMenu = new RMenu(choice.getName(), B,S,food);
        return rMenu;
    }
}