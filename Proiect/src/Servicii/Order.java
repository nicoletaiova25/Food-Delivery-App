package Servicii;

import Meniu.*;
import Food.Produs;
import Shop.*;
import User.DeliveryGuy;
import User.User;

import java.util.*;

public class Order {
    private User customer;
    private DeliveryGuy deliveryGuy;
    private Shop shop;
    private double price;
    private List<Menu> menus;
    private List<Produs> produse;
    private String address;

    public Order() {
        this.menus = new ArrayList<Menu>();
        this.produse = new ArrayList<Produs>();
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    private int getRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public void setDeliveryGuy(){
        List <DeliveryGuy> D = this.shop.getDeliveryGuys();
        int random = getRandomNumber(0, D.size()-1);
        this.deliveryGuy = D.get(random);
    }

    public void setPrice(){
        double totalPrice = 0;

        for(Menu m : menus){
            totalPrice += m.getPrice();
        }

        for(Produs p : produse){
            totalPrice += p.getPrice();
        }
        this.price = totalPrice;
    }

    public double getPrice(){
        return this.price;
    }

    public void setMenus(Menu meniu){
        this.menus.add(meniu);
    }

    public void setProduse(Produs produse){
        this.produse.add(produse);
    }

    @Override
    public String toString() {
        String output = "" + this.customer + "\n";
        output += "Total Price: " + this.price + "lei\n";
        output += "Delivery address: " + this.address + "\n";
        output += this.deliveryGuy;
        output += "Shop " + this.shop.getName() + "\n";
        output += "The menus you chose: \n";

        for(Menu m : menus){
            output += m;
        }

        output += "The products you chose: \n";

        for(Produs p : produse){
            output += p;
        }

        return output;

    }

    public void reader (HashMap<Integer, Shop> shops){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the delivery address: ");
        String address = sc.nextLine();
        this.address = address;

        System.out.print(">>List of the shops: ");
        if(shops.isEmpty()){
            System.out.println("No shops found");
        }
        else{
            Set set = shops.entrySet(); //convertim la set pentru a putea itera
            Iterator it = set.iterator();
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next(); //convertim la Map.Entry pentru a putea lua fiecare cheie in parte
                System.out.println(((Shop) entry.getValue()).getName());
            }
            System.out.println("Choose one of the listed shops: ");
            String shopName = sc.nextLine();

            it = set.iterator(); //reinitializam iteratorul
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();

                if(((Shop) entry.getValue()).getName().equalsIgnoreCase(shopName)){
                    //downcasting
                    if (entry.getValue() instanceof CandyShop){
                        this.shop = (CandyShop) entry.getValue();
                    } else if (entry.getValue() instanceof CaffeShop){
                        this.shop = (CaffeShop) entry.getValue();
                    }else
                        this.shop = (Restaurant) entry.getValue();

                    break;

                }
            }

            this.setDeliveryGuy(); //aleg random un livrator

            List <Produs> produse = this.shop.getProduses();
            int choice = 0;
            System.out.print(">>The products available are: ");

            for(int i = 0; i<produse.size(); i++){
                System.out.println("Product number " + i + " is:");
                System.out.println(produse.get(i));
            }
            String input;
            if (this.shop instanceof Restaurant) { // Verificați dacă shop-ul este un restaurant
                List<Menu> menus = this.shop.getMenus();

                System.out.print(">>The menus available are: ");

                for(int i = 0; i < menus.size(); i++){
                    System.out.println("Menu number" + i + "is:");
                    System.out.println(menus.get(i));
                }

                System.out.print("Do you want to add a menu? (yes/no): ");
                input = sc.nextLine();

                if(input.equalsIgnoreCase("yes")){
                    while(true){
                        //asta e posibil sa nu functioneze
                        System.out.print("Choose menu number: ");

                        choice = sc.nextInt();
                        Menu meniu = ((Restaurant) this.shop).orderRMenu((RMenu) menus.get(choice));

                        this.setMenus(meniu);
                        this.shop.StockLower(meniu.getName()); //scad cantitatea stocului

                        System.out.print ("Do you want to add another menu? (yes/no");
                        sc.nextLine();
                        input = sc.nextLine();
                        if(input.equalsIgnoreCase("no")){
                            break;
                        }
                    }
                }
            }

            System.out.print("Do you want to add an item? (yes/no): ");
                input = sc.nextLine();
                if(input.equalsIgnoreCase("yes")){
                    while(true){
                        System.out.print("Choose item number: ");
                        choice = sc.nextInt();
                        this.setProduse(produse.get(choice));

                        this.shop.StockLower((produse.get(choice)).getName());

                        System.out.print("Do you want to add another item? (yes/no): ");
                        sc.nextLine();
                        input = sc.nextLine();
                        if(input.equalsIgnoreCase("no")){
                            break;
                        }
                    }
                }
            }

        }
    }



