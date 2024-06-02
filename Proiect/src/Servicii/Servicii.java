package Servicii;

import Meniu.*;
import Food.*;
import Shop.*;
import User.Login;
import User.User;

import java.util.*;

class Sortare implements Comparator<Map.Entry<Integer, Shop>> {

    public int compare(Map.Entry<Integer, Shop> o1, Map.Entry<Integer, Shop> o2) {
        Shop shop1 = o1.getValue();
        Shop shop2 = o2.getValue();

        if(shop1.getRating() > shop2.getRating())
            return -1;
        return 1;
    }
}

public class Servicii { //singleton

    private static Servicii sg_instance = null;
    private Login login;
    private HashMap<Integer, Shop> shops;
    private HashMap<Integer, Order> orders;
    private User current_user;
    private int shopId;
    private int orderId;
    private ReadCSV readingCSVfile;
    private ServiciiAudit writing;

    private Servicii() {
        this.readingCSVfile = ReadCSV.getInstance();
        this.writing = ServiciiAudit.getInstance();
        this.shops = new HashMap<Integer, Shop>();
        this.orders = new HashMap<Integer, Order>();

        //citim magazinele din fisierul csv
        this.shops = this.readingCSVfile.getShops();
    }

    public static synchronized Servicii getInstance() {
        if(sg_instance == null) {
            sg_instance = new Servicii();
        }
        return sg_instance;
    }

    public void addShop(){
        //adaugare restaurant de catre admin
        writing.WriteTimeStamps("Add shop");

        Shop shop;
        Scanner sc = new Scanner(System.in);

        //validare tip restaurant
        while(true) {
            System.out.print("What kind of shop do you want to add? (CoffeShop/CandyShop/Restaurant):  ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("coffeshop")) {
                shop = new CaffeShop();
                break;
            }

            else if (input.equalsIgnoreCase("candyshop")) {
                shop = new CandyShop();
                break;
            }
            else if (input.equalsIgnoreCase("restaurant")) {
                shop = new Restaurant();
                break;
            }
            else{
                System.out.println("Invalid type of shop");
            }

        }
        shopId += 1;
        shop.reader();
        shops.put(shopId, shop);

        System.out.println("Shop added successfully");
    }

    public void deleteShop(){
        //stergere restaurant de catre admin
        writing.WriteTimeStamps("Delete shop");

        Scanner sc = new Scanner(System.in);
        System.out.println("List of the names of the shops");

        Set set = shops.entrySet(); //convertim la set pentru a putea itera
        Iterator it = set.iterator();

        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(((Shop)entry.getValue()).getName());
        }

        System.out.print("Write the name of the shop you want to delete: ");
        String input = sc.nextLine();

        it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();

