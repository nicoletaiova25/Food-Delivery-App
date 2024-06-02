package Servicii;

import Meniu.*;
import Food.*;
import Shop.*;
import User.*;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {  //singleton

    private static ReadCSV single_instance = null;
    private HashMap<Integer, Shop> shops;
    private int shopId;
    private Set<User> users;

    private void readCandyShop(String[] array, int k, Owner owner, List <DeliveryGuy> deliveryGuys) {
        String name = array[k++];
        int n = Integer.parseInt(array[k++]);
        List<Candy> candyList = new ArrayList<>();
        HashMap<String, Integer> stock = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Candy candy = new Candy(array[k++], //nume
                                Double.parseDouble(array[k++]), //pret
                                Integer.parseInt(array[k++]), //calorii
                                Boolean.parseBoolean(array[k++])); //isvegan?
            candyList.add(candy);
            stock.put(candy.getName(), Integer.parseInt(array[k++]));
        }

        CandyShop shop = new CandyShop(name, owner, deliveryGuys, candyList, stock);
        this.shopId += 1;
        this.shops.put(this.shopId, shop);
    }

    private void readCaffeShop(String[] array, int k, Owner owner, List <DeliveryGuy> deliveryGuys) {
        String name = array[k++];
        int numBeverages = Integer.parseInt(array[k++]);
        List <Beverage> beverageList = new ArrayList<>();
        HashMap<String, Integer> stock = new HashMap<>();

        for (int i = 0; i < numBeverages; i++){
            Beverage beverage = new Beverage(array[k++], //nume
                                            Double.parseDouble(array[k++]), //pret
                                            array[k++]); //flavor
            beverageList.add(beverage);
            stock.put(beverage.getName(), Integer.parseInt(array[k++]));
        }

        int numSweets = Integer.parseInt(array[k++]);
        List <Candy> candyList = new ArrayList<>();

        for (int i = 0; i < numSweets; i++){
            Candy candy = new Candy(array[k++], //nume
                                    Double.parseDouble(array[k++]), //pret
                                    Integer.parseInt(array[k++]),   //calorii
                                    Boolean.parseBoolean(array[k++])); //isVegan?
            candyList.add(candy);
            stock.put(candy.getName(), Integer.parseInt(array[k++]));
        }

        CaffeShop shop = new CaffeShop(name, owner, deliveryGuys, beverageList, candyList, stock );
        this.shopId += 1;
        this.shops.put(this.shopId, shop);
    }

    private void readRestaurant(String[] array,Owner owner, int k, List <DeliveryGuy> deliveryGuys) {
        String name = array[k++];
        int nrDrinks = Integer.parseInt(array[k++]);
        List <Beverage> beverageList = new ArrayList<>();
        HashMap<String, Integer> stock = new HashMap<>();

        for (int i = 0; i < nrDrinks; i++){
            Beverage beverage = new Beverage(array[k++], //nume
                                        Double.parseDouble(array[k++]), //pret
                                        array[k++]); //flavor
            beverageList.add(beverage);
            stock.put(beverage.getName(), Integer.parseInt(array[k++]));
        }

        int nrMenus = Integer.parseInt(array[k++]);
        List <RMenu> rMenus = new ArrayList<>();

        for (int i = 0; i < nrMenus; i++){
            String menuName = array[k++];
            int nrFoodItems = Integer.parseInt(array[k++]);
            List <Food> foodList = new ArrayList<>();
            for (int j = 0; j < nrFoodItems; j++){
                Food food = new Food(array[k++], //nume
                                    Double.parseDouble(array[k++]), //pret
                                    array[k++]); //ingrediente
                foodList.add(food);
            }
            int nrSweets = Integer.parseInt(array[k++]);
            List<Candy> candyList = new ArrayList<>();
            for (int j = 0; j < nrSweets; j++){
                Candy candy = new Candy(array[k++], //name
                                Double.parseDouble(array[k++]), //price
                                Integer.parseInt(array[k++]), //calories
                                Boolean.parseBoolean(array[k++]));      //isVegan?
                candyList.add(candy);
            }

            int nrDrinkInMenu = Integer.parseInt(array[k++]);
            List<Beverage> beveragesInMenu = new ArrayList<>();
            for (int j = 0; j < nrDrinkInMenu; j++){
                Beverage beverage = new Beverage(array[k++],
                                                Double.parseDouble(array[k++]),
                                                array[k++]);
                beveragesInMenu.add(beverage);
            }

            RMenu rMenu = new RMenu(menuName, beveragesInMenu, candyList, foodList);
            rMenus.add(rMenu);
        }

        Restaurant restaurant = new Restaurant(name, owner, deliveryGuys, beverageList, rMenus, stock);
        this.shopId += 1;
        this.shops.put(shopId, restaurant);

    }

   private <T> void readCSV(String path, Class<T> classOf){
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(path));

            String line = buffer.readLine();

            while (line != null){
                String[] array = line.split(","); //fiecare elem din fisier e despartit de ,
                int k = 0;
                Owner owner = new Owner(array[k++],  //name
                                        array[k++],  //email
                                        array[k++]);  //phone number
                int n = Integer.parseInt(array[k++]);
                List<DeliveryGuy> deliveryGuys = new ArrayList<DeliveryGuy>();

                for(int i = 0; i < n; i++){
                    DeliveryGuy d = new DeliveryGuy(array[k++], array[k++], array[k++], array[k++]);
                    deliveryGuys.add(d);
                }

                if(classOf.toString().equals("class Shop.CandyShop")) {
                    this.readCandyShop(array, k, owner, deliveryGuys);
                }
                else if(classOf.toString().equals("class Shop.CaffeShop")) {
                    this.readCaffeShop(array, k, owner, deliveryGuys);
                }
                else if (classOf.toString().equals("class Shop.Restaurant")){
                    this.readRestaurant(array, owner, k, deliveryGuys);
                }
                else {
                    System.out.println("Eroare la citirea din fisier");
                }

                line = buffer.readLine();
            }

            }
        catch (IOException e){
            e.printStackTrace();
        }
   }

   private ReadCSV(){
        this.users = new HashSet<User>();
        this.shops = new HashMap<Integer, Shop>();

        //apelare metoda de citire a userilor
        ReadUsers();
       //citire restaurante folosind metoda generica
       this.readCSV("C:\\Users\\nicol\\Desktop\\Proiect\\Files\\CandyShop.csv", CandyShop.class);
       this.readCSV("C:\\Users\\nicol\\Desktop\\Proiect\\Files\\CaffeShop.csv", CaffeShop.class);
       this.readCSV("C:\\Users\\nicol\\Desktop\\Proiect\\Files\\Restaurant.csv", Restaurant.class);
   }

   private void ReadUsers(){
        //setare date de admin
       String name = "Admin";
       String email = "admin@gmail.com";
       String phone = "0762929174";
       String password = "Admin01";
       User admin = new User (name, email, phone, password);
       this.users.add(admin);

       //citirea din fisier csv
       try{
           String path = "C:\\Users\\nicol\\Desktop\\Proiect\\Files\\Users.csv";
           BufferedReader buffer = new BufferedReader (new FileReader(path));

           String line = buffer.readLine();

           while (line != null){
               String [] array = line.split(",");
               User user = new User(array[0], array[1], array[2], array[3]);
               this.users.add(user);
               line = buffer.readLine();
           }
       } catch (IOException e){
           e.printStackTrace();
       }

   }

   public static synchronized ReadCSV getInstance(){
        if (single_instance == null)
            single_instance = new ReadCSV();
        return single_instance;
   }

   public HashMap<Integer, Shop> getShops(){
        return shops;
   }

   public Set<User> getUsers(){
        return users;
   }
}
