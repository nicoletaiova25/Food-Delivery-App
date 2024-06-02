package Shop;

import Meniu.*;
import Food.*;
import User.DeliveryGuy;
import User.Owner;

import java.util.*;

public class CaffeShop extends Shop {

    private List<Beverage> drinks;
    private List<Candy> sweets;

    public CaffeShop() {
        drinks = new ArrayList<Beverage>();
        sweets = new ArrayList<Candy>();
    }

    public CaffeShop(String name, Owner owner, List<DeliveryGuy> deliveryGuys, List<Beverage> drinks, List<Candy> sweets, HashMap<String, Integer> stock) {
        super(name, owner, deliveryGuys, stock);
        this.drinks = drinks;
        this.sweets = sweets;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the name of the CoffeeShop: ");
        String name = sc.nextLine();
        this.name = name;

        System.out.println(">>>"+this.name+"'s delivery guys: ");
        System.out.print("Enter the number of delivery guys:");
        int numberOfDeliveryGuys = sc.nextInt();

        for(int i=0; i<numberOfDeliveryGuys; i++){
            DeliveryGuy deliveryGuy = new DeliveryGuy();
            deliveryGuy.reader();
            deliveryGuys.add(deliveryGuy);
        }

        System.out.println("....The Menu of the Coffee Shop....");
        System.out.print("Enter the number of beverages:");
        int numberOfBeverages = sc.nextInt();

        for(int i=0; i<numberOfBeverages; i++){
            System.out.print("Enter drink number " + i + ": ");
            Beverage drink = new Beverage();
            drink.reader();
            this.drinks.add(drink);

            System.out.print("Enter the quantity of " + drink.getName() + ": ");
            int quantity = sc.nextInt();
            stock.put(drink.getName(), quantity);
        }

        System.out.println("The list of sweet items of the Coffee Shop: ");
        System.out.print("Enter the number of sweets:");
        int numberOfSweets = sc.nextInt();

        for(int i=0; i<numberOfSweets; i++){
            System.out.print("Enter sweet item number " + i + ": ");
            Candy sweet = new Candy();
            sweet.reader();
            this.sweets.add(sweet);

            System.out.println("Enter the quantity of " + sweet.getName() + ": ");
            int quantity = sc.nextInt();
            stock.put(sweet.getName(), quantity);
        }

        Owner owner = new Owner();
        owner.reader(this);
        this.owner = owner;

    }

    @Override
    public String toString(){
        String output = "........Coffee Shop.......\n";
        output += "Name: " + this.name + "\n";
        output += "Owner: " + this.owner + "\n";
        output += "Restaurant rating: " + this.rating + "\n";
        output += ">>>List of the Delivery Guys: \n";

        for(DeliveryGuy deliveryGuy : deliveryGuys){
            output += deliveryGuy + "\n";
        }

        output += ">>>List of the Beverages: \n";
        for(Beverage beverage : this.drinks){
            output += beverage + "\n Quantity:" + stock.get(beverage.getName()) +"\n";
        }

        output += ">>>List of the sweet items: \n";
        for(Candy candy : this.sweets){
            output += candy + "\n Quantity:" + stock.get(candy.getName()) +"\n";
        }

        return output;
    }

    @Override
    public List<Menu> getMenus(){
        return null;
    }

    @Override
    public List<Produs> getProduses(){
        List <Produs> lista = new ArrayList<Produs>();
        for (Beverage beverage : drinks) {
            lista.add(beverage);
        }

        for(Candy candy : sweets) {
            lista.add(candy);
        }
        return lista;
    }

    public void addBeverage(Beverage beverage){
        drinks.add(beverage);
    }

    public void addCandy(Candy candy){
        sweets.add(candy);
    }

    public void removeBeverage(Beverage beverage){
        for(Beverage it: drinks){
            if(it.equals(beverage)){
                drinks.remove(it);
                break;
            }
        }
    }

    public void removeCandy(Candy candy){
        for(Candy c: sweets){
            if(c.equals(candy)){
                sweets.remove(c);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if (!(o instanceof CaffeShop))
            return false;
        CaffeShop shop = (CaffeShop) o;

        return Objects.equals(drinks, shop.drinks) &&
                Objects.equals(sweets, shop.sweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinks, sweets);
    }


}
