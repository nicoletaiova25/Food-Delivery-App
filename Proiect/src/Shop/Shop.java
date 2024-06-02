package Shop;

import Meniu.Menu;
import Food.Produs;
import User.DeliveryGuy;
import User.Owner;

import java.util.*;

public abstract class Shop {

    protected String name;
    protected Owner owner;
    protected List<DeliveryGuy> deliveryGuys;
    protected double rating;
    HashMap<String, Integer>stock; // String = nume produs/meniu

    public Shop(){
        this.deliveryGuys = new ArrayList<DeliveryGuy>();
        this.stock = new HashMap<String, Integer>();
    }

    public Shop(String name, Owner owner, List<DeliveryGuy> deliveryGuys, HashMap<String, Integer> stock){
        this.name = name;
        this.owner = owner;
        this.deliveryGuys = deliveryGuys;
        this.stock = stock;
        this.rating = 0;
    }

    public void addRating(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Would you like to rate " + this.name + "? (yes/no)" );
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("yes")){
            System.out.print("Rate us any score 0-10: ");
            int rate = sc.nextInt();
            this.rating = (this.rating + rate)/2;
            System.out.println("Thank you for rating us!");
        }
    }

    public String getName(){
        return this.name;
    }

    public List<DeliveryGuy> getDeliveryGuys(){
        return deliveryGuys;
    }

    public abstract void reader();

    @Override
    public abstract String toString();
    public abstract List<Menu>getMenus();
    public abstract List<Produs> getProduses();

    public double getRating(){
        return rating;
    }

    //de modificat
    public void updateDeliveryGuy(DeliveryGuy deliveryGuy){
        int poz = 0;
        for (int i = 0; i < deliveryGuys.size(); i++) {
            if(deliveryGuys.get(i).equals(deliveryGuy)){
                poz = i;
                break;
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to update the car number? (yes/no)" );
        String input = sc.nextLine();

        if(input.equalsIgnoreCase("yes")){
            System.out.print("New Car Number: ");
            String newCarNumber = sc.nextLine();
            deliveryGuy.setCarNumber(newCarNumber);
        }

        System.out.print("Do you want to change his phone number? (yes/no)" );
        input = sc.nextLine();
        if(input.equalsIgnoreCase("yes")){
            System.out.print("New Phone Number: ");
            String newPhoneNumber = sc.nextLine();
            deliveryGuy.setPhoneNumber(newPhoneNumber);
        }
        deliveryGuys.set(poz, deliveryGuy);
    }

    public void updateStock(String productName, int quantity){
        this.stock.put(productName, quantity);
    }

    public void removeProductFromStock(String productName){
        Iterator it = this.stock.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            if (((String)pair.getKey()).equalsIgnoreCase(productName)){
                this.stock.remove(productName);
                break;
            }
        }
    }
    //putem face remove delivery guy from shop???
    // Metoda de eliminare a DeliveryGuy
    public void removeDeliveryGuy(DeliveryGuy deliveryGuy) {
        if (deliveryGuys.remove(deliveryGuy)) {
            System.out.println("Delivery guy removed successfully.");
        } else {
            System.out.println("Delivery guy not found.");
        }
    }

    // Metoda de listare a DeliveryGuys
    public void listDeliveryGuys() {
        if (deliveryGuys.isEmpty()) {
            System.out.println("No delivery guys available.");
        } else {
            for (int i = 0; i < deliveryGuys.size(); i++) {
                System.out.println((i + 1) + ". " + deliveryGuys.get(i));
            }
        }
    }

    public void StockLower(String name){
        stock.replace(name, stock.get(name) - 1);
    }

    public void setRating(int rate){
        this.rating = (this.rating + rate)/2;
    }
}