            if(((Shop)entry.getValue()).getName().equalsIgnoreCase(input)) {
                shops.remove(entry.getKey());
                System.out.println("Shop deleted successfully");
                break;
            }
        }

        System.out.println("List of the remaining shops: ");
        it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(((Shop)entry.getValue()).getName());
        }
    }

    public void listShops(){
        writing.WriteTimeStamps("List all shops");

        Set set = shops.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            System.out.println(((Shop)entry.getValue()).getName() + "\n");
        }
    }

    public void logOff(){
        writing.WriteTimeStamps("Log off");
        login.setCurrentUser(null);
        System.out.println("Logged off successfully!");
    }

    public void addOrder(){
        writing.WriteTimeStamps("Add order");
        Scanner sc = new Scanner(System.in);

        System.out.println(">> Place an order:");

        Order order = new Order();
        order.setCustomer(current_user);
        order.reader(shops);
        orderId += 1;
        order.setPrice();

        if(order.getPrice() > 0.0){
            System.out.println("The total price of the order is: " + order.getPrice());
            System.out.println("Review your order: \n" + order + "\n");
            System.out.print("Do you want to place the order? (yes/no): ");

            String input = sc.nextLine();
            if(input.equalsIgnoreCase("yes")){
                orders.put(orderId, order);
                System.out.println("Your order was procesed succesfully!");
            }
            else {
                this.orders.remove(orderId);
                System.out.println("Your order was cancelled");
            }
        }
    }

    public void addProdus(){
        writing.WriteTimeStamps("Add product");

        Scanner sc = new Scanner (System.in);
        System.out.println("Add a product");
        String input = sc.nextLine();

        Set set = shops.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(input)){
                System.out.println("Shop found");

                if(entry.getValue() instanceof CandyShop){
                    System.out.println("Candy Shop");
                    Candy candy = new Candy();
                    candy.reader();
                    ((CandyShop) entry.getValue()).addCandy(candy);

                    System.out.print("Quantity:");
                    int quantity = sc.nextInt();
                    ((CandyShop) entry.getValue()).updateStock(candy.getName(), quantity);
                    System.out.println("The product was added");

                }
                else if (entry.getValue() instanceof CaffeShop){
                    System.out.println("Coffee Shop");
                    System.out.print("Do you want to add a sweet item or a beverage?:");
                    String answer = sc.nextLine();
                    Produs produs = null;

                    if(answer.equalsIgnoreCase("sweet")){
                        produs = new Candy();
                        produs.reader();
                        ((CaffeShop) entry.getValue()).addCandy((Candy)produs);
                    }
                    else{
                        produs = new Beverage();
                        produs.reader();
                        ((CaffeShop) entry.getValue()).addBeverage((Beverage)produs);
                    }
                    System.out.print("Quantity:");
                    int quantity = sc.nextInt();
                    ((CaffeShop) entry.getValue()).updateStock(produs.getName(), quantity);

                    System.out.println("The product was added to the shop");
                }
                else if(entry.getValue() instanceof Restaurant){
                    System.out.println("Restaurant");
                    System.out.print("Add a drink:");

                    Beverage drink = new Beverage();
                    drink.reader();
                    ((Restaurant) entry.getValue()).addBeverage(drink);
                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();

                    ((Restaurant) entry.getValue()).updateStock(drink.getName(), quantity);
                    System.out.println("The product was added to the shop");

                }
                else{
                    System.out.println("No products found");
                }
                break;

                }
            }
        }

        public void deleteProduct(){
        //stergere produs
            writing.WriteTimeStamps("Delete Product");

            Scanner sc = new Scanner (System.in);
            System.out.println("Remove product");
            listShops();
            System.out.print("Enter the name of the shop: ");
            String name = sc.nextLine();

            Set set = shops.entrySet();
            Iterator it = set.iterator();

            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
                if(((Shop)entry.getValue()).getName().equalsIgnoreCase(name)){
                    System.out.print("Shop found:");

                    if(entry.getValue() instanceof CandyShop){
                        System.out.println("Candy Shop");
                        System.out.print("Enter the name of the item you want removed:");

                        String input = sc.nextLine();
                        List<Candy> candyList = ((CandyShop) entry.getValue()).getCandyList();
                        for(Candy item: candyList){
                            if(item.getName().equalsIgnoreCase(input)){
                                ((CandyShop) entry.getValue()).removeCandy(item);
                                ((CandyShop) entry.getValue()).removeProductFromStock(item.getName());
                                System.out.println("The product was removed");
                                break;
                            }
                        }
                    }
                    else if(entry.getValue() instanceof CaffeShop){
                        System.out.println("Coffee Shop");
                        System.out.print("Enter the name of the item you want removed:");
                        String input = sc.nextLine();
                        List <Produs> produse = ((CaffeShop) entry.getValue()).getProduses();
                        for(Produs item: produse){
                            if(item.getName().equalsIgnoreCase(input)){
                                if(item instanceof Candy){
                                    ((CaffeShop) entry.getValue()).removeCandy((Candy)item);
                                }
                                else{
                                    ((CaffeShop) entry.getValue()).removeBeverage((Beverage)item);
                                }

                                ((CaffeShop) entry.getValue()).removeProductFromStock(item.getName());
                                System.out.println("The product was removed");
                                break;
                            }
                        }
                    }else if (entry.getValue() instanceof Restaurant){
                        System.out.println("Restaurant");
                        System.out.print("Enter the name of the item you want removed:");

                        String input = sc.nextLine();
                        List <Beverage> beverages = ( (Restaurant) entry.getValue()).getBeverages();
                        for (Beverage item: beverages){
                            if(item.getName().equalsIgnoreCase(input)){
                                ((Restaurant) entry.getValue()).removeDrink(item);
                                ((Restaurant) entry.getValue()).removeProductFromStock(item.getName());
                                System.out.println("The product was removed");
                                break;
                            }
                        }
                    } else System.out.println("No products found");
                    break;
                }
            }
        }
        public void listOneShop(){
            writing.WriteTimeStamps("List One");

            Scanner sc = new Scanner (System.in);
            System.out.print("Enter the name of the shop you want to see: \n");
            String name = sc.nextLine();
            System.out.println(name);

            Set set = shops.entrySet();
            Iterator it = set.iterator();
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
                if(((Shop)entry.getValue()).getName().equalsIgnoreCase(name)){
                    System.out.println((Shop)entry.getValue());
                    break;
                }
            }

        }

        public void sortShopsByRating(){
            writing.WriteTimeStamps("Sort Shops by rating\n");

            Set<Map.Entry<Integer,Shop>> set = new TreeSet<>(new Sortare());
            set.addAll(this.shops.entrySet());

            for(Map.Entry<Integer,Shop> entry: set){
                System.out.println(entry.getValue().getName() + "....Rating:" + entry.getValue().getRating());
            }
        }

    public void AddMenu(){
        writing.WriteTimeStamps("Add Menu");

        Scanner sc = new Scanner (System.in);
        System.out.println("Add a new menu");
        System.out.print("Enter the name of the shop: ");
        String name = sc.nextLine();

        Set set = shops.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            if(((Shop)entry.getValue()).getName().equalsIgnoreCase(name)){
                System.out.print("Shop found: ");
                if(entry.getValue() instanceof Restaurant){
                    System.out.println("Restaurant");
                    RMenu rMenu = new RMenu();
                    rMenu.reader();
                    ((Restaurant) entry.getValue()).addrMenu(rMenu);

                    System.out.print("Stock of the restaurant menu:");
                    int quantity = sc.nextInt();
                    ((Restaurant) entry.getValue()).updateStock(rMenu.getName(), quantity);

                    System.out.println("The restaurant menu was added to the shop");
                }
                else{
                    System.out.println("No restaurant found");
                }
                break;
            }
        }
    }

    public int logIn(){
        login = Login.getInstance();
        login.setUsers(this.readingCSVfile.getUsers());

        int type = 0;

        while (true){
            Scanner sc = new Scanner (System.in);
            System.out.print("Do you want to Sign In/ Sign Up? (in/up): ");
            String input = sc.nextLine();

            if(input.equalsIgnoreCase("in")){
                writing.WriteTimeStamps("Sign In");

                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                if(login.signIn(email, password)){
                    System.out.println("You are logged in");
                    current_user = login.getCurrentUser();
                    if(email.equals("admin@gmail.com"))
                        type = 1;
                    break;
                }
                else{
                    System.out.println("Wrong email or password");
                }
            }
            else{
                writing.WriteTimeStamps("Sign Up");

                User customer = new User();
                customer.reader();

                if(login.signUp(customer)){
                    System.out.println("Your account have been registered");
                    current_user = customer;
                }
                else System.out.println("You are already logged in");
            }
        }
        return type;
    }

    public void rateShop(){
        writing.WriteTimeStamps("Rate Shop");

        Scanner sc = new Scanner (System.in);
        System.out.println("Listing the shops");

        if(shops.isEmpty())
            System.out.println("No shops found");
        else{
            Set set = shops.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                System.out.println(((Shop) entry.getValue()).getName());
            }
            System.out.print("Enter the name of the shop you want to rate: ");
            String input = sc.nextLine();

            it = set.iterator();
            Shop shop;
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry) it.next();
                if(((Shop) entry.getValue()).getName().equalsIgnoreCase(input)){
                    shop = (Shop) entry.getValue();
                    System.out.print("Enter any score between 0-10: ");
                    int score = sc.nextInt();
                    shop.setRating(score);
                    System.out.println("Thanks for rating us!");
                    break;
                }
            }

        }
    }

    public void writeOneShop(){
        writing.WriteTimeStamps("Write One Shop in CSV");

        Scanner sc = new Scanner (System.in);
        WriteCSV writeCSV = WriteCSV.getInstance();

        System.out.println("Enter the name of the shop you want to write: ");
        String name = sc.nextLine();
        System.out.println("Enter the path of the file you want to write in (eg. Files/NewFile.csv): ");
        String path = sc.nextLine();

        Set set = shops.entrySet();
        Iterator it = set.iterator();
        Boolean ok = false;

        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            if(((Shop) entry.getValue()).getName().equalsIgnoreCase(name)){
                ok = true;
                System.out.println("Shop found: " + name);
                writeCSV.writeShop(entry.getValue(), entry.getValue().getClass(), path);
                break;
            }
        }
        if(ok == false){
            System.out.println("Shop not found");
        }
    }

    public String getCurrentUsersEmail(){
        return current_user.getEmail();
    }
}

