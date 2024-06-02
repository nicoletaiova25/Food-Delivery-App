package Servicii;

import Shop.*;
import java.io.*;

public class WriteCSV {  //singleton
    private static WriteCSV sg_instance = null;
    private BufferedWriter buffer;

    private WriteCSV() {}

    public static synchronized WriteCSV getInstance() {
        if(sg_instance == null){
            sg_instance = new WriteCSV();
        }
        return sg_instance;
    }

    public <T> void writeShop(Object shop, Class<T> classOf, String path){

        try {
            buffer = new BufferedWriter(new FileWriter(path, true));

            //sterge continut dinaintea pornirii programului
            new FileWriter(path, false).close();
            if(classOf.toString().equals("class Shop.CandyShop")){
                System.out.println("You want to write a CakeShop");
                buffer.write(((CandyShop)shop).toString());
            }
            else if (classOf.toString().equals("class Shop.CaffeShop")){
                System.out.println("You want to write a CoffeeShop");
                buffer.write(((CaffeShop)shop).toString());
            }
            else if (classOf.toString().equals("class Shop.Restaurant")){
                System.out.println("You want to write a Restaurant");
                buffer.write(((Restaurant)shop).toString());
            }

            buffer.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
