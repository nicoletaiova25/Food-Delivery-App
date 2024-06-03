package Shop;

import Meniu.Menu;
import Food.*;
import User.DeliveryGuy;
import User.Owner;

import java.util.*;


public class CandyShop extends Shop {
    private List<Candy> candyList;

    public CandyShop() {
        this.candyList = new ArrayList<Candy>();
    }

    public CandyShop(String name, Owner owner, List<DeliveryGuy> deliveryGuys, List<Candy> candyList, HashMap<String, Integer>stock){
        super(name, owner, deliveryGuys, stock);
        this.candyList = candyList;
    }

    @Override
    public void reader(){
        Scanner sc = new Scanner(System.in);

        System.out.println("The CandyShop name: ");
        String name = sc.nextLine();
        this.name = name;

        System.out.println(">>>The CandyShop's delivery guys: ");
        System.out.print("Enter the number of delivery guys:");
        int numberOfDeliveryGuys = sc.nextInt();

        for(int i=0; i<numberOfDeliveryGuys; i++){
            DeliveryGuy deliveryGuy = new DeliveryGuy();
            deliveryGuy.reader();
            deliveryGuys.add(deliveryGuy);
        }

        System.out.println("....The Menu of the CandyShop....");
        System.out.print("Enter the number of candies:");
        int numberOfCandies = sc.nextInt();

        for(int i=0; i<numberOfCandies; i++){
            Candy candy = new Candy();
            candy.reader();
            candyList.add(candy);

            System.out.print("Enter the quantity of " + candy.getName() + ": ");
            int quantity = sc.nextInt();
            stock.put(candy.getName(), quantity);
        }

        Owner owner = new Owner();
        owner.reader(this);
        this.owner = owner;
    }

    @Override
    public String toString(){
        String output = "........CandyShop.......\n";
        output += "Name: " + this.name + "\n";
        output += "Owner: " + this.owner + "\n";
        output += "The list of delivery guys: \n";

        for(DeliveryGuy deliveryGuy : deliveryGuys){
            output += deliveryGuy + "\n";
        }

        output += "Restaurant's rating: " + this.rating + "\n";
        output += ">>>List of Sweet items: \n";

        for (Candy candy : this.candyList){
            output += candy +"Quantity:" + stock.get(candy.getName()) + "\n";
        }

        return output;
    }

    @Override
    public List<Menu> getMenus(){
        return null;
    }

    @Override
    public List<Produs> getProduses(){
        List<Produs> lista = new ArrayList<Produs>();

        for(Candy it: candyList){
            lista.add(it);
        }
        return lista;
    }

    public void addCandy(Candy candy){
        candyList.add(candy);
    }

    public void removeCandy(Candy candy){
        for(Candy it: candyList){
            if(it.equals(candy)){
                candyList.remove(candy);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o){
        if( this == o)
            return true;
        if(!(o instanceof CandyShop))
            return false;
        CandyShop candyShop = (CandyShop) o;
        return Objects.equals(candyList, candyShop.candyList);
    }

    @Override
    public int hashCode(){
        return Objects.hash(candyList);
    }

    public List<Candy> getCandyList(){
        return candyList;
    }
}
